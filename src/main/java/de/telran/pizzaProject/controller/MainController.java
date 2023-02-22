package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.CafeRepository;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Value("${images.dir}")
    private String imagesDir;
    private final CafeRepository cafeRepository;
    private final PizzaRepository pizzaRepository;

    @Autowired
    public MainController(CafeRepository cafeRepository,
                          PizzaRepository pizzaRepository) {
        this.cafeRepository = cafeRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Map<Cafe, List<Pizza>> pizzaByCafes = new HashMap<>();  //created Map (pizza by cafe)
        for (Cafe cafe : cafeRepository.findAll()) {      // for each cafe
            pizzaByCafes.put(cafe, pizzaRepository.findByCafe(cafe));
        }
        model.addAttribute("map", pizzaByCafes);
        return "index";
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(imagesDir + "\\" + filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);

    }

    @GetMapping("/cafes")
    public String admin(Model model) {
        model.addAttribute("cafes", cafeRepository.findAll());
        return "cafes";
    }


    @PostMapping("/addCafe")
    public String addCafe(Cafe cafe, Model model) {
        cafeRepository.save(cafe);
        return "redirect:/cafes";
    }

    @GetMapping("/addCafe")  //for btn submit
    public String addCafe (Model model){
        Cafe cafe = new Cafe();
        model.addAttribute("cafe", cafe);
        return "add_cafe";
    }

    @GetMapping("/editCafe/{id}") // for btn edit -edit cafe by id
    public String editCafe(@PathVariable String id, Model model){  // find id in path
        Cafe cafe = cafeRepository.findById(id).get();
        model.addAttribute("cafe", cafe);
        return "add_cafe";
    }

    @GetMapping("/deleteCafe/{id}") // for btn delete -delete cafe by id
    public String deleteCafe(@PathVariable String id, Model model){  // find id in path
        cafeRepository.deleteById(id);
        model.addAttribute("cafe", cafeRepository.findAll());
        return "redirect:/cafes";
    }

    // to add new pizza
    @PostMapping("/addPizza")
    public String addPizza (Pizza pizza, Model model) {
        pizzaRepository.save(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/addPizza")  //for btn submit
    public String addPizza (Model model){
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "add_pizza";
    }

    @GetMapping("/editPizza/{id}") // for btn edit -edit cafe by id
    public String editPizza(@PathVariable String id, Model model){  // find id in path
        Pizza pizza = pizzaRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "add_pizza";
    }

    @GetMapping("/deletePizza/{id}") // for btn delete -delete cafe by id
    public String deletePizza(@PathVariable String id, Model model){  // find id in path
        pizzaRepository.deleteById(id);
        model.addAttribute("pizza", pizzaRepository.findAll());
        return "redirect:/pizzas";
    }

}
