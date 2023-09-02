package com.craftdemo.inventorymanager.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    List<String> getCategories();
}
