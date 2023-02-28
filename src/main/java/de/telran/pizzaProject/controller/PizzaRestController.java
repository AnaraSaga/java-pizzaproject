package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PizzaRestController {

    private final PizzaRepository repository;

    @Autowired
    public PizzaRestController(PizzaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pizzas")
    public ResponseEntity<Iterable<Pizza>> getAllPizzas() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

//    @GetMapping("/pizzas")
//    public ResponseEntity<Iterable<Pizza>> sortAllPizzasByPriceAsc() {
//        return new ResponseEntity<>(repository.findPizzasByOrderByPricePriceAsc(), HttpStatus.OK);
//    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<?> getPizzaById(@PathVariable String id) {
        Optional<Pizza> optPizza = repository.findById(id);
        if (optPizza.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
    }

    @GetMapping("/pizza")
    public ResponseEntity<?> getPizzaByName(@RequestParam String name) {
        Pizza pizza = repository.findByName(name);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @RequestMapping(value = "/pizza", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updatePizza(@RequestBody Pizza pizza) {
        return new ResponseEntity<>(repository.save(pizza), HttpStatus.CREATED);
    }

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable String id) {
        Optional<Pizza> pizzaOpt = repository.findById(id);
        if (pizzaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(pizzaOpt.get());
        return ResponseEntity.notFound().build();
    }

}
