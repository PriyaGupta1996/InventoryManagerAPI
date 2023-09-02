package com.craftdemo.inventorymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShelfInfoDTO {
    private int shelfNumber;
    private int maxCapacity;
    private int quantity;
    private boolean isPrime;

}
