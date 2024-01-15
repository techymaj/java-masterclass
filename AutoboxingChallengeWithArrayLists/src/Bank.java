import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Customer> customers;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }

    public void addTransaction(Customer customer, ArrayList<Customer> transactions) {
        if (customers.contains(customer)) {
            transactions.add(customer);
        }
    }

    public void printStatement(Customer customer) {
        System.out.println(customer.getName() + customer.getTransactions());
    }
}
