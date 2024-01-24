import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

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
//        Arrays.sort(students);
//        System.out.println(Arrays.toString(students));

//        for (var student : students) {
//            int val = jax.compareTo(student);
//            System.out.println(val);
//        }
        System.out.println("Result = " + jax.compareTo(students[2]));

        // sort by gpa
        // if gpa is tied, sort by name
        // using the overloaded sort method (with a comparator)
        Arrays.sort(students, new StudentGPAComparator().reversed());
//        Comparator<Student> gpaComparator = new StudentGPAComparator();
//        Arrays.sort(students, gpaComparator);
        System.out.println(Arrays.toString(students));
    }
}

class Student implements Comparable<Student> {
    String name;
    private Integer age;
    private static int LAST_ID = 1_000;
    private static Random random = new Random();
    private int id;
    protected double gpa;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.id = LAST_ID++;
        this.gpa = random.nextDouble(1.0, 4.0);
    }

    @Override
    public String toString() {
        return "%d: %s, (%.2f)".formatted(id, name, gpa);
    }

//    @Override
//    public int compareTo(Student o) {
//        // compare based on age
//        return this.age.compareTo(o.age);
//    }

//    @Override
//    public int compareTo(Student o) {
//        // compare based on name
//        return this.name.compareTo(o.name);
//    }

    @Override
    public int compareTo(Student o) {
        // compare based on id
        // if this.id > o.id, return positive
        // if this.id < o.id, return negative
        // if this.id == o.id, return 0
//        return this.id - o.id;
        return Integer.valueOf(this.id).compareTo(Integer.valueOf(o.id));
    }
}

class StudentGPAComparator implements Comparator<Student> {

    // you want to make this a nested class
    // because then you unlock access to the private fields
    @Override
    public int compare(Student o1, Student o2) {
        // compare based on gpa
        // if gpa is tied, compare based on name
        // lowest to highest
        return (o1.gpa + o1.name).compareTo(o2.gpa + o2.name);

        // highest to lowest
//        return (o2.gpa + o2.name).compareTo(o1.gpa + o1.name);
    }
}