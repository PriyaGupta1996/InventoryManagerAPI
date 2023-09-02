package com.inventorymanager.inventorymanager.dto;

import java.util.Optional;
import lombok.Data;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
public class ProductFilterCriteria {
    private Optional<String> category = Optional.empty();
    private Optional<String> productName = Optional.empty();
    private Optional<Long> vendorId = Optional.empty();
    private Optional<Double> minPrice = Optional.empty();
    private Optional<Double> maxPrice = Optional.empty();
}