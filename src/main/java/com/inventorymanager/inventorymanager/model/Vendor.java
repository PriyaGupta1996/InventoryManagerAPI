package com.inventorymanager.inventorymanager.model;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.List;

@Getter
@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String link;

    public Vendor() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vendor(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
