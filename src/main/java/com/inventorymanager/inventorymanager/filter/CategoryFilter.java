package com.inventorymanager.inventorymanager.filter;

import com.inventorymanager.inventorymanager.model.Product;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CategoryFilter implements Specification<Product> {
    private final String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get("category"), category);
    }
}