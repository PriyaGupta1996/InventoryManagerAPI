package com.inventorymanager.inventorymanager.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String sku;
    private String pricePerUnit;
    private String category;
    private String vendorLink;
    private int shelfNumber;
    private int quantity;
}
