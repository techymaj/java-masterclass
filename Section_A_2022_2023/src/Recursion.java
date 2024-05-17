public class Recursion {

    // recursion - calling the same method within that very method
    int i = 0;

    public void recursive() {

        if (i < 3) {
            System.out.println("Some output");
            i++;
            recursive();
        }
    }

    public static void main(String[] args) {

        Recursion recursion = new Recursion();
        recursion.recursive();
    }
}
