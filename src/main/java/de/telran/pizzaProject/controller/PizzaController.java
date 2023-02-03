package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.CommonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PizzaController {

    private final CommonRepository<Pizza> repository;

    public PizzaController(CommonRepository<Pizza> repository) {
        this.repository = repository;
    }

    @GetMapping("/pizzas")
    public ResponseEntity <Iterable <Pizza>> getAllPizzas(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/pizza")
    public ResponseEntity<Pizza> addPizza (@RequestBody Pizza pizza){
        return new ResponseEntity<>(repository.save(pizza), HttpStatus.CREATED);
    }
}
