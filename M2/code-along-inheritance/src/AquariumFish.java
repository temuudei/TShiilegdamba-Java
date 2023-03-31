public class AquariumFish extends Fish {
    private String nickname;
    private String food;
    double minTemp;
    double maxTemp;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
    @Override
    public String toString() {
        return "This aquarium fish has the nickname: " + nickname + " it is of species: " + getScientificName();
    }
}
