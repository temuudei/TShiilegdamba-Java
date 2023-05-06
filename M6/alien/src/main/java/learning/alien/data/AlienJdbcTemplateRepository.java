package learning.alien.data;

import learning.alien.models.Alien;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Repository

public class AlienJdbcTemplateRepository implements AlienRepository {
    private final JdbcTemplate jdbcTemplate;
    public AlienJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Alien> findAll() {
        final String sql = "select alien_id, alien_name, alien_price, alien_date from Alien;";
        try {
            return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
                Alien alien = new Alien();
                Date date = resultSet.getDate("alien_date");
                LocalDate alienDate = date.toLocalDate();

                alien.setAlienId(resultSet.getInt("alien_id"));
                alien.setAlienName(resultSet.getString("alien_name"));
                alien.setAlienPrice(resultSet.getBigDecimal("alien_price"));
                alien.setAlienDate(alienDate);
                return alien;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Alien findById(int alienId) {
        return null;
    }

    @Override
    public Alien add(Alien alien) {
        return null;
    }

    @Override
    public boolean update(Alien alien) {
        return false;
    }

    @Override
    public boolean deleteById(int alienId) {
        return false;
    }
}
