package com.inventorymanager.inventorymanager.service;

import com.inventorymanager.inventorymanager.dto.ProductInfoDTO;
import com.inventorymanager.inventorymanager.model.Product;
import com.inventorymanager.inventorymanager.model.Shelf;
import com.inventorymanager.inventorymanager.model.Vendor;
import com.inventorymanager.inventorymanager.repository.ProductRepository;
import com.inventorymanager.inventorymanager.repository.ShelfRepository;
import com.inventorymanager.inventorymanager.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ShelfRepository shelfRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, VendorRepository vendorRepository, ShelfRepository shelfRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.shelfRepository = shelfRepository;
    }

    private ProductInfoDTO mapProductToProductInfoDTO(Product product) {
        return new ProductInfoDTO(
                product.getSku(),
                product.getCategory(),
                product.getName(),
                product.getPricePerUnit(),
                product.getShelf().getShelfNumber(),
                product.getShelf().getMaxCapacity(),
                product.getShelf().getQuantity(),
                product.getVendor().getId(),
                product.getVendor().getName(),
                product.getVendor().getLink(),
                product.getId(),
                product.getShelf().isPrime()
        );
    }


    private String generateSKU(String productName, String category) {
        return "sku_" + String.join("_",productName.toLowerCase().split(" "))+ "_" + String.join("_",category.toLowerCase().split(" "));
    }


    private void setProductDetails(ProductInfoDTO productInfoDTO, Vendor vendor, Shelf shelf, Product product) {
        product.setName(productInfoDTO.getProductName().toLowerCase());
        product.setCategory(productInfoDTO.getCategory().toLowerCase());
        product.setVendor(vendor);
        product.setShelf(shelf);
        product.setPricePerUnit(productInfoDTO.getPricePerUnit());
        product.setSku(generateSKU(productInfoDTO.getProductName(),productInfoDTO.getCategory()).toLowerCase());
        productRepository.save(product);
    }


    @Override
    public List<ProductInfoDTO> getFilteredData(
            String category, String partialProductName, Long vendorId, Double minPrice, Double maxPrice
    ) {
        List<Product> products;
        Vendor vendorReference=null;
        if(vendorId!=null){
            vendorReference  =vendorRepository.getReferenceById(vendorId);
        }

        if (category != null && partialProductName != null && vendorId != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByCategoryAndNameContainingAndVendorAndPricePerUnitBetween(
                    category, partialProductName, vendorReference, minPrice, maxPrice
            );
        } else if (category != null && partialProductName != null && vendorId != null) {
            products = productRepository.findByCategoryAndNameContainingAndVendor(category, partialProductName, vendorReference);
        } else if (category != null && partialProductName != null) {
            products = productRepository.findByCategoryAndNameContaining(category, partialProductName);
        } else if (category != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByCategoryAndPricePerUnitBetween(category, minPrice, maxPrice);
        } else if (partialProductName != null && vendorId != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByNameContainingAndVendorAndPricePerUnitBetween(
                    partialProductName, vendorReference, minPrice, maxPrice
            );
        } else if (partialProductName != null && vendorId != null) {
            products = productRepository.findByNameContainingAndVendor(partialProductName, vendorReference);
        } else if (vendorId != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByVendorAndPricePerUnitBetween(vendorReference, minPrice, maxPrice);
        } else if (category != null) {
            products = productRepository.findByCategory(category);
        } else if (partialProductName != null) {
            products = productRepository.findByNameContaining(partialProductName);
        } else if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPricePerUnitBetween(minPrice, maxPrice);
        }
        else if (vendorId!=null) {
            products = productRepository.findByVendor(vendorReference);
        }else {
            products = productRepository.findAll();
        }

        return products.stream()
                .map(this::mapProductToProductInfoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findAll()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(ProductInfoDTO productInfoDTO) {
        Vendor vendor = vendorRepository.getReferenceById(productInfoDTO.getVendor().getId());
        Shelf shelf= new Shelf(productInfoDTO.getQuantity(), productInfoDTO.getShelfNumber(), productInfoDTO.isPrime(), productInfoDTO.getMaxCapacity());
        shelfRepository.save(shelf);
        Product product = new Product();
        setProductDetails(productInfoDTO, vendor, shelf, product);
    }

    @Override
    public void updateProduct(Long id, ProductInfoDTO productInfoDTO) {
        Product product = productRepository.getReferenceById(id);
        Vendor vendor = vendorRepository.getReferenceById(productInfoDTO.getVendor().getId());
        Shelf shelf= product.getShelf();
        shelf.setShelfNumber(productInfoDTO.getShelfNumber());
        shelf.setPrime(productInfoDTO.isPrime());
        shelf.setQuantity(productInfoDTO.getQuantity());
        shelf.setMaxCapacity(productInfoDTO.getMaxCapacity());
        shelfRepository.save(shelf);
        setProductDetails(productInfoDTO, vendor, shelf, product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productRepository.delete(product);
    }
}
