package com.craftdemo.inventorymanager.service.impl;


import com.craftdemo.inventorymanager.model.Shelf;
import com.craftdemo.inventorymanager.repository.ShelfRepository;
import com.craftdemo.inventorymanager.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;

    @Value("${shelf.total-shelves}")
    private int totalShelves;
    @Value("${shelf.max-shelves-available-to-choose}")
    private int maxShelvesToChoose;

    @Autowired
    public ShelfServiceImpl(ShelfRepository shelfRepository){
        this.shelfRepository=shelfRepository;
    }
    public List<Integer> getAvailableShelves() {
        List<Shelf> usedShelves = shelfRepository.findAll();
        Set<Integer> usedShelfNumbers=new HashSet<>();
        ArrayList<Integer> availableShelves = new ArrayList<>();
        for(Shelf item: usedShelves){
            usedShelfNumbers.add(item.getShelfNumber());
        }
        int counterToChoose=0;
        for(int i=1;i<=totalShelves;i++){
            if(!usedShelfNumbers.contains(i)) {
                availableShelves.add(i);
                counterToChoose++;
                if (counterToChoose >= maxShelvesToChoose)
                    break;
            }
        }
        return availableShelves;
    }
}
