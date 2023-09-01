package com.inventorymanager.inventorymanager.service;


import com.inventorymanager.inventorymanager.model.Shelf;
import com.inventorymanager.inventorymanager.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;
    private final int TOTAL_SHELVES=50;
    private final int MAX_SHELVES_AVAILABLE_TO_CHOOSE=10;
    @Autowired
    public ShelfServiceImpl(ShelfRepository shelfRepository){
        this.shelfRepository=shelfRepository;
    }
    public List<Integer> getAvailableShelves() {
        List<Shelf> usedShelves = shelfRepository.findAll();
        HashSet<Integer> usedShelfNumbers=new HashSet<>();
        ArrayList<Integer> availableShelves = new ArrayList<>();
        for(Shelf item: usedShelves){
            usedShelfNumbers.add(item.getShelfNumber());
        }
        int counterToChoose=0;
        for(int i=1;i<=TOTAL_SHELVES;i++){
            if(!usedShelfNumbers.contains(i)) {
                availableShelves.add(i);
                counterToChoose++;
                if (counterToChoose >= MAX_SHELVES_AVAILABLE_TO_CHOOSE)
                    break;
            }
        }
        return availableShelves;
    }
}
