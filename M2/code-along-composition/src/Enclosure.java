public class Enclosure {
    private String name;
    private Inhabitat resident;
    private double temparature;
    //region All getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inhabitat getResident() {
        return resident;
    }

    public void setResident(Inhabitat resident) {
        this.resident = resident;
    }

    public double getTemparature() {
        return temparature;
    }

    public void setTemparature(double temparature) {
        this.temparature = temparature;
    }
    //endregion
}
