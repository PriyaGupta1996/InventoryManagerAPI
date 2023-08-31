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
    private Long productId;
    private boolean isPrime;

    private VendorDetails vendor; // Grouped vendor details

    @Data
    public static class VendorDetails {
        private Long id;
        private String name;
        private String link;
    }

    public ProductInfoDTO(
            String sku,
            String category,
            String productName,
            Double pricePerUnit,
            int shelfNumber,
            int maxCapacity,
            int quantity,
            Long vendorId,
            String vendorName,
            String vendorLink,
            Long productId,
            boolean isPrime
    ) {
        this.sku = sku;
        this.category = category;
        this.productName = productName;
        this.pricePerUnit = pricePerUnit;
        this.shelfNumber = shelfNumber;
        this.maxCapacity = maxCapacity;
        this.quantity = quantity;

        VendorDetails vendorDetails = new VendorDetails();
        vendorDetails.id = vendorId;
        vendorDetails.name = vendorName;
        vendorDetails.link = vendorLink;

        this.vendor = vendorDetails;
        this.productId = productId;
        this.isPrime = isPrime;
    }

    // Constructors, getters, setters, and other methods


}
