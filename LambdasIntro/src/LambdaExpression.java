import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaExpression {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of("alpha", "bravo", "charlie"));

        for (var s : list) {
            System.out.println(s);
        }

        System.out.println("-------------");

        list.forEach(s -> System.out.println(s));

        System.out.println("-------------");

//        String s = "hello"; // will cause a compile time error if we use s in the lambda expression

        list.forEach(s -> { // s is already defined in the scope
            char first = s.charAt(0);
            System.out.println(s + " means " + first);
        });

        System.out.println("-------------");
        String prefix = "nato";
        ReturnLambda myNameIs = name -> {
//            prefix = "alo"; // this is not allowed. prefix must be final or effectively final
            var upper = prefix + name.toUpperCase();
            return "Your name is " + upper;
        };
//        prefix = "what about this?"; // also not allowed
        System.out.println(myNameIs.name("John"));

        list.removeIf(s -> s.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));

        System.out.println("-------------");

        list.addAll(List.of("easy", "earnest", "eager"));
        list.forEach(s -> System.out.println(s));
        list.removeIf(s -> s.startsWith("ea"));
        System.out.println("-------ea------");
        list.forEach(s -> System.out.println(s));

        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        System.out.println("-------replaceAll------");
        list.forEach(s -> System.out.println(s));

        String[] array = new String[10];
        System.out.println(Arrays.toString(array));
        Arrays.fill(array, "");
        System.out.println(Arrays.toString(array));

        Arrays.setAll(array, i -> "" + (i + 1) + ". ");
        System.out.println(Arrays.toString(array));

        Arrays.setAll(array, i -> "" + (i + 1) + ". "
                        + switch (i) {
                    case 0 -> "one";
                    case 1 -> "two";
                    case 2 -> "three";
                    case 3 -> "four";
                    case 4 -> "five";
                    case 5 -> "six";
                    case 6 -> "seven";
                    case 7 -> "eight";
                    case 8 -> "nine";
                    case 9 -> "ten";
                    default -> "many";
                }
        );
        System.out.println(Arrays.toString(array));
    }
}

@FunctionalInterface
interface ReturnLambda {
    String name(String name);
}
