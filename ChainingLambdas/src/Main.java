import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        String name = "Wilfried";
        Function<String, String> uName = String::toUpperCase;
        System.out.println(uName.apply(name));

        Function<String, String> lName = s -> s.concat(" Majaliwa");
        System.out.println(lName.apply(name));

        // execute uName and then lName using name as input
        var uNameThenLName = uName.andThen(lName).apply(name);
        // uName upper cases the name and then lName appends the last name
        System.out.println(uNameThenLName);

        // execute lName lambda first and then uName
        var composed = uName.compose(lName).apply(name);
        System.out.println(composed);

        Function<String, String[]> f0 = uName
                .andThen(s -> s.concat(" Majaliwa"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name)));

        // chained lambdas don't have to return the same type
        Function<String, String> f1 = uName
                .andThen(s -> s.concat(" Majaliwa"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(name));
    }
}
