package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PizzaController {

    private final PizzaRepository repository;

    @Autowired
    public PizzaController(PizzaRepository repository) {
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
