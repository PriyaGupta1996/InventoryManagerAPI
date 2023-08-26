package com.inventorymanager.inventorymanager.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name="vendor_id")
    private Long vendorId;
    private String name;
    private String sku;

    @Column(name="price_per_unit")
    private Double pricePerUnit;
    private String category;

    public void setVendor(Long vendorId) {
        this.vendorId = vendorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice_per_unit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
