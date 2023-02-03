package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collection;

@Repository
public class DrinkRepository implements CommonRepository <Drink> {

    private static final String SQL_INSERT = "INSERT INTO drink (id, name, price, image, description) " +
            "VALUES (:id, :name, :price, :image, :description)";

    private static final String SQL_FIND_ALL = "SELECT * FROM drink";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM drink " +
            "WHERE id = :id";

    private static final String SQL_UPDATE = "UPDATE drink SET name = :name, price = :price, " +
            "image = :image, description = :description WHERE id = :id";

    private static final String SQL_DELETE = "DELETE FROM drink WHERE id =:id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DrinkRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Drink> drinkRowMapper = (ResultSet rs, int rowNum) -> {
        Drink drink = new Drink();
        drink.setId(rs.getString("id"));
        drink.setName(rs.getNString("name"));
        drink.setPrice(rs.getDouble("price"));
        drink.setName(rs.getNString("image"));
        drink.setDescription(rs.getNString("description"));
        return drink;
    };
    @Override
    public Drink save(Drink entity) {
        return null;
    }

    @Override
    public Iterable<Drink> save(Collection<Drink> entities) {
        return null;
    }

    @Override
    public void delete(Drink entity) {

    }

    @Override
    public Drink findById(String id) {
        return null;
    }

    @Override
    public Iterable<Drink> findAll() {
        return null;
    }

    @Override
    public Drink findByName(String name) {
        return null;
    }
}
