package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Drinks;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DrinksRepository implements CommonRepository <Drinks> {

    @Override
    public Drinks save(Drinks entity) {
        return null;
    }

    @Override
    public Iterable<Drinks> save(Collection<Drinks> entities) {
        return null;
    }

    @Override
    public void delete(Drinks entity) {

    }

    @Override
    public Drinks findById(String id) {
        return null;
    }

    @Override
    public Iterable<Drinks> findAll() {
        return null;
    }
}
