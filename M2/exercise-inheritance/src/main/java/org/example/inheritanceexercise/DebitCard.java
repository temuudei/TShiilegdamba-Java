package org.example.inheritanceexercise;

public class DebitCard extends Payment {
    private long routingNumber;
    private long accountNumber;
    private String bankName;
    private double processingFee;

    public DebitCard(int id, double amount, long routingNumber, long accountNumber, String bankName, double processingFee) {
        super(id, amount);
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.processingFee = processingFee;
    }
    public long getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(long routingNumber) {
        this.routingNumber = routingNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }
    @Override
    public boolean processPayment() {
        System.out.println("Direct debit processing fee: $" + processingFee);
        System.out.println("Sending transaction to bank: " + bankName);
        return true;
    }
    @Override
    public String toString() {
        return "Payment: " + super.getId() + " Amount: " + super.getAmount() + " Type: Debit card" + " Bank name: " + getBankName();
    }
}
