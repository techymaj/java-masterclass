public class Main {

    public static void main(String[] args) {

        BankAccount companyAccount = new BankAccount(10_000);

        Thread t1 = new Thread(() -> companyAccount.withdraw(2500));
        Thread t2 = new Thread(() -> companyAccount.deposit(5000));
        Thread t3 = new Thread(() -> companyAccount.withdraw(2500));
        Thread t4 = new Thread(() -> companyAccount.withdraw(5000));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final balance: " + companyAccount.getBalance());
    }
}
