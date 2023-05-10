package org.example.data;

import org.example.models.Phone;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.util.List;

@Repository
public class PhoneJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;
    public PhoneJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate();
    }

    @Transactional
    public List<Phone> findAll() {
        final String sql = "select id, os, release_date, price, condition from Phone limit 1000;";
        return jdbcTemplate.query(sql, new PhoneMapper());
    }

    @Transactional
    public Phone findById(int id) {
        final String sql = "select id, os, release_date, price, condition from Phone where id = ?;";
        return jdbcTemplate.query(sql, new PhoneMapper(), id).stream().findFirst().orElse(null);
    }

    @Transactional
    public Phone add(Phone phone) {
        final String sql = "insert into Phone (os, release_date, price, condition) values (?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, phone.getPhoneOS());
            ps.setDate(2, Date.valueOf(phone.getReleaseDate()));
            ps.setBigDecimal(3, phone.getPhonePrice());
            ps.setBoolean(4, phone.isStillInGoodCondition());
            return ps;
        }, keyHolder);

        if (rowsAffected <=0) {
            return null;
        }

        phone.setPhoneId(keyHolder.getKey().intValue());
        return phone;
    }

    @Transactional
    public boolean update(Phone phone) {
        final String sql = "update Phone set "
                + "os = ?, "
                + "release_date = ?, "
                + "price = ?, "
                + "condition = ? "
                + "where id = ?;";

        return jdbcTemplate.update(sql,
                phone.getPhoneOS(),
                phone.getReleaseDate(),
                phone.getPhonePrice(),
                phone.isStillInGoodCondition(),
                phone.getPhoneId()) > 0;
    }

    public boolean deleteById(int id) {
        return jdbcTemplate.update("delete from Phone where id = ?;", id) > 0;
    }
}
