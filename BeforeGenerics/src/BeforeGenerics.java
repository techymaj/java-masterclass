import java.util.ArrayList;
import java.util.List;

public class BeforeGenerics {

    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("31");
        list.add(22);
        list.add('A');

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        int age = (int) list.get(0); // casting. This is not type safe
        System.out.println(age);

        String myAge = (String) list.get(0); // casting. This is not type safe
        System.out.println(myAge);
    }
}
