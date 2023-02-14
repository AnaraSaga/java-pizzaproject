package de.telran.pizzaProject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Cafe {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private String address;

    private String phoneNumber;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "cafe")
    private List<Pizza> pizzas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cafe")
    private List<Drink> drinks;
}
