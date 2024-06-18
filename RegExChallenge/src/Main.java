import java.util.List;

public class Main {

    public static void main(String[] args) {

        String string = "Hello World!";
        String match = "^[hH].*!$";

        if (string.matches(match)) {
//            System.out.println("Got it!");
        }

        String regex = "[A-Z].*\\p{Punct}?\\s.*\\p{Punct}$";

        String sentence2 = "I am.";
        String s2 = "The bike is red, and has flat tires.";
        String s3 = "I love being a new L.P.A. student!";
        String s4 = "Hello, friends and family: Welcome!";
        String s5 = "How are you, Mary?";

        for (var sentence : List.of(sentence2, s2, s3, s4, s5)) {
          boolean m  = sentence.matches(regex);
            System.out.println(m);
        }
    }
}
