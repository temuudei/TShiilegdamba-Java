public class Manager extends Employee{
    /* 1. Has to extend the Employee class
       2. Has an array of Employee object
       3. Getter and setter
       4. Would have to implement the paySalary() method
       5. addEmployee() method which adds an employee to the array of Employee object and has an imput of Employee e.
       6. removeEmployee() method which removes an emoloyee from the array of Employee object based on their employee id number
       7. Would have to implement doJob() methods
     */


    Employee[] employees;

    /*
    public void addEmployee(Employee employee) {
        Employee[] newArray = new Employee[employees.length+1];
        newArray[employees.length] = employee;
        setEmployees(newArray);
    }

    public void removeEmployee(int )
     */

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    @Override
    public void doJob(){
        System.out.println("You're all fired!");
        setEmployees(new Employee[]{});
    }

    @Override
    public void paySalary(){
        for (Employee e : employees) {
            e.paySalary();
        }

    }

    public String toString() {
        return getName() + " is the " + getPosition() + ", has worked for "
                + getWorkedHours() + " and is in charge of " + employees.length + " employees.";
    }
}