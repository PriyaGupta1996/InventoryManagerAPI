package com.craftdemo.inventorymanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VendorDTO {
    private Long id;
    private String link;
    private String name;
}
