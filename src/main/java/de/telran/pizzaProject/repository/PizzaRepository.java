package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Pizza;
import org.springframework.data.repository.CrudRepository;


public interface PizzaRepository extends CrudRepository<Pizza, String> {
        Pizza findByName(String name);

        //List <Pizza> findByCafe (Cafe cafe);


}