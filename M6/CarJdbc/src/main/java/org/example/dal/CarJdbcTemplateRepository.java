package org.example.dal;

import org.example.models.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CarJdbcTemplateRepository implements CarRepository {
    private final JdbcTemplate jdbcTemplate;
    public CarJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        final String sql = "select car_id, car_name, car_price, car_release from Car;";
        try {
            return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
                Car car = new Car();
                car.setCarId(resultSet.getInt("car_id"));
                car.setCarName(resultSet.getString("car_name"));
                Date date = new Date();
                date = resultSet.getDate("car_release");
                car.setCarRelease(LocalDate.f(date.getTime()));
                car.setCarPrice(resultSet.getBigDecimal("car_price"));
                return car;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car findById(int carId) {
        return null;
    }

    @Override
    public Car add(Car car) {
        return null;
    }

    @Override
    public boolean update(Car car) {
        return false;
    }

    @Override
    public boolean deleteById(int carId) {
        return false;
    }
}
