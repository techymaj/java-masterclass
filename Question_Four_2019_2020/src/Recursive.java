public class Recursive {

    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        else if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        else {
            return mystery(a + a, b / 2) + a;
        }
    }

    // s1: a = 5, b = 25
    // s2: a = 10, b = 12 | a = 5
    // s3: a = 20, b = 6
    // s4: a = 40, b = 3
    // s5: a = 80, b = 1 | a = 80
    // s6: a = 160, b = 0 | a = 160










    public static void main(String[] args) {
        int value = mystery(5, 25);
        System.out.println(value);
   }
}
