package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private int carId;
    private String carName;
    private BigDecimal carPrice;
    private LocalDate carRelease;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public LocalDate getCarRelease() {
        return carRelease;
    }

    public void setCarRelease(LocalDate carRelease) {
        this.carRelease = carRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId && Objects.equals(carName, car.carName) && Objects.equals(carPrice, car.carPrice) && Objects.equals(carRelease, car.carRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carName, carPrice, carRelease);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carName='" + carName + '\'' +
                ", carPrice=" + carPrice +
                ", carRelease=" + carRelease +
                '}';
    }
}
