import java.util.ArrayList;
import java.util.List;

public class BankCustomer {
    private String name;
    private static int id;
    List<BankAccount> accounts = new ArrayList<>();

    public BankCustomer(String name, BankAccount accounts) {
        this.name = name;
        this.accounts.add(accounts);
        BankCustomer.id++;
    }
}

class Sub extends BankCustomer {

    public Sub(String name, BankAccount accounts) {
        super(name, accounts);
    }
}
