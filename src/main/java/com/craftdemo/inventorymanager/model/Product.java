package com.craftdemo.inventorymanager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "vendor", referencedColumnName = "id")
    private Vendor vendor;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="shelf", referencedColumnName = "id")
    private Shelf shelf;
    @Column(unique = true)

    private String name;

    private String sku;

    @Column(name="price_per_unit")
    private Double pricePerUnit;
    private String category;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product() {}

    public Product(Vendor vendor, String name, String sku, Double pricePerUnit, String category,Shelf shelf) {
        this.vendor = vendor;
        this.name = name;
        this.sku = sku;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
        this.shelf=shelf;
    }
}
