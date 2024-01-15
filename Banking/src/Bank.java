import java.util.ArrayList;

public class Bank {
    // write code here
    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    public boolean addBranch(String name) {
        // if added successfully,
        if (findBranch(name) == null) {
            branches.add(new Branch(name));
            return true;
        }

        return false;
    }

    public boolean addCustomer(String name, String customer, double initTransaction) {
        // if added successfully
        var branch = findBranch(name);

        if (branch != null) {
            for (var cus : branch.getCustomers()) {
                if (cus.getName().equals(customer)) {
                    return false;
                }
            }
            branch.newCustomer(customer, initTransaction);
            return true;
        }

        return false;
    }

    public boolean addCustomerTransaction(String name, String customer, double transaction) {
        // if added successfully
        var branch = findBranch(name);

        if (branch != null) {
            return branch.addCustomerTransaction(customer, transaction);
        }

        return false;
    }

    private Branch findBranch(String name) {
        // return Branch
        for (var branch : branches) {
            if (branch.getName().equals(name)) {
                return branch;
            }
        }

        return null;
    }

    public boolean listCustomers(String name, boolean printTransactions) {
        // if branch exists
        Branch branch = findBranch(name);

        if (branch != null) {
            System.out.println("Customer details for branch " + name);
            var i = 0;
            for (var customer : branch.getCustomers()) {
                System.out.println("Customer: " + customer.getName() + "[" + (i + 1) + "]");
                // transactions
                if (printTransactions) {
                    System.out.println("Transactions");
                    var j = 0;
                    for (var transaction : customer.getTransactions()) {
                        System.out.println("[" + (j + 1) +"]" + " Amount " + transaction);
                        j++;
                    }
                }
                i++;
            }

            return true;
        }

        return false;
    }
}