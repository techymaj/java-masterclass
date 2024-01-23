public class MainGeneric {

    public static <T> void noise(T x) {
        System.out.println(x + " !!!!");
    }

    public static void main(String[] args) {
        noise(new Cat());
    }
}


class Cat {
    String noise = "Meoww";

    @Override
    public String toString() {
        return noise;
    }
}