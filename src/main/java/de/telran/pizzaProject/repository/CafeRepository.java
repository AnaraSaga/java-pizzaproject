package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Cafe;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CafeRepository implements CommonRepository <Cafe> {
    @Override
    public Cafe save(Cafe entity) {
        return null;
    }

    @Override
    public Iterable<Cafe> save(Collection<Cafe> entities) {
        return null;
    }

    @Override
    public void delete(Cafe entity) {

    }

    @Override
    public Cafe findById(String id) {
        return null;
    }

    @Override
    public Iterable<Cafe> findAll() {
        return null;
    }
}
