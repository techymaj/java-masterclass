import java.util.Scanner;

public class CheckException {
    public static void main(String[] args)  {
        Scanner s = new Scanner(System.in);
       try {
           int age = Integer.parseInt("hghuoo");
       } catch (NumberFormatException nfe) {
           System.out.println("String passed is not a number");
       }
    }
}
