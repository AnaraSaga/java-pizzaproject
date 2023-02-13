package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Drink;
import de.telran.pizzaProject.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/drink/{id}")
    public ResponseEntity<?> getDrinkById(@PathVariable String id){
        Optional<Drink> optDrink = repository.findById(id);
        if (optDrink.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(optDrink.get(), HttpStatus.OK);
    }

    @GetMapping("/drink")
    public ResponseEntity<?> getDrinkByName(@RequestParam String name){
        Drink drink = repository.findByName(name);
        if (drink == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @RequestMapping(value = "/drink", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updateDrink(@RequestBody Drink drink) {
        return new ResponseEntity<>(repository.save(drink), HttpStatus.CREATED);
    }

    @DeleteMapping ("/drink/{id}")
    public ResponseEntity <?> deleteDrink (@PathVariable String id){
        Optional <Drink> drinkOpt = repository.findById(id);
        if (drinkOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        repository.delete(drinkOpt.get());
        return ResponseEntity.notFound().build();
    }

}
