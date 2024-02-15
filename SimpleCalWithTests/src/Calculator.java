public class Calculator {

    public static void main(String[] args) {
        System.out.println("Addition: " + add(5, 3));
        System.out.println("Subtraction: " + subtract(5, 3));
        System.out.println("Multiplication: " + multiply(5, 3));
        System.out.println("Division: " + divide(5, 3));
        System.out.println("Modulo: " + modulo(5, 3));
        System.out.println("Square: " + square(5));
        System.out.println("Cube: " + cube(5));
        System.out.println("Power: " + power(5, 3));
        System.out.println("Factorial: " + factorial(5));
        System.out.println("Absolute: " + absolute(-5));
        System.out.println("Maximum: " + maximum(5, 3));
        System.out.println("Minimum: " + minimum(5, 3));
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        return a / b;
    }

    public static int modulo(int a, int b) {
        return a % b;
    }

    public static int square(int a) {
        return a * a;
    }

    public static int cube(int a) {
        return a * a * a;
    }

    public static int power(int a, int b) {
        return (int) Math.pow(a, b);
    }

    public static int factorial(int a) {
        int result = 1;
        for (int i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }

    public static int absolute(int a) {
        return Math.abs(a);
    }

    public static int maximum(int a, int b) {
        return Math.max(a, b);
    }

    public static int minimum(int a, int b) {
        return Math.min(a, b);
    }
}
