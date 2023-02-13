package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Cafe;
import org.springframework.data.repository.CrudRepository;


public interface CafeRepository extends CrudRepository<Cafe, String> {
  Cafe findByName(String name);
}
