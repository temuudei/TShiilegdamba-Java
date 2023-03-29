package aquarium;

public class AquariumFish {
    private String species;
    private String commonName;
    private double maxTemp;
    private double minTemp;
    private String diet;
    private boolean validInput;
    public String getSpecies() {
        return this.species;
    }

    public boolean isValidInput() {
        return this.validInput;
    }

    public void setValidInput(boolean validInput) {
        this.validInput = validInput;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public double getMaxTemp() {
        return this.maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return this.minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public String getDiet() {
        return this.diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public double getAverageTemp() {
        return (this.maxTemp + this.minTemp) / 2.0;
    }
}