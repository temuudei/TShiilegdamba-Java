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
        // 2. connection string is:
        // [db-tech]:[db-vendor]://[host]:[port]/[database-name]
        result.setUrl("jdbc:mysql://localhost:3306/contactdb");
        // 3. username
        result.setUser("root");
        // 4. password
        result.setPassword("top-secret-password");
        return result;
    }

    public List<Contact> findAll() {
        List<Contact> result = new ArrayList<>();

        final String sql = "select contact_id, first_name, last_name, email, phone from Contact;";
        try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
                 while (rs.next()) {
                     Contact contact = new Contact();
                     contact.setContactId(rs.getInt("contact_id"));
                     contact.setFirstName(rs.getString("first_name"));
                     contact.setLastName(rs.getString("last_name"));
                     contact.setPhone(rs.getString("phone"));
                     contact.setEmail(rs.getString("email"));
                     result.add(contact);
                 }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Contact findByName(String firstName, String lastName) {
        final String sql = "select contact_id, first_name, last_name, email, phone from Contact where first_name = ? and last_name = ?;";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setContactId(rs.getInt("contact_id"));
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
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
}
