package com.craftdemo.inventorymanager.repository;

import com.craftdemo.inventorymanager.dto.VendorDTO;
import com.craftdemo.inventorymanager.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<VendorDTO> findDistinctByNameNotNull();
}
