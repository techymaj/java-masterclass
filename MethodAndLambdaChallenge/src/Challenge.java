import java.util.Arrays;
import java.util.Random;

public class Challenge {

    public static void main(String[] args) {

        String[] names = {"Derrick", "mariam", "mIkE", "paul", "george", "maria", "anNa"};

        System.out.println("----------toUpper----------");
        Arrays.setAll(names, s -> names[s].transform(String::toUpperCase));
        System.out.println(Arrays.toString(names));
        System.out.println();

        System.out.println("----------middleName----------");
        var backed = Arrays.asList(names);
        backed.replaceAll(s -> {
                    var mid = s + " " + middleName('A', 'M') + ". ";
                    return mid.transform(String::toLowerCase);
                }
        );
        System.out.println(backed);
        System.out.println();

        System.out.println("----------reversed----------");
        backed.forEach(s -> {
            var fname = s.split(" ")[0];
            var lname = reverse(fname);
            var fullName = s + lname.transform(String::toUpperCase);
//            String f = fullName.transform(String::toLowerCase);
            System.out.println(fullName);
        });
        System.out.println();
    }

    private static String reverse(String s) {

        StringBuilder unoReversal = new StringBuilder(s);
        return unoReversal.reverse().toString();
    }

    private static char middleName(char from, char to) {

        Random random = new Random();
        return (char) random.nextInt(from, (to + 1));
    }
}
