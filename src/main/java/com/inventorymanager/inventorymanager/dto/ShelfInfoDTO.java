package com.inventorymanager.inventorymanager.dto;

import lombok.Data;

@Data
public class ShelfInfoDTO {
    private int shelfNumber;
    private int maxCapacity;
    private int stock;
    private boolean isPrime;

    public ShelfInfoDTO(int shelfNumber, int maxCapacity, int stock, boolean isPrime) {
        this.shelfNumber = shelfNumber;
        this.maxCapacity = maxCapacity;
        this.stock = stock;
        this.isPrime = isPrime;
    }
}
