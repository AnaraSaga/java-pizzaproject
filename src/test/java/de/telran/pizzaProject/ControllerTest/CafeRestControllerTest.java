package de.telran.pizzaProject.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.pizzaProject.configTest.SpringSecurityTestConfig;
import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.repository.CafeRepository;
import jdk.jfr.Category;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityTestConfig.class
)
@AutoConfigureMockMvc
public class CafeRestControllerTest {
    @MockBean
    CafeRepository cafeRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithUserDetails("admin")
    //@WithAnonymousUser
    public void testGetAllCafes() throws Exception {
        Cafe cafe = new Cafe();
        cafe.setName("Test cafe");
        Mockito.when(cafeRepository.findByOrderByName()).thenReturn(List.of(cafe));
        mockMvc.perform(get("/api/cafes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test cafe")));
    }

    private String asJsonString(Cafe cafe) {
        try {
            return new ObjectMapper().writeValueAsString(cafe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithUserDetails("admin")
    //@WithUserDetails("user")
    //@WithAnonymousUser
    public void updateCafe() throws Exception {
        Cafe cafe = new Cafe();
        cafe.setId("123");
        cafe.setName("TestCafe");

        Cafe cafe1 = new Cafe();
        cafe1.setId("456");
        cafe1.setName("TestCafe1");

        Mockito.when(cafeRepository.save(any(Cafe.class))).thenReturn(cafe1);

        mockMvc.perform(put("api/cafe")
                        .with(csrf())
                        .content(asJsonString(cafe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("TestCafe"));
    }

    @Test
    @WithUserDetails("admin")
    //@WithUserDetails("user")
    //@WithAnonymousUser
    public void deleteCafe() throws Exception {
        Cafe cafe = new Cafe();
        cafe.setId("12");
        cafe.setName("TestCafe");
        Mockito.when(cafeRepository.findById(anyString()))
                .thenReturn(Optional.of(cafe));

        cafeRepository.delete(cafe);

        Mockito.verify(cafeRepository, Mockito.times(1)).delete(cafe);
    }

}