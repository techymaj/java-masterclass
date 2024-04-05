import java.util.Scanner;
public class Payment {

    public int numDays;
    public final static int rate = 5;
    public String workerName;

    public void printDetails() {
        System.out.println(workerName + " " + numDays + " " + rate);
    }

    public static void main(String[] args) {

        Payment[] payments = new Payment[5];
        Scanner remy = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            payments[i] = new Payment();
            payments[i].workerName = remy.nextLine(); // '/n' is not consumed
            payments[i].numDays = remy.nextInt();
            remy.nextLine();
            payments[i].printDetails();
        }
    }
}
