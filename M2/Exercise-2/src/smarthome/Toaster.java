package smarthome;

public class Toaster implements Connectable{
    private  String name;
    private boolean isOn;
    public Toaster(String name) {
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
