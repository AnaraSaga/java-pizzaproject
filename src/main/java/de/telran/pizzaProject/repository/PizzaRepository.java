package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PizzaRepository extends CrudRepository<Pizza, String> {
        Pizza findByName(String name);

        List<Pizza> findByCafe (Cafe cafe);


}