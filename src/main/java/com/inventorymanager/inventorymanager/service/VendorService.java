package com.inventorymanager.inventorymanager.service;

import com.inventorymanager.inventorymanager.model.Vendor;

import java.util.List;

public interface VendorService {
     List<Vendor> getDistinctVendors();
}
