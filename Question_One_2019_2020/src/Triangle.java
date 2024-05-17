import java.util.Scanner;

public class Triangle {

    int a;
    int b;
    int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    static Scanner scanner = new Scanner(System.in);

    public void equilateral() {
        if (this.a == this.b && this.b == c) {
            System.out.println("Equilateral triangle");
        }
    }

    public void isosceles() {
        if (this.a == this.b && this.b != this.c ||
        this.a == this.c && this.c != this.b) {
            System.out.println("Isosceles triangle");
        }
    }

    public void scalene() {
        int a = this.a;
        int b = this.b;
        int c = this.c;

        // a2 = b2 + c2
        int exp = (int) (Math.pow(b, 2) + Math.pow(c, 2));
        int aSquared = (int) Math.pow(a, 2);

        if (((a != b) && (b != c) && (a != c)) && !(aSquared == exp)) {
            System.out.println("Scalene triangle");
        }
    }

    public void rightAngled() {
        int a = this.a;
        int b = this.b;
        int c = this.c;

        // a2 = b2 + c2
        int exp = (int) (Math.pow(b, 2) + Math.pow(c, 2));
        a = (int) Math.pow(a, 2);

        if (a == exp) {
            System.out.println("Right angled triangle");
        }
    }

    public static void main(String[] args) {
        System.out.println("side a: ");
        int a = scanner.nextInt();
        System.out.println("side b: ");
        int b = scanner.nextInt();
        System.out.println("side c: ");
        int c = scanner.nextInt();

        Triangle triangle = new Triangle(a, b, c);
        triangle.equilateral();
        triangle.isosceles();
        triangle.scalene();
        triangle.rightAngled();
    }
}
