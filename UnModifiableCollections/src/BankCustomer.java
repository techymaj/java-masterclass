import java.util.List;

public class BankCustomer {
    String name;
    int customerID;
    List<BankAccount> accounts;

    public String getName() {
        return name;
    }

    public int getCustomerID() {
        return customerID;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}
