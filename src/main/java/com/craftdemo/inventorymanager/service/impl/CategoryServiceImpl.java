package com.craftdemo.inventorymanager.service.impl;

import com.craftdemo.inventorymanager.model.Product;
import com.craftdemo.inventorymanager.repository.ProductRepository;
import com.craftdemo.inventorymanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<String> getCategories() {
       return productRepository.findAll()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
    }
}
