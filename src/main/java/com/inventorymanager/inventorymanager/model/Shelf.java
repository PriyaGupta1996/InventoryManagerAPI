package com.inventorymanager.inventorymanager.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Getter
@Entity
@Table(name = "shelf")
public class Shelf {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private int quantity;
    @Column(name="shelf_number")
    private int shelfNumber;
    @Column(name="is_prime")
    private boolean isPrime;
    @Column(name="max_capacity")
    private int maxCapacity;


    public Shelf() {}

    public Shelf(int quantity, int shelfNumber, boolean isPrime, int maxCapacity) {
        this.quantity = quantity;
        this.shelfNumber = shelfNumber;
        this.isPrime = isPrime;
        this.maxCapacity = maxCapacity;
    }
}
