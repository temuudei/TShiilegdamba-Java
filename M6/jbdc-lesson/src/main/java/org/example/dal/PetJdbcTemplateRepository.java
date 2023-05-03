package org.example.dal;

import org.example.models.Pet;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PetJdbcTemplateRepository implements PetRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public List<Pet> findAll() {

        final String sql = "select pet_id, `name`, `type` from pet;";

        try {
            return jdbcTemplate.query(sql, new PetMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pet findById(int petId) {
        final String sql = "select pet_id, `name`, `type` from pet where pet_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new PetMapper(), petId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet (`name`, `type`) values (?, ?);";
        KeyHolder keys = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pet.getName());
            ps.setString(2, pet.getType());
            return ps;
        }, keys);

        if (rowsAffected <=0) {
            return null;
        }
        pet.setPetId(keys.getKey().intValue());
        return pet;
    }

    @Override
    public boolean update(Pet pet) {

        final String sql = "update pet set "
                + "`name` = ?, "
                + "`type` = ? "
                + "where pet_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql, pet.getName(), pet.getType(), pet.getPetId());
        return rowsUpdated >0;
    }

    @Override
    public boolean deleteById(int petId) {
        final String sql = "delete from pet where pet_id = ?;";

        int rowsDeleted = 0;
        try {
            rowsDeleted = jdbcTemplate.update(sql, petId);
        } catch (DataAccessException e) {
            return false;
        }
        return rowsDeleted >0;
    }
}
