package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.repository.CafeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CafeController {
    private final CafeRepository repository;

    public CafeController(CafeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cafes")
    public ResponseEntity<Iterable<Cafe>> getAllCafes () {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/cafe")
    public ResponseEntity<Cafe> addCafe(@RequestBody Cafe cafe) {
        return new ResponseEntity<>(repository.save(cafe), HttpStatus.CREATED);
    }
}
