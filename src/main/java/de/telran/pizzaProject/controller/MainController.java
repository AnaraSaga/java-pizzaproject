package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final CafeRepository repository;

    @Autowired
    public MainController (CafeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/pizzeria")
    public String index(Model model){
        model.addAttribute("cafes", repository.findAll());
        return "index";

     }


}
