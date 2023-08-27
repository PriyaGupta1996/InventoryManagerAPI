package com.inventorymanager.inventorymanager.model;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@Table(name = "shelf")
public class Shelf {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private int stock;

    public Shelf(int stock, Product product) {
        this.stock = stock;
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public Shelf() {

    }


    public void setStock(int stock) {
        this.stock = stock;
    }

}
