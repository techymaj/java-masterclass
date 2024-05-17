import java.util.Scanner;

public class Paddock {

    int length;
    int width;
    int area;

    public Paddock(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public void areaOfSmallPaddock() {
        this.area = this.length * this.width;
        System.out.println("Small paddock area: " + this.area);
        System.out.println("To make 100 paddocks, total area is: " + this.area * 100);// area of each small paddock
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int width = scanner.nextInt();

        Paddock paddock = new Paddock(length, width);
        paddock.areaOfSmallPaddock();
    }
}
