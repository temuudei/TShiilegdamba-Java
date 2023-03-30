package smarthome;

public interface Connectable {
    public void turnOn();
    public void turnOff();
    public boolean getState();
    public String getName();
}
