 interface X {
    int XZ=10;
     static void execute(){
         System.out.println("slaps");
     }
    void run();

     private static void m1() {
         System.out.println("m1");
     }

     static void m2() {
         // run private method
         m1();
     }
}

interface Y {
    static void execute (){
        System.out.println("slaps harder");
    }
    void run();
    default void m1() {
        System.out.println("m1");
    }
}

interface Z extends X, Y {
    void run();
    default void m1() {
        System.out.println("m2");
    }
}

class A implements Z {
    public void run() {
        System.out.println("run");
    }
    public static void main(String[] args) {
        Y a = new A();
        a.run();
        a.m1();
        X.execute();
        X.m2();
        System.out.println(X.XZ);
    }
}