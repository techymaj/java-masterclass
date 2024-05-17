public class Encaps extends Object {

   static String name = "Terry";   // "Salah"
    int age = 12; // 32

    public static void main(String[] args) {

        Encaps obj1 = new Encaps();
        System.out.println(obj1.age); // 12

        Encaps obj2 = new Encaps();
        obj2.age = 32;
        System.out.println(obj2.age); // 32

        Encaps obj3 = new Encaps();
        System.out.println(obj3.age); // 32
    }
}
