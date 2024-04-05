public class Switch {
    public static void main(String[] args) {
        int n = 2;

        do {
            n++;
            if (n % 3 == 0) {
                if (n != 9) {
                    System.out.println(n);
                }
            }
        } while (n < 16);
    }
}
