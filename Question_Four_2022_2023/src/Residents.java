import java.util.Scanner;

public class Residents extends Guest {

    static final int municipalFee = 30;
    double amountToPay;

    public void printDetails(String GuestName, String GuestID, double roomFees, int numOfDays) {
        this.amountToPay = roomFees * Residents.municipalFee * numOfDays;
        Guest guest = new Guest();
        guest.GuestName = GuestName;
        guest.GuestID = GuestID;
        guest.printDetails();
        System.out.println("The total amount to pay is $" + this.amountToPay);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Residents resident = new Residents();
        System.out.println("Enter the Guest Name: ");
        resident.GuestName = scanner.nextLine();
        System.out.println("Enter the Guest ID: ");
        resident.GuestID = scanner.nextLine();
        System.out.println("Enter the room fees for this resident: ");
        double roomFees = scanner.nextDouble();
        System.out.println("Enter the number of days the guest will stay: ");
        int numOfDays = scanner.nextInt();
        resident.printDetails(resident.GuestName, resident.GuestID, roomFees, numOfDays);
    }
}
