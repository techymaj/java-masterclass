package sams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public interface ConsumerInterface<T> {
    void accept(T t);
}

class Main {
    public static void main(String[] args) {
        ConsumerInterface<String> consumeMe = s -> System.out.println(
                "Well, I too can make something happen, " + s
        );
        consumeMe.accept("Wilfried");
//        String s = "M";
        String prefix = "nato";
        List<String> strings = new ArrayList<>();
        strings.add("Alpha");
        strings.add("Bravo");
        strings.add("Charlie");
        strings.add("Delta");
        List<String> npa = new ArrayList<>(strings);
//        Comp

        npa.forEach(s -> {
                    var starter = s.charAt(0);
                    System.out.println(s + " is " + starter + " in " + prefix);
                }
        );

        IntFunction<String> well = (x) -> x + " years old.";
        var age = well.apply(29);
        System.out.println(age);
    }
}