import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String helloWorld = "%s %s".formatted("Hello", "World");
        String helloWorld2 = String.format("%s %s", "Hello", "World");

//        System.out.println("Using formatted method: " + helloWorld);
//        System.out.println("Using format method: " + helloWorld2);

        String helloWorld3 = format(" ", "Hello World", "Majaliwa Wilfried");
        System.out.println("Using format method: " + helloWorld3);
    }

    private static String format(String regexp, String... args) {
        String[] split = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            var split_string = args[i].split(regexp);
            split[i] = Arrays.toString(split_string);
        }
        return Arrays.toString(split);
    }
}
