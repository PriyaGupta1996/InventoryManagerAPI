package com.inventorymanager.inventorymanager.service;

import com.inventorymanager.inventorymanager.model.Vendor;
import com.inventorymanager.inventorymanager.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getDistinctVendors() {
        return vendorRepository.findDistinctByNameNotNull();
    }
}
