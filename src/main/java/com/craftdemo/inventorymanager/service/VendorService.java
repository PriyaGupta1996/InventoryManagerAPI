package com.craftdemo.inventorymanager.service;

import com.craftdemo.inventorymanager.dto.VendorDTO;
import com.craftdemo.inventorymanager.model.Vendor;

import java.util.List;

public interface VendorService {
     List<VendorDTO> getDistinctVendors();
}
