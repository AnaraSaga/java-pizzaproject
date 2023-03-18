package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Cafe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CafeRepository extends CrudRepository<Cafe, String> {
  List<Cafe> findByOrderByName ();
}
