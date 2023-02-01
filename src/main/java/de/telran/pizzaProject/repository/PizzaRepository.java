package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Pizza;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class PizzaRepository implements CommonRepository<Pizza> {

    private static final String SQL_INSERT = "INSERT INTO pizza (id, name, price, image, description) " +
            "VALUES (:id, :name, :price, :image, :description)";

    private static final String SQL_FIND_ALL = "SELECT * FROM pizza";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM pizza " +
            "WHERE id = :id";

    private static final String

    @Override
    public Pizza save(Pizza entity) {
        return null;
    }

    @Override
    public Iterable<Pizza> save(Collection<Pizza> entities) {
        return null;
    }

    @Override
    public void delete(Pizza entity) {

    }

    @Override
    public Pizza findById(String id) {
        return null;
    }

    @Override
    public Iterable<Pizza> findAll() {
        return null;
    }
}
