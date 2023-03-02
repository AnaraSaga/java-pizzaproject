package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.service.CafeService;
import de.telran.pizzaProject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private final CafeService cafeService;
    private final PizzaService pizzaService;

    @Autowired
    public MainController(CafeService cafeService, PizzaService pizzaService) {
        this.cafeService = cafeService;
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Map<Cafe, List<Pizza>> pizzaByCafes = new HashMap<>();
        for (Cafe cafe : cafeService.getAllCafes()) {
            pizzaByCafes.put(cafe, pizzaService.getPizzaByCafe(cafe));
        }
        model.addAttribute("map", pizzaByCafes);
        return "index";
    }

    @GetMapping("/login")
    public String admin(Model model){
        return "login";
    }

}
