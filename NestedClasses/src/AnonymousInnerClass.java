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

        Yo y = new Yo();
        y.define("JA");

        Yo yo = new Yo() {
            @Override
            public void define(String name) {
                System.out.println("Yo, still anon, " + name);
            }

            public void me() {
                System.out.println("me");
            }
        };
        yo.define("JAX");
//        yo.me(); // compile time error. not a recognized method

        Alo alo = new Alo() {
            @Override
            public void define(String s) {
                System.out.println("Alo...");
            }
        };
        alo.define("..");

        // passing anonymous classes as arguments
        var s = titan(new Thanos() {
            @Override
            String iam() {
                return "Thanos";
            }
        });
        String i = s.iam();
        System.out.println(i);
    }

    static Thanos titan(Thanos s) {
        return s;
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

interface IAnon {
    void define(String s);
}

class Yo implements IAnon {
    @Override
    public void define(String name) {
        System.out.println("Yo, still anon, y'all");
    }
}

abstract class Alo implements IAnon {}

abstract class Thanos {
    abstract String iam();
}