package com.inventorymanager.inventorymanager.filter;

import com.inventorymanager.inventorymanager.model.Product;
import com.inventorymanager.inventorymanager.model.Vendor;
import jakarta.persistence.criteria.*;
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

