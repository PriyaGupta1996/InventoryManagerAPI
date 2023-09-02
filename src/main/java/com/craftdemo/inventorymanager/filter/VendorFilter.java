package com.craftdemo.inventorymanager.filter;

import com.craftdemo.inventorymanager.model.Product;
import com.craftdemo.inventorymanager.model.Vendor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class VendorFilter implements Specification<Product> {
    private final Vendor vendor;

    public VendorFilter(Vendor vendor) {
        this.vendor = vendor;
    }
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("vendor"), vendor);
    }
}

