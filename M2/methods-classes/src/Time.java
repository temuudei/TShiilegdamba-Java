public class Time {
    private int hours;
    private double minutes;

    public Time(int hours, double minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public double addMinutes(double minutes) {
        return this.minutes + minutes;
    }
    public double subtractMinutes(double minutes) {
        return this.minutes - minutes;
    }
    
}
