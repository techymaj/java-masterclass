import java.util.ArrayList;
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

        list.forEach(s -> {
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
        System.out.println(myNameIs.name("John"));
    }
}

@FunctionalInterface
interface ReturnLambda {
    String name(String name);
}
