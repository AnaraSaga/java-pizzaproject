package de.telran.pizzaProject.repositoryTest;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.repository.CafeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CafeRepositoryTest {
    @Autowired
    CafeRepository cafeRepository;

    public void testCreateReadDelete() {
        Cafe cafe = new Cafe();
        cafe.setName("TestCafe");

        cafeRepository.save(cafe);

        Iterable<Cafe> cafes = cafeRepository.findAll();
        Assertions.assertEquals(cafes.iterator().next().getName(), "TestCafe");

        cafeRepository.deleteAll();
        Assertions.assertFalse(cafeRepository.findAll().iterator().hasNext());
    }
}
