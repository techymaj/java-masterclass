import java.util.concurrent.TimeUnit;

public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        try {
            System.out.println("Deposit - Talking to teller");
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // don't let other threads wait for the conversation to end
        synchronized (this) {
            double original = balance;
            balance += amount;
            System.out.printf("Starting %.2f, Deposit: %.2f, new balance: %.2f\n", original, amount, balance);
        }
    }

    public synchronized void withdraw(double amount) {
        try {
            TimeUnit.NANOSECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        double original = balance;
        if (amount <= balance) {
            balance -= amount;
            System.out.printf("Starting %.2f, Withdraw: %.2f, new balance: %.2f\n", original, amount, balance);
        } else {
            System.out.printf("Insufficient funds: %.2f\n", balance);
        }
    }
}
