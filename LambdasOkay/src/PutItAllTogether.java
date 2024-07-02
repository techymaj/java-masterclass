import java.util.*;

public class PutItAllTogether {
    static Random random = new Random(26);

    public static void main(String[] args) {
        String[] firstNames = new String[]{
                "WilFriEd",
                "mariA",
                "Brian",
                "innoCENt",
                "Anna"
        };
        Arrays.asList(firstNames).replaceAll(s -> s.toUpperCase());
        Arrays.asList(firstNames).forEach(s -> System.out.println(s));

        Arrays.asList(firstNames).replaceAll(s -> {
            var letter = (char) random.nextInt(65, 91);
            s = s + " " + letter + ".";
            return s;
        });
        System.out.println(Arrays.toString(firstNames));

        Arrays.asList(firstNames).replaceAll(s -> {
            var twoNames = s.split(" ");
            var lastName = twoNames[0].toCharArray();
            for (int i = 0; i < lastName.length / 2; i++) {
                var temp = lastName[i];
                lastName[i] = lastName[lastName.length - (i + 1)];
                lastName[lastName.length - (i + 1)] = temp;
            }
            return s + " " + new String(lastName);
        });
        System.out.println(Arrays.toString(firstNames));
    }
}
