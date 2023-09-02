package com.craftdemo.inventorymanager.dto;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInfoDTO {

    private String sku;
    @NotBlank(message="Category is required")
    @Size(min = 1, max = 100, message = "Category must be between 1 and 100 characters")
    private String category;

    @NotBlank(message="Product Name is required")
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    private String productName;

    @Min(value = 1, message = "Price Per Unit must be greater than or equal to 1")
    @Max(value = 1000000, message = "Price Per Unit must be less than or equal to 1000000")
    @NotNull(message="Price Per Unit is required")
    private Double pricePerUnit;

    @Min(value = 1, message = "Shelf number must be greater than or equal to 1")
    @Max(value = 50, message = "Shelf number must be less than or equal to 50")
    @NotNull(message="Shelf Number Name is required")
    private int shelfNumber;

    @Min(value = 1, message = "Max capacity must be greater than or equal to 1")
    @Max(value = 10, message = "Max capacity must be less than or equal to 10")
    @NotNull(message="Max Capacity e is required")
    private int maxCapacity;

    @Positive(message = "Quantity must be positive")
    @NotNull(message="Quantity is required")
    private int quantity;

    private Long productId;

    @NotNull(message="IsPrime is required")
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
