import java.util.Scanner;

public class NonResidents extends Guest {

    int entranceFee;
    int amountToPay;
    int numOfDays;

    public NonResidents(int entranceFee, int numOfDays) {
        this.entranceFee = entranceFee;
        this.numOfDays = numOfDays;
    }

    public void printDetails(String GuestName, String GuestID) {
        this.amountToPay = this.entranceFee * numOfDays;
        Guest guest = new Guest();
        guest.GuestName = GuestName;
        guest.GuestID = GuestID;
        guest.printDetails();
        System.out.println("The total amount to pay is $" + this.amountToPay);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Guest Name: ");
        String guestName = scanner.nextLine();
        System.out.println("Enter the Guest ID: ");
        String guestID = scanner.nextLine();
        System.out.println("Enter the number of days the guest will stay: ");
        int numOfDays = scanner.nextInt();
        NonResidents nonResident = new NonResidents(40, numOfDays);
        nonResident.printDetails(guestName, guestID);
    }
}
