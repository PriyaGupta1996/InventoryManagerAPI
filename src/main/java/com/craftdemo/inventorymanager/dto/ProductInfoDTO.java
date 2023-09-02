package com.craftdemo.inventorymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VendorDetails {
        private Long id;
        private String name;
        private String link;
    }
}
