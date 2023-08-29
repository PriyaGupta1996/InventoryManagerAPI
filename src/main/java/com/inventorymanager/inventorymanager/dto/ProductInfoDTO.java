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
    private int quantity;
    private String vendorLink;
    private Long vendorId;
    private boolean isPrime;
    private String vendorName;

    public ProductInfoDTO(String sku, String category, String productName, Double pricePerUnit, int shelfNumber, int maxCapacity, int quantity, String vendorLink,Long vendorId, boolean isPrime,String vendorName) {
        this.sku = sku;
        this.category = category;
        this.productName = productName;
        this.pricePerUnit = pricePerUnit;
        this.shelfNumber = shelfNumber;
        this.maxCapacity = maxCapacity;
        this.quantity = quantity;
        this.vendorLink = vendorLink;
        this.vendorId=vendorId;
        this.isPrime=isPrime;
        this.vendorName=vendorName;
    }


    // Constructors, getters, and setters
}
