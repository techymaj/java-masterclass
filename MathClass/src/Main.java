public class Main {

    public static void main(String[] args) {

        int maxMinusFive = Integer.MAX_VALUE - 5;

        for (int j = 0, id = maxMinusFive; j < 10; id = Math.incrementExact(id), j++) {
            System.out.printf("Assigning id %,d%n", id);
        }
    }
}
