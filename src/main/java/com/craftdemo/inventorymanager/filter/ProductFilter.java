package com.craftdemo.inventorymanager.filter;

import com.craftdemo.inventorymanager.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductFilter implements Specification<Product> {
    private final String productNamePattern;

    public ProductFilter(String productNamePattern) {
        this.productNamePattern = productNamePattern;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("name"), "%" + productNamePattern + "%");
    }
}
