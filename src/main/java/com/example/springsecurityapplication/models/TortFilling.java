package com.example.springsecurityapplication.models;

import javax.persistence.*;
import java.util.List;

//Начинки для тортов
@Entity
@Table(name = "tortFilling")
public class TortFilling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="minWeight")
    private int minWeight;

    @OneToMany(mappedBy = "tortFilling")
    private List<Cart> cart;

    @OneToMany(mappedBy = "tortFilling")
    private List<Orders> order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public int getId() {
        return id;
    }
}
