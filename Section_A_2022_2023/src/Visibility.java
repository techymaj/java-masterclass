public class Visibility {

    String name;

    public static void main(String[] args) {

        Visibility obj = new Visibility();
        System.out.println(obj.name);
    }
}

class Another extends Visibility {

    public static void main(String[] args) {

        Another another = new Another();
        System.out.println("another = " + another.name);
    }
}