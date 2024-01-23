public class AnonymousInnerClass {
    public static void main(String[] args) {
        // anon class = one instance of the class
        Anon anon = new Anon() {
            @Override
            void define() {
                System.out.println("I am");
            }
        };
        anon.define();

        StillAnon nan = new StillAnon() {
            @Override
            void define() {
                System.out.println("Yup, still am");
            }
        };
        nan.define();
    }
}

abstract class Anon {
    abstract void define();
}

class StillAnon {
    void define() {
        System.out.println("I AM");
    }
}