import java.util.ArrayList;

public class Branch {
    // write code here
    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public boolean newCustomer(String name, double initTransaction) {
        // if added successfully
        if (findCustomer(name) != null) {
            return false;
        }

        customers.add(new Customer(name, initTransaction));
        return true;
    }

    public boolean addCustomerTransaction(String name, double transaction) {
        // if customer transaction added successfully
        var customer = findCustomer(name);

        if (customer == null) {
            return false;
        }

        customer.addTransaction(transaction);
        return true;
    }

    private Customer findCustomer(String name) {
        // if exists
        for (var customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }

        return null;
    }
}