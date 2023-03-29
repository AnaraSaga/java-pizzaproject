package de.telran.pizzaProject.controller;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.entity.Role;
import de.telran.pizzaProject.entity.User;
import de.telran.pizzaProject.repository.RoleRepository;
import de.telran.pizzaProject.repository.UserRepository;
import de.telran.pizzaProject.service.CafeService;
import de.telran.pizzaProject.service.PizzaService;
import de.telran.pizzaProject.service.RoleService;
import de.telran.pizzaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private final CafeService cafeService;
    private final PizzaService pizzaService;

    private final RoleService roleService;

    private final UserService userService;

    @Autowired
    public MainController(CafeService cafeService, PizzaService pizzaService,
                          RoleService roleService, UserService userService) {
        this.cafeService = cafeService;
        this.pizzaService = pizzaService;
        this.roleService = roleService;
        this.userService = userService;
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
    public String admin(Model model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.addError(new FieldError(
                    "user", "password", "password must be equals!"));
            return "registration";
        }

        String username = user.getLogin();
        String password = user.getPassword();
        Role role = roleService.findByName("ROLE_USER");
        if (role == null) {
            role = roleService.save(new Role("ROLE_USER"));
        }
        userService.saveUser(user);

        //
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = new UsernamePasswordAuthenticationToken(
                username,
                password,
                List.of(new SimpleGrantedAuthority(role.getName()))
        );
        securityContext.setAuthentication(auth);
        return "redirect:/";
    }

}
