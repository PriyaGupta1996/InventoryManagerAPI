package com.inventorymanager.inventorymanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
@Data
@Getter
@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendorId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="shelf_id", referencedColumnName = "id")
    private Shelf shelfId;
    @Column(unique = true)
    private String name;
    private String sku;

    @Column(name="price_per_unit")
    private Double pricePerUnit;
    private String category;

    public Product() {}

    public Product(Vendor vendorId, String name, String sku, Double pricePerUnit, String category,Shelf shelfId) {
        this.vendorId = vendorId;
        this.name = name;
        this.sku = sku;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
        this.shelfId=shelfId;
    }
}
