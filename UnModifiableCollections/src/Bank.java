import java.util.Map;

public class Bank {
    int routingNumber;
    long lastTransactionID;
    Map<String, BankCustomer> customers;

    public Map<String, BankCustomer> getCustomers() {
        return customers;
    }

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {

    }

    void doTransaction(String id, BankAccount.AccountType type, double amount) {

    }
}
