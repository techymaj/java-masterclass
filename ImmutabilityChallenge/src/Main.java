import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        BankAccount equity = new BankAccount("Savings", 20_000);

        BankCustomer jax = new BankCustomer("Jax", equity);
        BankCustomer hayley = new BankCustomer("Hayley", equity);

        System.out.println(hayley.accounts);
    }

}
