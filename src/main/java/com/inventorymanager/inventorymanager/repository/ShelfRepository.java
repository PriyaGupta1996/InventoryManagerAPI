package com.inventorymanager.inventorymanager.repository;

import com.inventorymanager.inventorymanager.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    Shelf findByShelfNumber(int shelfNumber);
}
