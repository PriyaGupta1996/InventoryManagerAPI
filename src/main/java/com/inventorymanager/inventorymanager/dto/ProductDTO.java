package com.inventorymanager.inventorymanager.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String sku;
    private Double pricePerUnit;
    private String category;
    private String vendorLink;
    private List<Long> shelves;
    private int quantity;
}
