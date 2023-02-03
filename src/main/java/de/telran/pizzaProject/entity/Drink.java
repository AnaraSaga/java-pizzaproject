package de.telran.pizzaProject.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Drink {

    private String id;

    private String name;

    private double price;

    private String description;

    private String image;

    public Drink() {
        this.id = UUID.randomUUID().toString();
    }
}
