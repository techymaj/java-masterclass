import java.util.Scanner;

public class Frankia {
    public static void main(String[] args) {
        Name maria = s -> {
            s = "You are " + s + ", the beloved";
            System.out.println(s);
        };
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name?");
        var name = scanner.nextLine();
        maria.youAre(name);
    }
}

@FunctionalInterface
interface Name {
    void youAre(String s);
}
