package com.inventorymanager.inventorymanager.controller;

import com.inventorymanager.inventorymanager.model.Shelf;
import com.inventorymanager.inventorymanager.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shelves")
public class ShelfController {
    private final ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @GetMapping
    public List<Integer> getAvailableShelves(){
        return shelfService.getAvailableShelves();
    }
}
