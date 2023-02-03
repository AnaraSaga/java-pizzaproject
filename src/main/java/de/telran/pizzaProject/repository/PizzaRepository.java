package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PizzaRepository implements CommonRepository<Pizza> {

    private static final String SQL_INSERT = "INSERT INTO pizza (id, name, price, image, description) " +
            "VALUES (:id, :name, :price, :image, :description)";

    private static final String SQL_FIND_ALL = "SELECT * FROM pizza";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM pizza " +
            "WHERE id = :id";

    private static final String SQL_UPDATE = "UPDATE pizza SET name = :name, price = :price, " +
            "image = :image, description = :description WHERE id = :id";

    private static final String SQL_DELETE = "DELETE FROM pizza WHERE id =:id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PizzaRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Pizza> pizzaRowMapper = (ResultSet rs, int rowNum) -> {
        Pizza pizza = new Pizza();
        pizza.setId(rs.getString("id"));
        pizza.setName(rs.getNString("name"));
        pizza.setPrice(rs.getDouble("price"));
        pizza.setName(rs.getNString("image"));
        pizza.setDescription(rs.getNString("description"));
        return pizza;
    };


    @Override
    public Pizza save(Pizza entity) {
        Pizza pizza = findById(entity.getId());
        if (pizza != null) {
            return upsert(entity, SQL_UPDATE);
        }
        return upsert(entity, SQL_INSERT);
    }

    private Pizza upsert(Pizza pizza, String sql) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", pizza.getId());
        parameters.put("name", pizza.getName());
        parameters.put("price", pizza.getPrice());
        parameters.put("image", pizza.getImage());
        parameters.put("description", pizza.getDescription());
        jdbcTemplate.update(sql, parameters);
        return findById(pizza.getId());
    }

    @Override
    public Iterable<Pizza> save(Collection<Pizza> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Pizza entity) {
        Map<String, String> parameters = Collections.singletonMap("id", entity.getId());
        jdbcTemplate.update(SQL_DELETE, parameters);
    }

    @Override
    public Pizza findById(String id) {
        try {
            Map<String, String> parameters = Collections.singletonMap("id", id);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, parameters, pizzaRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Iterable<Pizza> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, pizzaRowMapper);
    }
}
