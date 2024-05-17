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
            payments[i].workerName = remy.nextLine();
            payments[i].numDays = remy.nextInt(); // '/n' is not consumed when you press enter
            remy.nextLine(); // '/n' is consumed
            payments[i].printDetails();
        }
    }
}
