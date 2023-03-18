package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
    Role findByName(String name);
}
