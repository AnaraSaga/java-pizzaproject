//package de.telran.pizzaProject.ControllerTest;
//
//import ch.qos.logback.core.boolex.Matcher;
//import de.telran.pizzaProject.entity.Cafe;
//import de.telran.pizzaProject.repository.CafeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.stubbing.OngoingStubbing;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultHandler;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest (CafeRestControllerTest.class)
//public class CafeRestControllerTest {
//
//    @MockBean
//    CafeRepository cafeRepository;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void testGetAllCafes() throws Exception{
//        Cafe cafe = new Cafe();
//        cafe.setName("Test cafe");
//        mockMvc.perform(get("/api/cafes")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", Matcher.hasSize(1)))
//                .andExpect(jsonPath("$[0].name", Matcher.is("Test cafe")));
//
//
//    }
//
//
//}
