package com.craftdemo.inventorymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shelf")
public class Shelf {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Min(value = 0)
    @Max(value = 100000) // Adjust based on your maximum capacity needs
    private int quantity;

    @Column(name="shelf_number",unique = true)
    @Min(value = 1, message = "Shelf number must be greater than or equal to 1")
    @Max(value = 50, message = "Shelf number must be less than or equal to 50")
    private int shelfNumber;

    @Column(name="is_prime")
    private boolean isPrime;

    @Column(name="max_capacity")
    private int maxCapacity;

    @CreationTimestamp
    private LocalDateTime
            createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Shelf() {}

    public Shelf(int quantity, int shelfNumber, boolean isPrime, int maxCapacity) {
        this.quantity = quantity;
        this.shelfNumber = shelfNumber;
        this.isPrime = isPrime;
        this.maxCapacity = maxCapacity;
    }
}
