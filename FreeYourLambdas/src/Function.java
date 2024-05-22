import java.util.stream.Stream;

public class Function {

    public static void main(String[] args) {

        MyFunction<String, Integer> me = age -> age + " years old";
        String IAM = me.apply(29);
        System.out.println(IAM);
        
    }
}

@FunctionalInterface
interface MyFunction<R, T> {

    R apply(T t);
}
