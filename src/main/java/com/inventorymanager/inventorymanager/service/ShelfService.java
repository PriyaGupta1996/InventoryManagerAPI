package com.inventorymanager.inventorymanager.service;

import org.springframework.stereotype.Service;

import java.util.List;
public interface ShelfService {
    List<Integer> getAvailableShelves();
}
