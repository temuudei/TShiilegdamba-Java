package org.example.data;

import org.example.models.Phone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneMapper implements RowMapper<Phone> {

    @Override
    public Phone mapRow(ResultSet resultSet, int i) throws SQLException {
        Phone phone = new Phone();
        phone.setPhoneId(resultSet.getInt("id"));
        phone.setPhoneOS(resultSet.getString("os"));
        phone.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        phone.setPhonePrice(resultSet.getBigDecimal("price"));
        if (resultSet.getInt("condition") == 1) {
            phone.setStillInGoodCondition(true);
        }
        else {
            phone.setStillInGoodCondition(false);
        }
        return phone;
    }
}


