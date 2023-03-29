package de.telran.pizzaProject.service;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PizzaService {
    @Value("${images.dir}")
    private String imagesDir;

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    //save Image to DB
    public void savePizza(MultipartFile file, Pizza pizza) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            pizza.setPicture(fileName);
            Path path = Paths.get(imagesDir + "/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }
        pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizzas() {
        return StreamSupport.stream(pizzaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Pizza getPizzaById(String id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        return pizza;
    }

    public List<Pizza> getPizzaByCafe(Cafe cafe) {
        return pizzaRepository.findByCafe(cafe);
    }

    public void deletePizzaById(String id) {
        pizzaRepository.deleteById(id);
    }

}
