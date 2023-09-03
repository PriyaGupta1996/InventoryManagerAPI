package com.craftdemo.inventorymanager.seed;

import com.craftdemo.inventorymanager.model.Product;
import com.craftdemo.inventorymanager.model.Shelf;
import com.craftdemo.inventorymanager.model.Vendor;
import com.craftdemo.inventorymanager.repository.ProductRepository;
import com.craftdemo.inventorymanager.repository.ShelfRepository;
import com.craftdemo.inventorymanager.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ShelfRepository shelfRepository;

    @Autowired
    public DataSeeder(ProductRepository productRepository, VendorRepository vendorRepository, ShelfRepository shelfRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.shelfRepository = shelfRepository;
    }

    private String generateSKU(String productName, String category) {
        return "sku_" + String.join("_",productName.toLowerCase().split(" "))+ "_" + String.join("_",category.toLowerCase().split(" "));
    }

    private Double generateRandomPrice() {
        return Double.valueOf(String.format("%.2f", 50 + new Random().nextDouble() * 450));
    }

    @Override
    public void run(String... args) throws Exception {
        List<Vendor> vendors = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Shelf> shelves = new ArrayList<>();

        // Seed vendors
        for (int i = 0; i < 10; i++) {
            String vendorName= "Vendor " +i;
            Vendor vendor = new Vendor(vendorName.toLowerCase(),"https://alibaba.com/?tab=all&InAS=y");
            vendors.add(vendor);
        }
        vendorRepository.saveAll(vendors);

        for(int i=0;i<10;i++){
            Shelf shelf;
            if(i==5)
                 shelf = new Shelf((new Random().nextInt(50) + 1), i+1,true,50);
            else
                 shelf = new Shelf((new Random().nextInt(50) + 1), i+1,false,50);

            shelves.add(shelf);
        }
        shelfRepository.saveAll(shelves);


        // Seed products and shelves
        for (int i = 0; i < 10; i++) {
            String productName = "Product " + i;
            String category = "Category " + i;
            Product product = new Product(vendors.get(i),productName.toLowerCase(), generateSKU(productName, category).toLowerCase(), generateRandomPrice(), category.toLowerCase(),shelves.get(i));
            products.add(product);
        }
        productRepository.saveAll(products);
    }
}
