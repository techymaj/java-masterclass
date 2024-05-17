import java.util.Map;

public class BankAccount {

    public enum AccountType {
        SAVINGS,
        FIXED
    }

    AccountType accountType;
    double balance;
    Map<Long, Transaction> transactions;

    Map<Long, Transaction> getTransactions() {
        return transactions;
    }
}
