public class CarAttendant extends Employee{
    String[] listOfCars;
    private double tipsMade;

    public String[] getListOfCars() {
        return listOfCars;
    }

    public void setListOfCars(String[] listOfCars) {
        this.listOfCars = listOfCars;
    }

    public double getTipsMade() {
        return tipsMade;
    }

    public void setTipsMade(double tipsMade) {
        this.tipsMade = tipsMade;
    }
    public void giveTip(double received) {
        tipsMade += received;
    }
    @Override
    public void paySalary() {
        super.paySalary();
    }
    public void addCar(String car) {
        for (int i = 0; i < listOfCars.length; i++) {
            listOfCars[i] += car;
        }
    }
    public String[] removeCar(String car) {
        String[] newArray = new String[listOfCars.length - 1];
        for (int i = 0, j = 0; i < listOfCars.length; i++) {
            if (!listOfCars[i].equals(car)) {
                newArray[j] = listOfCars[i];
                j++;
            }
        }
        return newArray;
    }
    @Override
    void doJob() {
        System.out.println("In charge of customers' cars for valet parking");
    }
    /*
        1. Has to extend Employee object
        2. String array called listOfCars which holds which cars the attendant is in charge of.
        3. double tipsMade
        4. method addCar and method removeCar for listOfCars array
        5. method giveTip(double tipRecieved) to add a value to the tipsMade
        6. Getter and setter methods
        7. Override/implement the paySalary() method
        8. Would have to implement doJob() methods
     */


}
