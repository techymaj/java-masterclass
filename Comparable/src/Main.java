import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer five = 5;
        Integer[] others = {20, 1, 10, 5, -14, 50};

        String banana = "banana";
        String[] fruits = {"banana", "apple", "orange", "grape", "watermelon"};

        // it compares the value of the first character of the string
        // and moves on to the next string
//        for (var fruit : fruits) {
//            System.out.println(
//                    fruit.charAt(0) + ":" + (int) fruit.charAt(0) + " " +
//                            fruits[0].charAt(0) + ":" + (int) fruits[0].charAt(0));
//            int val = banana.compareTo(fruit);
//            System.out.println(val);
//        }

//        for (var number : others) {
//           int val = five.compareTo(number);
//            System.out.println(val);
//        }
        Student jax = new Student("Jax", 20);
        Student[] students = {
                new Student("john", 20),
                new Student("james", 21),
                new Student("mary", 19),
                new Student("tom", 22),
                new Student("alex", 18),
                new Student("claire", 23),
                new Student("zack", 20),
        };
// Because uppercase letters have a lower hexadecimal value
// than lowercase ones, an uppercase letter "A" in "App" will
// come before a lowercase letter "a" in "ape".
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

//        for (var student : students) {
//            int val = jax.compareTo(student);
//            System.out.println(val);
//        }
        System.out.println("Result = " + jax.compareTo(students[2]));
    }
}

class Student implements Comparable<Student> {
    private String name;
    private Integer age;

    public Student(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    @Override
    public String toString() {
        return name;
    }

//    @Override
//    public int compareTo(Student o) {
//        // compare based on age
//        return this.age.compareTo(o.age);
//    }

    @Override
    public int compareTo(Student o) {
        // compare based on name
        return this.name.compareTo(o.name);
    }
}
