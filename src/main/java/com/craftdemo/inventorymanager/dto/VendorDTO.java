package com.craftdemo.inventorymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VendorDTO {
    private Long id;
    private String link;
    private String name;
}
