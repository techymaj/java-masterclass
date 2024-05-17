public class Recursion {

    // recursion - re-calling the same method
    static int i = 0; // "0"

    public void add() {

        if (i < 10) { // 9
            System.out.println("Calling add() with i = " + i);
            i++; // 10
            add();
        }
    }

    public static void main(String[] args) {

        Recursion recursion = new Recursion();
        recursion.add();
    }
}
