import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WildCards {

//    <?> name; // error: only used with collections
    List<?> names;

    public static void main(String[] args) {
        List<Dog> ints = new ArrayList<>();
        print(ints); // compile time error if List<Object> used:
        // List of Objects is not a super class of List of Integer
    }

//    private static void print(List<Object> list) {
//        System.out.println();
//    }

//    private static <?> void print(List<?> list) { // error <?> void
//        System.out.println("Amaze");
//    }

    private static void print(List<? extends Animal> list) {
        System.out.println("Amaze");
    }
}

class Animal {}

class Dog extends Animal {}
