package de.telran.pizzaProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue (generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    String name;

//    @ManyToMany
//    @JoinTable(name ="pizza_ingredient")
//    private List<Pizza> pizzas;


}
