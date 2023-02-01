package de.telran.pizzaProject.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Drinks {

    private String id;

    private String name;

    private double price;

    private int quantity;

    private String description;

    private String image;

    public Drinks(String id) {
        this.id = UUID.randomUUID().toString();
    }
}
