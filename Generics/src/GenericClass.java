//public class GenericClass<T> implements Cloneable {
//    public T name;
//
//    public GenericClass(T name) {
//        this.name = name;
//    }
//
//    public T getName() {
//        return this.name;
//    }
//
//
//
//    public static void main(String[] args) {
//        GenericClass<String> person = new GenericClass<>("Jax");
//        String name = person.getName();
//        System.out.println(name);
//        // Another
//        GenericClass<Integer> number = new GenericClass<>(1);
//        int num = number.getName();
//        System.out.println(num);
//    }
//
//    @Override
//    public GenericClass<T> clone() throws CloneNotSupportedException {
////        try {
//            GenericClass clone = (GenericClass) super.clone();
//            // TODO: copy mutable state here, so the clone can't change the internals of the original
//            return clone;
////        } catch (CloneNotSupportedException e) {
////            throw new AssertionError();
////        }
//    }
//}

public class GenericClass {
    public <T> T describe(T name) {
        return name;
    }

    public static void main(String[] args) {
        GenericClass g = new GenericClass();
        String name = g.describe("Jax");
        int age = g.describe(29);
        System.out.println("Your name is " + name + " and you are " + age);
    }
}