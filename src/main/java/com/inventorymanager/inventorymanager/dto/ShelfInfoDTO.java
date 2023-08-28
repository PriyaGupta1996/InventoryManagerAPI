package com.inventorymanager.inventorymanager.dto;

import lombok.Data;

@Data
public class ShelfInfoDTO {
    private int shelfNumber;
    private int maxCapacity;
    private int quantity;
    private boolean isPrime;

    public ShelfInfoDTO(int shelfNumber, int maxCapacity, int quantity, boolean isPrime) {
        this.shelfNumber = shelfNumber;
        this.maxCapacity = maxCapacity;
        this.quantity = quantity;
        this.isPrime = isPrime;
    }
}
