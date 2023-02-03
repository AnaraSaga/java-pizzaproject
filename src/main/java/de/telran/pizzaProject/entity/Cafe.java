package de.telran.pizzaProject.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Cafe {

    private String id;

    private String name;

    private String address;

    private String phoneNumber;

    public Cafe() {
        this.id = UUID.randomUUID().toString();
    }


}
