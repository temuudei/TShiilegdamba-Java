package main.java;

public class Lake extends Pond{
    public Lake(String name, double temperature, String fish, boolean fountain) {
        super(name, temperature, fish, fountain);
    }

    protected void printLake() {
        this.name = "fish";
    }
}
