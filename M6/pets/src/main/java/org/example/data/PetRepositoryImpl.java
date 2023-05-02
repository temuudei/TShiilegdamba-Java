package org.example.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.example.models.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
public class PetRepositoryImpl implements PetRepository {

    // 1. Dangerous initialization during construction
    private DataSource dataSource = initDataSource();

    private DataSource initDataSource() {
        MysqlDataSource result = new MysqlDataSource();
        // 2. connection string is:
        // [db-tech]:[db-vendor]://[host]:[port]/[database-name]
        result.setUrl("jdbc:mysql://localhost:3306/pets");
        // 3. username
        result.setUser("root");
        // 4. password
        result.setPassword("top-secret-password");
        return result;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> result = new ArrayList<>();

        final String sql = "select pet_id, `name`, `type` from pet;";

        try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Pet pet = new Pet();
                pet.setPetId(rs.getInt("pet_id"));
                pet.setName(rs.getString("name"));
                pet.setType(rs.getString("type"));
                result.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Pet findByName(String petName) {
        final String sql = "select * from pet where `name` = ?;";

        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, petName);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pet pet = new Pet();
                    pet.setPetId(rs.getInt("pet_id"));
                    pet.setName(rs.getString("name"));
                    pet.setType(rs.getString("type"));
                    return pet;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pet findById(int petName) {
        final String sql = "select pet_id, `name`, `type` from pet where pet_id = ?;";

        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, petName);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pet pet = new Pet();
                    pet.setPetId(rs.getInt("pet_id"));
                    pet.setName(rs.getString("name"));
                    pet.setType(rs.getString("type"));
                    return pet;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet (`name`, `type`) values (?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, pet.getName());
            st.setString(2, pet.getType());
            int rowsInserted = st.executeUpdate();
            if (rowsInserted <= 0) {
                return null;
            }

            try (ResultSet keys = st.getGeneratedKeys()) {
                if (keys.next()) {
                    pet.setPetId(keys.getInt(1));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    @Override
    public boolean update(Pet pet) {
        final String sql = "update pet set `name` = ?, `type` = ? where pet_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, pet.getName());
            st.setString(2, pet.getType());
            st.setInt(3, pet.getPetId());
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int petId) {
        final String sql = "delete from pet where pet_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, petId);
            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
