public class Account {
    private String accountNumber;
    private double accountBalance;
    private String customerName;
    private String email;
    private String phoneNumber;

    public Account() {
        
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void deposit(double funds) {
        System.out.println("Previous balance: " + this.accountBalance);
        System.out.println("Deposited: " + funds);
        this.accountBalance += funds;
        System.out.println("New balance: " + this.accountBalance);
    }

    public void withdraw(double funds) {
        System.out.println("Processing withdrawal...");
        if (accountBalance < funds) {
            System.out.println("Insufficient funds");
            System.out.println("You are trying to withdraw: " + funds);
            System.out.println("Current balance: " + this.accountBalance);
        } else {
            this.accountBalance -= funds;
            System.out.println("Withdraw successful. Current balance: " + this.accountBalance);
        }
    }
}
