package com.craftdemo.inventorymanager.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShelfInfoDTO {
    @Min(value = 1, message = "Shelf number must be greater than or equal to 1")
    @Max(value = 50, message = "Shelf number must be less than or equal to 50")
    private int shelfNumber;

    @Min(value = 1, message = "Max Capacity must be greater than or equal to 1")
    @Max(value = 10, message = "Max Capacity  must be less than or equal to 10")
    private int maxCapacity;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    private boolean isPrime;

}
