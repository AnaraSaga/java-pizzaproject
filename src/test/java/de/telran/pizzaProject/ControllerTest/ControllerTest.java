package de.telran.pizzaProject.ControllerTest;

import de.telran.pizzaProject.controller.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    CafeController cafeController;

    @Autowired
    CafeRestController cafeRestController;

    @Autowired
    MainController mainController;

    @Autowired
    PizzaController pizzaController;

    @Autowired
    PizzaRestController pizzaRestController;

    @Test
    public void cafeControllerTest() {
        Assertions.assertNotEquals(cafeController, null);
    }

    @Test
    public void cafeRestControllerTest() {
        Assertions.assertNotEquals(cafeRestController, null);
    }

    @Test
    public void mainControllerTest() {
        Assertions.assertNotEquals(mainController, null);
    }

    @Test
    public void pizzaControllerTest() {
        Assertions.assertNotEquals(pizzaController, null);
    }

    @Test
    public void pizzaRestControllerTest() {
        Assertions.assertNotEquals(pizzaRestController, null);
    }

}
