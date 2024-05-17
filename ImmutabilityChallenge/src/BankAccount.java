public class BankAccount {
    private final String accountType;
    private int balance;

    public BankAccount(String accountType, int balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
