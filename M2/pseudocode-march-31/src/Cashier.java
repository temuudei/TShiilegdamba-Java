public class Cashier extends Employee{
    /*
   1. Extend Employee object
   2. double totalCashInRegister that holds the total cash in the current register
   3. processTransaction() method which increases totalCashInRegister
   4. processWithdrawal() method which decreases totalCashInRegister
   5. Define getter and setter
   6. Implement/ Override paySalary.
   7. Would have to implement doJob() methods
)     */

    private double cashInRegister;

    public double getCashInRegister() {
        return cashInRegister;
    }

    public void setCashInRegister(double cashInRegister) {
        this.cashInRegister = cashInRegister;
    }

    public void processTransaction(double amount) {
        System.out.printf("Added %.2f to the cash register\n", amount);
        cashInRegister+=amount;
    }

    public void processWithdrawal() {
        System.out.println("Emptied the cash register");
        setCashInRegister(0);
    }

    @Override
    public void doJob() {
        if (getCashInRegister()>2) {
            System.out.println("Took $2 from the register!");
            setCashInRegister(cashInRegister-2);
        } else {
            System.out.println("Played on my phone");
        }
    }

    @Override
    public String toString(){
        return getName() + " is the " + getPosition() + ", has worked for "
                + getWorkedHours() + " and they now have "+cashInRegister+ " dollars in the register";
    }
}