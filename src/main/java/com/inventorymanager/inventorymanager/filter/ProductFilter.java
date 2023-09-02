package com.inventorymanager.inventorymanager.filter;
import jakarta.persistence.criteria.*;
import com.inventorymanager.inventorymanager.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductFilter implements Specification<Product> {
    private final String productNamePattern;

    public ProductFilter(String productNamePattern) {
        this.productNamePattern = productNamePattern;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("productName"), "%" + productNamePattern + "%");
    }
}
