package org.example;

public class Person {
    private String fName;
    private String lName;
    private int age;
    private double salary;

    public Person(String fName, String lName, int age, double salary) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.salary = salary;
    }

    public Person() {
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
