package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByLogin(String login);

}
