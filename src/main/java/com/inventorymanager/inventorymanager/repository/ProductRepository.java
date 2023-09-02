package com.inventorymanager.inventorymanager.repository;

import com.inventorymanager.inventorymanager.dto.ProductInfoDTO;
import com.inventorymanager.inventorymanager.model.Product;
import com.inventorymanager.inventorymanager.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findByNameContaining(String partialProductName);

    List<Product> findByCategoryAndNameContaining(String category, String partialProductName);


    List<Product> findByCategoryAndPricePerUnitBetween(String category, Double minPrice, Double maxPrice);



    List<Product> findByPricePerUnitBetween(Double minPrice, Double maxPrice);

    List<Product> findByCategoryAndNameContainingAndVendorAndPricePerUnitBetween(String category, String partialProductName, Vendor vendor, Double minPrice, Double maxPrice);

    List<Product> findByCategoryAndNameContainingAndVendor(String category, String partialProductName, Vendor vendor);

    List<Product> findByNameContainingAndVendorAndPricePerUnitBetween(String partialProductName, Vendor vendor, Double minPrice, Double maxPrice);

    List<Product> findByNameContainingAndVendor(String partialProductName, Vendor vendor);

    List<Product> findByVendorAndPricePerUnitBetween(Vendor vendor, Double minPrice, Double maxPrice);

    List<Product> findByVendor(Vendor vendorReference);
}
