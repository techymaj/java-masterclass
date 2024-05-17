public class SACCO {

    String regNo;
    int currentBalance;

    public SACCO(String regNo, int deposit) {
        this.regNo = regNo;
        this.currentBalance = deposit;
    }

    public void withdraw(int amount) {
        if (amount > this.currentBalance) {
            System.out.println("Account " + this.regNo + " Has Insufficient Funds");
            return;
        };
        this.currentBalance = this.currentBalance - amount;
        System.out.println("You have withdrawn: " + amount);
        System.out.println("Your current balance is: " + this.currentBalance);
    }

    public void deposit(int amount) {
        this.currentBalance = this.currentBalance + amount;
        System.out.println("You have deposited: " + amount);
        System.out.println("Your new balance is: " + this.currentBalance);
    }

    public void menu() {
        System.out.println("""
                Menu:
                (1) - Withdraw
                (2) - Deposit
                (3) - Quit
                """);
    }

    public void userInput(int option, int amountToTransact) {

        switch (option) {
            case 1 -> withdraw(amountToTransact);
            case 2 -> deposit(amountToTransact);
            case 3 -> System.exit(98);
        }
    }
}
