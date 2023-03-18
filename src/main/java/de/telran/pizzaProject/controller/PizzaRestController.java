package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.CafeRepository;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PizzaRestController {

    private final PizzaRepository pizzaRepository;

    private final CafeRepository cafeRepository;

    @Autowired
    public PizzaRestController(PizzaRepository pizzaRepository, CafeRepository cafeRepository) {
        this.pizzaRepository = pizzaRepository;
        this.cafeRepository = cafeRepository;
    }

    @GetMapping("/pizzas")
    public ResponseEntity<?> getAllPizzas() {

        return new ResponseEntity<>(pizzaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<?> getPizzaById(@PathVariable String id) {
        Optional<Pizza> optPizza = pizzaRepository.findById(id);
        if (optPizza.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
    }

    @GetMapping("/pizza")
    public ResponseEntity<?> getPizzaByName(@RequestParam String name) {
        Pizza pizza = pizzaRepository.findByName(name);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @RequestMapping(value = "/pizza", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updatePizza(@RequestBody Pizza pizza) {
        return new ResponseEntity<>(pizzaRepository.save(pizza), HttpStatus.CREATED);
    }

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable String id) {
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);
        if (pizzaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pizzaRepository.delete(pizzaOpt.get());
        return ResponseEntity.noContent().build();
    }

}
