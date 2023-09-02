package com.craftdemo.inventorymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class ProductFilterCriteria {

    private Optional<String> category;
    private Optional<String> productName;
    private Optional<Long> vendorId;
    private Optional<Double> minPrice;
    private Optional<Double> maxPrice;
}