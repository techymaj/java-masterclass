public class Customer {
    private String name;
    private double creditLimit;
    private String emailAddress;

    public Customer(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    public Customer() {
        this("Jax", 1000000, "email@email.com");
    }

    public Customer(String name, String emailAddress) {
        this(name, 100, emailAddress);
    }

    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void describe() {
        System.out.println("Customer: " + getName());
        System.out.println("Email: " + getEmailAddress());
        System.out.println("Limit: " + getCreditLimit());
    }
}
