package com.craftdemo.inventorymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min=1, max=200)
    private String name;

    @NotNull
    @Size(min=1, max=200)
    private String sku;

    @Column(name="price_per_unit")
    @NotNull
    @Min(value = 0)
    @Max(value = 1000000)
    private Double pricePerUnit;

    @NotNull
    @Size(min=1, max=200)
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
