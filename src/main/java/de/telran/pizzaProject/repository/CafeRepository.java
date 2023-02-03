package de.telran.pizzaProject.repository;

import de.telran.pizzaProject.entity.Cafe;
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
public class CafeRepository implements CommonRepository<Cafe> {

    private static final String SQL_INSERT = "INSERT INTO cafe (id, name, address, phoneNumber) " +
            "VALUES (:id, :name, :address, :phoneNumber)";

    private static final String SQL_FIND_ALL = "SELECT * FROM cafe";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM cafe " +
            "WHERE id = :id";

    private static final String SQL_FIND_BY_NAME = "SELECT * FROM cafe " +
            "WHERE name = :name";

    private static final String SQL_UPDATE = "UPDATE cafe SET id =:id, name = :name, address = :address, " +
            "phoneNumber = :phoneNumber WHERE id = :id";

    private static final String SQL_DELETE = "DELETE FROM cafe WHERE id =:id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CafeRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Cafe> cafeRowMapper = (ResultSet rs, int rowNum) -> {
        Cafe cafe = new Cafe();
        cafe.setId(rs.getNString("id"));
        cafe.setName(rs.getNString("name"));
        cafe.setAddress(rs.getNString("address"));
        cafe.setPhoneNumber(rs.getNString("phoneNumber"));
        return cafe;
    };

    @Override
    public Cafe save(Cafe entity) {
        Cafe cafe = findById(entity.getId());
        if (cafe != null) {
            return upsert(entity, SQL_UPDATE);
        }
        return upsert(entity, SQL_INSERT);
    }

    private Cafe upsert(Cafe cafe, String sql) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", cafe.getId());
        parameters.put("name", cafe.getName());
        parameters.put("address", cafe.getAddress());
        parameters.put("phoneNumber", cafe.getPhoneNumber());
        jdbcTemplate.update(sql, parameters);
        return findById(cafe.getId());
    }
    @Override
    public Iterable<Cafe> save(Collection<Cafe> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Cafe entity) {
        Map<String, String> parameters = Collections.singletonMap("id", entity.getId());
        jdbcTemplate.update(SQL_DELETE, parameters);
    }

    @Override
    public Cafe findById(String id) {
        try {
            Map<String, String> parameters = Collections.singletonMap("id", id);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, parameters, cafeRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Iterable<Cafe> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, cafeRowMapper);
    }

    @Override
    public Cafe findByName(String name) {
        try {
            Map<String, String> parameters = Collections.singletonMap("name", name);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, parameters, cafeRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }
}
