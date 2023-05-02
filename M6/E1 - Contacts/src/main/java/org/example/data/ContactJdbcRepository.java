package org.example.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.example.models.Contact;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactJdbcRepository {
    private DataSource dataSource = initDataSource();

    private DataSource initDataSource() {
        MysqlDataSource result = new MysqlDataSource();

        result.setUrl("jdbc:mysql://localhost:3306/contacts");
        result.setUser("root");
        result.setPassword("top-secret-password");
        return result;
    }

    public List<Contact> findAll() {
        List<Contact> result = new ArrayList<>();

        final String sql = "select contactId, firstName, lastName, email, phone from contact;";

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setContactId(rs.getInt("contactId"));
                contact.setFirstName(rs.getString("firstName"));
                contact.setLastName(rs.getString("lastName"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                result.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Contact findById(int contactId) {
        final String sql = "select contactId, firstName, lastName, email, phone from contact where firstName = ? and lastName = ?;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, contactId);

            try(ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Contact contact = new Contact();
                    contact.setContactId(rs.getInt("contactId"));
                    contact.setFirstName(rs.getString("firstName"));
                    contact.setLastName(rs.getString("lastName"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getString("phone"));
                    return contact;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Contact findByName(String firstName, String lastName) {
        final String sql = "select contactId, firstName, lastName, email, phone from contact where firstName = ? and lastName = ?;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            try(ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Contact contact = new Contact();
                    contact.setContactId(rs.getInt("contactId"));
                    contact.setFirstName(rs.getString("firstName"));
                    contact.setLastName(rs.getString("lastName"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getString("phone"));
                    return contact;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Contact add(Contact contact) {
        final String sql = "insert into contact (firstName, lastName, email, phone) values (?, ?, ?, ?);";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getEmail());
            statement.setString(4, contact.getPhone());

            int rowInserted = statement.executeUpdate();
            if (rowInserted <= 0) {
                return null;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    contact.setContactId((keys.getInt(1)));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

    public boolean update(Contact contact) {
        final String sql = "update contact set " +
                "firstName = ?, " +
                "lastName = ?," +
                "email = ?," +
                "phone = ?" +
                "where contactId = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1,contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getEmail());
            statement.setString(4, contact.getPhone());
            statement.setInt(5, contact.getContactId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete (int contactId) {
        final String sql = "delete from contact where contactId = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, contactId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
