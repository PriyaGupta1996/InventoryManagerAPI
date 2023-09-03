package com.craftdemo.inventorymanager.controller;

import com.craftdemo.inventorymanager.dto.VendorDTO;
import com.craftdemo.inventorymanager.model.Vendor;
import com.craftdemo.inventorymanager.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/vendors")
public class VendorController {
    private final VendorService vendorService;
    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<VendorDTO> getDistinctVendors(){
        return vendorService.getDistinctVendors();
    }
}
