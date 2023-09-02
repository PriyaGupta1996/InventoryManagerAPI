package com.craftdemo.inventorymanager.repository;

import com.craftdemo.inventorymanager.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    Shelf findByShelfNumber(int shelfNumber);
}
