public class Account extends Bank {
    public Account(int balance) {
        super(balance);
    }

    public static void main(String[] args) {
        Account myAccount = new Account(1_000_000);
        int balance = myAccount.getBalance();
        System.out.println(balance);
    }
}
