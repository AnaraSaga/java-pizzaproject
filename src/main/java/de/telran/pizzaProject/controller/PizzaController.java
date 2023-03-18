package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.service.CafeService;
import de.telran.pizzaProject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class PizzaController {

    @Value("${images.dir}")
    private String imagesDir;
    private final CafeService cafeService;
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(CafeService cafeService, PizzaService pizzaService) {
        this.cafeService = cafeService;
        this.pizzaService = pizzaService;
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(imagesDir + "/" + filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @PostMapping("/addPizza")
    public String addPizza(@Valid Pizza pizza,
                           BindingResult bindingResult,
                           @RequestParam("image") MultipartFile file,
                           Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cafes", cafeService.getAllCafes());
            return "pizza";
        }
        Pizza pizzaFromDb = pizzaService.getPizzaById(pizza.getId());
        if (file.isEmpty() && pizzaFromDb == null) {
            bindingResult.addError(new FieldError("pizza", "picture", "File is not loaded"));
            model.addAttribute("cafes", cafeService.getAllCafes());
            return "pizza";
        }
        if (pizzaFromDb != null) {
            pizza.setPicture(pizzaFromDb.getPicture());
        }
        pizzaService.savePizza(file, pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/pizzas")
    public String pizzaTable(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "pizzas";
    }

    @GetMapping("/addPizza")  //for btn add
    public String addPizza(Model model) {
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        model.addAttribute("cafes", cafeService.getAllCafes());  //shows all cafes in the list
        return "pizza";
    }

    @GetMapping("editPizza/{id}")
    public String editPizza(@PathVariable String id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id);
        model.addAttribute("pizza", pizza);
        model.addAttribute("cafes", cafeService.getAllCafes());
        return "pizza";
    }

    @GetMapping("/deletePizza/{id}")
    public String deletePizza(@PathVariable String id, Model model) {
        pizzaService.deletePizzaById(id);
        model.addAttribute("pizza", pizzaService.getAllPizzas());
        return "redirect:/pizzas";
    }


}
