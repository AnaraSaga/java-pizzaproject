package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Drink;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DrinkRepository extends CrudRepository<Drink, String> {

    Drink findByName (String name);

    List<Drink> findByCafe (Cafe cafe);
}
