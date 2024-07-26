import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l", "He", "lo");
        String dText = "He".concat("llo");
        String eText = "hello";

//        System.out.println(eText.equals(aText));

        List<String> hellos = Arrays.asList(aText, bText, cText, dText, eText);
//        hellos.forEach(hello -> System.out.println(hello + " hashcode: " + hello.hashCode()));


        Set<String> mySet = new HashSet<>(hellos);
        mySet.forEach(hello -> System.out.println(hello + " hashcode: " + hello.hashCode()));
        System.out.println(mySet);
    }
}
