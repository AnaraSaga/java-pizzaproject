package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CafeRestController {
    private final CafeRepository repository;

    @Autowired
    public CafeRestController(CafeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cafes")
    @PreAuthorize("authenicated")
    public ResponseEntity<?> getAllCafes() {
        return new ResponseEntity<>(repository.findByOrderByName(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cafe", method = {RequestMethod.POST, RequestMethod.PUT})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCafe(@RequestBody Cafe cafe) {
        return new ResponseEntity<>(repository.save(cafe), HttpStatus.CREATED);
    }

    @DeleteMapping("/cafe/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCafe(@PathVariable String id) {
        Optional<Cafe> cafeOpt = repository.findById(id);
        if (cafeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(cafeOpt.get());
        return ResponseEntity.noContent().build();
    }

}
