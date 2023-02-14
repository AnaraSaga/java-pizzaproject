package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CafeController {
    private final CafeRepository repository;

    @Autowired
    public CafeController(CafeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cafes")
    public ResponseEntity<Iterable<Cafe>> getAllCafes() {
        return new ResponseEntity<>(repository.findByOrderByName(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cafe", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updateCafe(@RequestBody Cafe cafe) {
        return new ResponseEntity<>(repository.save(cafe), HttpStatus.CREATED);
    }

    @GetMapping("/cafe/{id}")
    public ResponseEntity<?> getCafeById(@PathVariable String id){
        Optional<Cafe> optCafe = repository.findById(id);
        if (optCafe.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(optCafe.get(), HttpStatus.OK);
    }

    @GetMapping("/cafe")
    public ResponseEntity<?> getCafeByName(@RequestParam String name){
        Cafe cafe = repository.findByName(name);
        if (cafe == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(cafe, HttpStatus.OK);
    }

    @DeleteMapping ("/cafe/{id}")
    public ResponseEntity <?> deleteCafe (@PathVariable String id){
        Optional <Cafe> cafeOpt = repository.findById(id);
        if (cafeOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        repository.delete(cafeOpt.get());
        return ResponseEntity.notFound().build();
    }

}
