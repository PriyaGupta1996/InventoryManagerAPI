package com.inventorymanager.inventorymanager.dto;

import lombok.Data;

@Data
public class ProductInfoDTO {
    private String sku;
    private String category;
    private String productName;
    private Double pricePerUnit;
    private int shelfNumber;
    private int maxCapacity;

    public ProductInfoDTO(String sku, String category, String productName, Double pricePerUnit, int shelfNumber, int maxCapacity, int stock, String vendorLink) {
        this.sku = sku;
        this.category = category;
        this.productName = productName;
        this.pricePerUnit = pricePerUnit;
        this.shelfNumber = shelfNumber;
        this.maxCapacity = maxCapacity;
        this.stock = stock;
        this.vendorLink = vendorLink;
    }

    private int stock;
    private String vendorLink;

    // Constructors, getters, and setters
}
