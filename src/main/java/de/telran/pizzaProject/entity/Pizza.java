package de.telran.pizzaProject.entity;

import lombok.Data;
import lombok.Generated;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Pizza {

    private String id;

    private String name;

    private double price;

    //private int quantity;

    private String description;

    private String image;


    public Pizza(String id) {
        this.id = UUID.randomUUID().toString();

    }
}
