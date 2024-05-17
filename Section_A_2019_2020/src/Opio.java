import java.util.Scanner;

public class Opio {
    static final int CityCouncilTax = 1_000;
    int rent;

    static Scanner scanner = new Scanner(System.in);
}

class Timber extends Opio {
    int timberPieces;
    int timberPrice;
    int dailyIncome;

    public Timber(int timberPieces, int timberPrice) {
        this.timberPieces = timberPieces;
        this.timberPrice = timberPrice;
        this.dailyIncome = this.timberPieces * this.timberPrice;
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of timber pieces: ");
        int pieces = scanner.nextInt();
        System.out.println("Enter the timber price: ");
        int price = scanner.nextInt();
        Timber t = new Timber(pieces, price); // object creation
        int dailyIncome = t.dailyIncome;
        System.out.println("Daily Income for Timber: " + dailyIncome);
    }
}

class Saloon extends Opio {
    int dailyIncome;
    int servicePrice;
    int numOfCustomers;

    public Saloon(int servicePrice, int numOfCustomers) {
        this.servicePrice = servicePrice;
        this.numOfCustomers = numOfCustomers;
        this.dailyIncome = this.servicePrice * this.numOfCustomers;
    }

    public static void main(String[] args) {
        System.out.println("Enter the price of the service offered: ");
        int price = scanner.nextInt();
        System.out.println("Enter the number of customers worked on: ");
        int numOfCustomers = scanner.nextInt();
        Saloon s = new Saloon(price, numOfCustomers); // Object creation
        int dailyIncome = s.dailyIncome;
        System.out.println("Daily Income for the Unisex Saloon: " + dailyIncome);
    }
}
