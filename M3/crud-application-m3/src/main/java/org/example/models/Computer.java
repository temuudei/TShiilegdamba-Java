package org.example.models;

import java.util.Objects;

public class Computer {
    private String brandName;
    private String cpu;
    private String gpu;
    private String operatingSystem;
    private int releaseYear;
    private int id;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int serialNumber) {
        this.id = serialNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Double.compare(computer.price, price) == 0 && Objects.equals(brandName, computer.brandName) && Objects.equals(cpu, computer.cpu) && Objects.equals(gpu, computer.gpu) && Objects.equals(operatingSystem, computer.operatingSystem) && Objects.equals(releaseYear, computer.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandName, cpu, gpu, operatingSystem, releaseYear, price);
    }
}
