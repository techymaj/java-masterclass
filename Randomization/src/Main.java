import java.util.Random;

public class Main {

    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) {
//            System.out.println((int) (Math.random() * 10) + 1);
//        }

        Random main = new Random();
        main.ints()
                .limit(5)
                .boxed()
                .forEach(System.out::println);
        
        Random r = new Random();
        r.ints()
                .limit(10)
                .forEach(System.out::println);

        System.out.println("-".repeat(50));

        r.ints(0, 10)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("-".repeat(50));

        r.ints(5, 0, 10)
                .forEach(System.out::println);
    }
}
