package com.inventorymanager.inventorymanager.dto;

import lombok.Data;

@Data
public class VendorDTO {
    private String link;
    private String name;

    public VendorDTO(String link, String name) {
        this.link = link;
        this.name = name;
    }
}
