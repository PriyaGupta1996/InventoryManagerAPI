package com.inventorymanager.inventorymanager.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String link;

    public Vendor() {}
    public Vendor(String name, String link) {
        this.name = name;
        this.link = link;
    }
}
