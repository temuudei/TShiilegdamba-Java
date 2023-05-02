package org.example.dal;

import org.example.models.Pet;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetMapper implements RowMapper<Pet> {


    @Override
    public Pet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Pet pet = new Pet();
            // 4. Column values are for the current row.
            pet.setPetId(resultSet.getInt("pet_id"));
            pet.setName(resultSet.getString("name"));
            pet.setType(resultSet.getString("type"));
            return pet;
    }
}
