package com.inventorymanager.inventorymanager.repository;

import com.inventorymanager.inventorymanager.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
