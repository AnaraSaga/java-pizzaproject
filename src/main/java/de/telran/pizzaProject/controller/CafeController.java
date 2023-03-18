package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.service.CafeService;
import de.telran.pizzaProject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CafeController {
    private final CafeService cafeService;
    private final PizzaService pizzaService;

    @Autowired
    public CafeController(CafeService cafeService, PizzaService pizzaService) {

        this.cafeService = cafeService;
        this.pizzaService = pizzaService;
    }

    @PostMapping("/addCafe")
    public String addCafe(@Valid Cafe cafe, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "cafe";
        }
        cafeService.saveCafe(cafe);
        return "redirect:/cafes";
    }

    @GetMapping("/cafes")
    public String cafeTable(Model model) {
        model.addAttribute("cafes", cafeService.getAllCafes());
        return "cafes";
    }

    @GetMapping("/addCafe")  //for btn submit
    public String addCafe(Model model) {
        Cafe cafe = new Cafe();
        model.addAttribute("cafe", cafe);
        return "cafe";
    }

    @GetMapping("/editCafe/{id}")
    public String editCafe(@PathVariable String id, Model model) {
        Cafe cafe = cafeService.getCafeById(id);
        model.addAttribute("cafe", cafe);
        return "cafe";
    }

    @GetMapping("/deleteCafe/{id}")
    public String deleteCafe(@PathVariable String id, Model model) {
        cafeService.deleteCafeById(id);
        model.addAttribute("cafe", cafeService.getAllCafes());
        return "redirect:/cafes";
    }

}
