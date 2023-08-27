package com.inventorymanager.inventorymanager.service;

import com.inventorymanager.inventorymanager.dto.ProductDTO;
import com.inventorymanager.inventorymanager.model.Product;
import com.inventorymanager.inventorymanager.model.Shelf;
import com.inventorymanager.inventorymanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProductsWithVendorAndShelfInfo() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setSku(product.getSku());
            productDTO.setPricePerUnit(product.getPricePerUnit());
            productDTO.setCategory(product.getCategory());

            if (product.getVendor() != null) {
                productDTO.setVendorLink(product.getVendor().getLink());
            }

            List<Long> shelfIds = new ArrayList<>();
            int totalQuantity = 0; // Initialize total quantity
            for (Shelf shelf : product.getShelves()) {
                shelfIds.add(shelf.getId());
                totalQuantity += shelf.getStock(); // Add stock to total quantity
            }
            productDTO.setShelves(shelfIds);
            productDTO.setQuantity(totalQuantity); // Set total quantity

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}
