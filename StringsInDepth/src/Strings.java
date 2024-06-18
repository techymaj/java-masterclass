import java.util.List;

public class Strings {

    public static void main(String[] args) {

        // == compares references
        // .equals( ) compares content, i.e character sequence

        String s1 = "String One";
        String s2 = new String("String Two");
        var s3 = "String One".describeConstable();
        String s4 = new String("String Two");
        String s5 = new String("String One");

        for (var s : List.of(s1, s2, s3, s4, s5)) {
            System.out.println(s + " " + s.hashCode());
        }

        System.out.println(s1.equals(s5));
    }
}
