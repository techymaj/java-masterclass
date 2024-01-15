import java.util.ArrayList;

public class Customer {
    // write code here
    private String name;
    private ArrayList<Double> transactions = new ArrayList<>();;

    public Customer(String name, double initTransaction) {
        this.name = name;
        this.transactions.add(initTransaction);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double transaction) {
        this.transactions.add(transaction);
    }
}