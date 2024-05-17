import java.util.Scanner;

public class Timber extends Opio {

    int dailyIncome;
    int timberPrice;
    int timberPieces;

    public Timber(int timberPrice, int timberPieces) {
        this.timberPrice = timberPrice;
        this.timberPieces = timberPieces;
    }

    public int getDailyIncome() {
        return this.timberPieces * this.timberPrice;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the timber price");
        int price = scanner.nextInt();
        System.out.println("Enter timber pieces");
        int pieces = scanner.nextInt();
        Timber timber01 = new Timber(price, pieces);
        int dailyIncome = timber01.getDailyIncome();
        System.out.println("Daily Income = " + dailyIncome);
    }
}
