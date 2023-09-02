package com.inventorymanager.inventorymanager.filter;

import com.inventorymanager.inventorymanager.model.Product;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;


public class PriceRangeFilter implements Specification<Product> {
    private final Double minPrice;
    private final Double maxPrice;

    public PriceRangeFilter(Double minPrice, Double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.between(root.get("pricePerUnit"), minPrice, maxPrice);
    }
}