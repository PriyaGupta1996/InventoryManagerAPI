package com.inventorymanager.inventorymanager.controller;

import com.inventorymanager.inventorymanager.model.Vendor;
import com.inventorymanager.inventorymanager.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    private final VendorService vendorService;
    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<Vendor> getDistinctVendors(){
        return vendorService.getDistinctVendors();
    }
}
