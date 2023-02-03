package de.telran.pizzaProject.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Pizza {

    private String id;

    private String name;

    private double price;

    private String description;

    private String image;


    public Pizza() {
        this.id = UUID.randomUUID().toString();

    }
}
