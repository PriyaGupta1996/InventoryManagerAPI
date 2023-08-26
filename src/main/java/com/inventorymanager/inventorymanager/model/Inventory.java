package com.inventorymanager.inventorymanager.model;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="shelf_id")
    private String shelfId;
    private String sku;
    private int stock;


    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

}
