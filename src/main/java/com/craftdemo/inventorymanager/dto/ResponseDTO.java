package com.craftdemo.inventorymanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDTO {
    private String message;
    private int status;

}
