package de.telran.pizzaProject.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.pizzaProject.configTest.SpringSecurityTestConfig;
import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.entity.Pizza;
import de.telran.pizzaProject.repository.CafeRepository;
import de.telran.pizzaProject.repository.PizzaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityTestConfig.class
)
@AutoConfigureMockMvc
public class PizzaRestControllerTest {

    @MockBean
    PizzaRepository pizzaRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithUserDetails("admin")
    public void testGetAllPizzas() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setName("Test pizza");
        Mockito.when(pizzaRepository.findAll()).thenReturn(List.of(pizza));
        mockMvc.perform(get("/api/pizzas")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test pizza")));
    }

    private String asJsonString(Pizza pizza) {
        try {
            return new ObjectMapper().writeValueAsString(pizza);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithUserDetails("admin")
    //@WithUserDetails("user")
    //@WithAnonymousUser
    public void updatePizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setId("1");
        pizza.setName("TestPizza");

        Pizza pizza1 = new Pizza();
        pizza1.setId("12");
        pizza1.setName("TestPizza1");

        Mockito.when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza1);

        mockMvc.perform(put("api/pizza")
                        .with(csrf())
                        .content(asJsonString(pizza))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("TestPizza"));
    }

    @Test
    @WithUserDetails("admin")
    //@WithUserDetails("user")
    //@WithAnonymousUser
    public void deletePizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setId("123");
        pizza.setName("TestPizza");
        Mockito.when(pizzaRepository.findById(anyString()))
                .thenReturn(Optional.of(pizza));

        pizzaRepository.delete(pizza);

        Mockito.verify(pizzaRepository, Mockito.times(1)).delete(pizza);
    }

}

