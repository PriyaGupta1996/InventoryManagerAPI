package com.inventorymanager.inventorymanager.service;

import com.inventorymanager.inventorymanager.dto.ProductInfoDTO;
import com.inventorymanager.inventorymanager.model.Product;
import com.inventorymanager.inventorymanager.model.Vendor;
import com.inventorymanager.inventorymanager.repository.ProductRepository;
import com.inventorymanager.inventorymanager.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.vendorRepository=vendorRepository;
    }

    private ProductInfoDTO mapProductToProductInfoDTO(Product product) {
        return new ProductInfoDTO(
                product.getSku(),
                product.getCategory(),
                product.getName(),
                product.getPricePerUnit(),
                product.getShelfId().getShelfNumber(),
                product.getShelfId().getMaxCapacity(),
                product.getShelfId().getStock(),
                product.getVendorId().getLink());
    }

    public List<ProductInfoDTO> getFilteredData(
            String category, String partialProductName, Long vendorId, Double minPrice, Double maxPrice
    ) {
        List<Product> products;
        Vendor vendorReference=null;
        if(vendorId!=null){
            System.out.println("I am inside"+vendorId);
            vendorReference  =vendorRepository.getReferenceById(vendorId);
            System.out.println(vendorReference);
        }


        if (category != null && partialProductName != null && vendorId != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByCategoryAndNameContainingAndVendorIdAndPricePerUnitBetween(
                    category, partialProductName, vendorReference, minPrice, maxPrice
            );
        } else if (category != null && partialProductName != null && vendorId != null) {
            products = productRepository.findByCategoryAndNameContainingAndVendorId(category, partialProductName, vendorReference);
        } else if (category != null && partialProductName != null) {
            products = productRepository.findByCategoryAndNameContaining(category, partialProductName);
        } else if (category != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByCategoryAndPricePerUnitBetween(category, minPrice, maxPrice);
        } else if (partialProductName != null && vendorId != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByNameContainingAndVendorIdAndPricePerUnitBetween(
                    partialProductName, vendorReference, minPrice, maxPrice
            );
        } else if (partialProductName != null && vendorId != null) {
            products = productRepository.findByNameContainingAndVendorId(partialProductName, vendorReference);
        } else if (vendorId != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByVendorIdAndPricePerUnitBetween(vendorReference, minPrice, maxPrice);
        } else if (category != null) {
            products = productRepository.findByCategory(category);
        } else if (partialProductName != null) {
            products = productRepository.findByNameContaining(partialProductName);
        } else if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPricePerUnitBetween(minPrice, maxPrice);
        }
        else if (vendorId!=null) {
            products = productRepository.findByVendorId(vendorReference);
        }else {
            products = productRepository.findAll();
        }

        return products.stream()
                .map(this::mapProductToProductInfoDTO)
                .collect(Collectors.toList());
    }



    public List<String> getAllCategories() {
        List<String> uniqueCategories = productRepository.findAll()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
        return uniqueCategories;
    }
}
