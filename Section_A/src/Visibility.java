public class Visibility {

    void method() {

    }

    public static void main(String[] args) {

        Visibility visibility = new Visibility();
        visibility.method();
    }
}

class SubClass extends Visibility {

    public static void main(String[] args) {

        SubClass subClass = new SubClass();
        subClass.method();
    }
}
