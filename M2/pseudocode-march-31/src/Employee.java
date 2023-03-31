public abstract class Employee {
    private double hourlyPay;
    private double workedHours;
    private String position;
    private int idNUmber;
    private String name;

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getIdNUmber() {
        return idNUmber;
    }

    public void setIdNUmber(int idNUmber) {
        this.idNUmber = idNUmber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void paySalary() {
        System.out.println("Employee's salary is: $" + "%.2f" + (hourlyPay * workedHours));
    }
    abstract void doJob();

    public String toString() {
        return "Employee name: " + name + " employee position: " + position + " employee ID number " + idNUmber;
    }
}