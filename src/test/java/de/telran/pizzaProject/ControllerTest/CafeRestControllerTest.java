package de.telran.pizzaProject.ControllerTest;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.repository.CafeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest (CafeRestControllerTest.class)
public class CafeRestControllerTest {

    @MockBean
    CafeRepository cafeRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllCafes() throws Exception{
        Cafe cafe = new Cafe();
        cafe.setName("Test cafe");
        Mockito.when(cafeRepository.findByOrderByName()).thenReturn(List.of(cafe));
        mockMvc.perform(get("/api/cafes"))


    }


}
