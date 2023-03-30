package smarthome;

public class TV implements Connectable{
    private  String name;
    private boolean isOn;
    public TV(String name) {
        this.name = name;
    }
    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    @Override
    public boolean getState() {
        return isOn;
    }

    @Override
    public String getName() {
        return name;
    }
}
