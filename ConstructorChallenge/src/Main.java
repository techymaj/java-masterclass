public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.describe();
        System.out.println(" ");

        Customer customer1 = new Customer("John", "john@john.com");
        customer1.describe();
        System.out.println(" ");

        Customer customer2 = new Customer("James", 23433, "james@james.com");
        customer2.describe();
    }
}
