package org.example.dal;

import org.example.models.Contact;
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
public class ContactJdbcTemplateRepository implements ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    public ContactJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Contact> findAll() {
        final String sql = "select contact_id, first_name, last_name, email, phone from Contact;";
        try {
            return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
                Contact contact = new Contact();
                contact.setContactId(resultSet.getInt("contact_id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setEmail(resultSet.getString("email"));
                return contact;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Contact findById(int contactId) {
        final String sql = "select contact_id, first_name, last_name, email, phone from Contact where contact_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
                Contact contact = new Contact();
                contact.setContactId(resultSet.getInt("contact_id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setEmail(resultSet.getString("email"));
                return contact;
            }, contactId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Contact add(Contact contact) {
        final String sql = "insert into Contact (first_name, last_name, email, phone) values (?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = 0;
        try {
            rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, contact.getFirstName());
                ps.setString(2, contact.getLastName());
                ps.setString(3, contact.getEmail());
                ps.setString(4, contact.getPhone());
                return ps;
            }, keyHolder);
            if (rowsAffected <= 0) {
                return null;
            }
            contact.setContactId(keyHolder.getKey().intValue());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public boolean update(Contact contact) {
        final String sql = "update Contact set first_name = ?, last_name = ?, email = ?, phone = ? where contact_id = ?;";
        int rowsUpdated = 0;
        try {
            rowsUpdated = jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getContactId());
            return rowsUpdated > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int contactId) {
        final String sql = "delete from Contact where contact_id = ?;";
        int rowsDeleted = 0;
        try {
            rowsDeleted = jdbcTemplate.update(sql, contactId);
            return rowsDeleted > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
