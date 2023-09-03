package com.craftdemo.inventorymanager.service.impl;

import ch.qos.logback.classic.Logger;
import com.craftdemo.inventorymanager.dto.ProductFilterCriteria;
import com.craftdemo.inventorymanager.dto.ProductInfoDTO;
import com.craftdemo.inventorymanager.exception.BadRequestException;
import com.craftdemo.inventorymanager.exception.ResourceNotFoundException;
import com.craftdemo.inventorymanager.filter.CategoryFilter;
import com.craftdemo.inventorymanager.filter.PriceRangeFilter;
import com.craftdemo.inventorymanager.filter.ProductFilter;
import com.craftdemo.inventorymanager.filter.VendorFilter;
import com.craftdemo.inventorymanager.model.Product;
import com.craftdemo.inventorymanager.model.Shelf;
import com.craftdemo.inventorymanager.model.Vendor;
import com.craftdemo.inventorymanager.repository.ProductRepository;
import com.craftdemo.inventorymanager.repository.ShelfRepository;
import com.craftdemo.inventorymanager.repository.VendorRepository;
import com.craftdemo.inventorymanager.service.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${product.max_capacity}")
            int maxCapacity;
    Logger logger = (Logger) LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ShelfRepository shelfRepository;

    private final ShelfServiceImpl shelfService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, VendorRepository vendorRepository, ShelfRepository shelfRepository,ShelfServiceImpl shelfService) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.shelfRepository = shelfRepository;
        this.shelfService=shelfService;
    }

    private ProductInfoDTO mapProductToProductInfoDTO(Product product) {
        ProductInfoDTO.VendorDetails vendorDetails = new ProductInfoDTO.VendorDetails(
                product.getVendor().getId(),
                product.getVendor().getName(),
                product.getVendor().getLink()
        );
        return new ProductInfoDTO(
                product.getSku(),
                product.getCategory(),
                product.getName(),
                product.getPricePerUnit(),
                product.getShelf().getShelfNumber(),
                product.getShelf().getMaxCapacity(),
                product.getShelf().getQuantity(),
                product.getVendor().getId(),
                product.getShelf().isPrime(),
                vendorDetails
        );
    }

    @Override
    public Page<ProductInfoDTO> getFilteredData(
            ProductFilterCriteria criteria, String orderBy, String sortOrder, int page, int size) {

            if(criteria.getMaxPrice().isPresent() && criteria.getMinPrice().isPresent() && criteria.getMaxPrice().get() < criteria.getMinPrice().get())
                throw new BadRequestException("Min Price can not be smaller than max price");

            AtomicReference<Specification<Product>> spec = new AtomicReference<>(Specification.where(null));
            criteria.getCategory().ifPresent(category -> spec.updateAndGet(currentSpec -> currentSpec.and(new CategoryFilter(category))));
            criteria.getProductName().ifPresent(name -> spec.updateAndGet(currentSpec -> currentSpec.and(new ProductFilter(name))));
            criteria.getVendorId().ifPresent(vendorId -> {
                Vendor vendor = new Vendor(); // Create a Vendor object with the provided ID
                vendor.setId(vendorId);
                spec.updateAndGet(currentSpec -> currentSpec.and(new VendorFilter(vendor)));
            });
            double minPrice = criteria.getMinPrice().orElse(Double.MIN_VALUE);
            double maxPrice = criteria.getMaxPrice().orElse(Double.MAX_VALUE);
            spec.updateAndGet(currentSpec -> currentSpec.and(new PriceRangeFilter(minPrice, maxPrice)));
            Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, orderBy); // Create a Sort object based on the orderBy parameter
            Pageable pageable = PageRequest.of(page, size, sort); // Create a Pageable object for pagination
            Page<Product> productPage = productRepository.findAll(spec.get(), pageable);
            if (productPage.isEmpty()) {
             logger.warn("No products found.");
            }
            return productPage.map(this::mapProductToProductInfoDTO);
    }

    private String generateSKU(String productName, String category) {
        return "sku_" + String.join("_", productName.toLowerCase().split(" ")) + "_" + String.join("_", category.toLowerCase().split(" "));
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void addProduct(ProductInfoDTO productInfoDTO) {
        try {
            Vendor vendor = vendorRepository.getReferenceById(productInfoDTO.getVendor().getId());
            List<Integer> shelvesAvailable=shelfService.getAvailableShelves();
            if(!shelvesAvailable.contains(productInfoDTO.getShelfNumber()))
                throw new BadRequestException("Shelf number "+ productInfoDTO.getShelfNumber()+ " is not available pick from "+shelvesAvailable);
           if(productInfoDTO.getQuantity()>maxCapacity)
               throw new BadRequestException("Max Quantity for this product is "+maxCapacity);
            Shelf shelf = new Shelf(productInfoDTO.getQuantity(), productInfoDTO.getShelfNumber(), productInfoDTO.isPrime(), productInfoDTO.getMaxCapacity());
            shelfRepository.save(shelf);
            Product product = new Product();
            setProductDetails(productInfoDTO, vendor, shelf, product);
        }catch(Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    private void setProductDetails(ProductInfoDTO productInfoDTO, Vendor vendor, Shelf shelf, Product product) {
        product.setName(productInfoDTO.getProductName().toLowerCase());
        product.setCategory(productInfoDTO.getCategory().toLowerCase());
        product.setVendor(vendor);
        product.setShelf(shelf);
        product.setPricePerUnit(productInfoDTO.getPricePerUnit());
        product.setSku(generateSKU(productInfoDTO.getProductName(), productInfoDTO.getCategory()).toLowerCase());
        productRepository.save(product);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void updateProduct(Long id, ProductInfoDTO productInfoDTO) {
        try {
            Product product = productRepository.getReferenceById(id);
            List<Integer> shelvesAvailable=shelfService.getAvailableShelves();
            int currentShelf= product.getShelf().getShelfNumber();
            if(currentShelf!=productInfoDTO.getShelfNumber()  && !shelvesAvailable.contains(productInfoDTO.getShelfNumber()))
                throw new BadRequestException("Shelf number " + productInfoDTO.getShelfNumber() + " is not available pick from " + shelvesAvailable);
            if(productInfoDTO.getQuantity()> product.getShelf().getMaxCapacity())
                throw new BadRequestException("Max Quantity for this product is "+product.getShelf().getMaxCapacity());
            Vendor vendor = vendorRepository.getReferenceById(productInfoDTO.getVendor().getId());
            Shelf shelf = product.getShelf();
            shelf.setShelfNumber(productInfoDTO.getShelfNumber());
            shelf.setPrime(productInfoDTO.isPrime());
            shelf.setQuantity(productInfoDTO.getQuantity());
            shelf.setMaxCapacity(productInfoDTO.getMaxCapacity());
            shelfRepository.save(shelf);
            setProductDetails(productInfoDTO, vendor, shelf, product);
        }catch(Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(product==null) {
            logger.error("Product not found");
            throw new ResourceNotFoundException("Product not found with product id: " + productId);
        }
        logger.info("Product deleted successfully with product id : "+ productId);
        productRepository.delete(product);
    }
}
