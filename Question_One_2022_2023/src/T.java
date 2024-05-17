public abstract class T {

    public static void main(String[] args) {

//        T obj1 = new T(); // True
        // reason - T is an abstract class and abstract classes cannot be instantiated

        T arr[] = new T[5]; // False
        // reason - T - can be used to create an array
    }
}
