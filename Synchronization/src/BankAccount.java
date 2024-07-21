import java.util.concurrent.TimeUnit;

public class BankAccount {

    private double balance;
    private String name;
    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankAccount(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public void setName(String name) {
        synchronized (lockName) {
            this.name = name;
            System.out.println("Updated name to: " + this.name);
        }
    }

    public String getName() {
        return name;
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
        synchronized (lockBalance) {
            double original = balance;
            balance += amount;
            System.out.printf("Starting %.2f, Deposit: %.2f, new balance: %.2f\n",
                    original, amount, balance);
            addPromoDollars(amount);
        }
    }

    private void addPromoDollars(double amount) {
        if (amount >= 5_000) {
            synchronized (lockBalance) {
                System.out.println("Congratulations! You've earned $100 in promo dollars!");
                balance += 100;
            }
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
