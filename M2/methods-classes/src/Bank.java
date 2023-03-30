public class Bank {
    private String name;
    private int[] accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getAccounts() {
        return accounts;
    }

    public void setAccounts(int[] accounts) {
        this.accounts = accounts;
    }

    public int[] addAccount(int account){
        return accounts;
    }
    public int[] removeAccount(int account) {
        return accounts;
    }
    
    public double calculateTotalBalance() {
        double sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            sum += accounts[i];
        }
        return sum;
    }
}
