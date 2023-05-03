package org.example.dal;

import org.example.models.Car;

import java.util.List;

public interface CarRepository {
    List<Car> findAll();
    Car findById(int carId);
    Car add(Car car);
    boolean update(Car car);
    boolean deleteById(int carId);
}
