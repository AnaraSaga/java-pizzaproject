package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Drink;
import de.telran.pizzaProject.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DrinkController {

    private final DrinkRepository repository;

    @Autowired
    public DrinkController(DrinkRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/drinks")
    public ResponseEntity<Iterable <Drink>> getAllDrinks(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/drink")
    public ResponseEntity<Drink> addDrink (@RequestBody Drink drink){
        return new ResponseEntity<>(repository.save(drink), HttpStatus.CREATED);
    }


}
