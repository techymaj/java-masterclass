import java.util.Arrays;
import java.util.stream.Stream;

public class StreamFromArrays {

    int age = 4;

    private StreamFromArrays(int age) {
        this.age = age;
        m();
        m2();
    }

    public static void main(String[] args) {
//        System.out.println(age);
        StreamFromArrays s = new StreamFromArrays(5);

//        int[] numbers = {5, 4, 3, 2, 1};
//        Arrays.stream(numbers)
//                .sorted()
//                .forEach(System.out::println);

//        var intStream = Stream.of(numbers);
//        intStream.mapToInt()

//        System.out.println("count: " + count);
    }

    void m2() {
        System.out.println("m2");
    }

    static void m() {
//        StreamFromArrays s = new StreamFromArrays();
        System.out.println("age");
    }
}

//class C extends StreamFromArrays {
//    C() {
////        super(5);
//    }
//}
