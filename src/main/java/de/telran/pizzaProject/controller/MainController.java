package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.CafeRepository;
import de.telran.pizzaProject.repository.DrinkRepository;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    //    @Value("${images.dir}")
//    private String imagesDir;
    private final CafeRepository cafeRepository;
    private final PizzaRepository pizzaRepository;
    private final DrinkRepository drinkRepository;

    @Autowired
    public MainController(CafeRepository cafeRepository,
                          PizzaRepository pizzaRepository,
                          DrinkRepository drinkRepository) {
        this.cafeRepository = cafeRepository;
        this.pizzaRepository = pizzaRepository;
        this.drinkRepository = drinkRepository;
    }

    @GetMapping("/pizzeria")
    public String index(Model model) {
        Map<Cafe, List<Pizza>> pizzaByCafes = new HashMap<>();  //created Map (pizza by cafe)
        for (Cafe cafe : cafeRepository.findAll()) {      // for each cafe
            pizzaByCafes.put(cafe, pizzaRepository.findByCafe(cafe));
        }
        model.addAttribute("map", pizzaByCafes);
        //model.addAttribute("imagesDir", imagesDir);
        return "index";

//        Map<Cafe, List<Drink>> drinkByCafes = new HashMap<>();
//        for (Cafe cafe : cafeRepository.findAll()) {
//            drinkByCafes.put(cafe, drinkRepository.findByCafe(cafe));  //
//        }
//        model.addAttribute("mapForDrink", drinkByCafes);
//        return "index";

    }
        @GetMapping("/admin")
        public String admin(Model model){
        Cafe cafe = new Cafe();
        model.addAttribute("cafe", cafe);
            return "index";
        }

        @PostMapping("/addCafe")
        public String addCafe (@RequestBody Cafe cafe, Model model){
        cafeRepository.save(cafe);
        return "index";
        }
}
