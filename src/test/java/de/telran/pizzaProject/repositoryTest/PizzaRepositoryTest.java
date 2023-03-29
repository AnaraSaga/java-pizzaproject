package de.telran.pizzaProject.repositoryTest;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.CafeRepository;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PizzaRepositoryTest {

    @Autowired
    PizzaRepository pizzaRepository;

    public void testCreateReadDelete() {
        Pizza pizza = new Pizza();
        pizza.setName("TestPizza");

        pizzaRepository.save(pizza);

        Iterable<Pizza> pizzas = pizzaRepository.findAll();
        Assertions.assertEquals(pizzas.iterator().next().getName(), "TestPizza");

        pizzaRepository.deleteAll();
        Assertions.assertFalse(pizzaRepository.findAll().iterator().hasNext());
    }
}


