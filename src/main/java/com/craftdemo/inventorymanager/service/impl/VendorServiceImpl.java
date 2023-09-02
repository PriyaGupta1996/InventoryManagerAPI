package com.craftdemo.inventorymanager.service.impl;

import com.craftdemo.inventorymanager.dto.VendorDTO;
import com.craftdemo.inventorymanager.model.Vendor;
import com.craftdemo.inventorymanager.repository.VendorRepository;
import com.craftdemo.inventorymanager.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<VendorDTO> getDistinctVendors() {
        return vendorRepository.findDistinctByNameNotNull();
    }
}
