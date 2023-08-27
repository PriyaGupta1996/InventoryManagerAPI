package com.inventorymanager.inventorymanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vendor vendor;

    @OneToMany(mappedBy = "product")
    private List<Shelf> shelves;

    public Product() {
    }

    private String name;
    private String sku;

    @Column(name="price_per_unit")
    private Double pricePerUnit;
    private String category;

    public Product(Vendor vendor, String name, String sku, Double pricePerUnit, String category) {
        this.vendor = vendor;
        this.name = name;
        this.sku = sku;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice_per_unit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
