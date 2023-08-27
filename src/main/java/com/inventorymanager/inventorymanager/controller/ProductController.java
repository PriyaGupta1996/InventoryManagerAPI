package com.inventorymanager.inventorymanager.controller;

import com.inventorymanager.inventorymanager.dto.ProductDTO;
import com.inventorymanager.inventorymanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProductsWithVendorAndShelfInfo() {
        return productService.getAllProductsWithVendorAndShelfInfo();
    }
}
