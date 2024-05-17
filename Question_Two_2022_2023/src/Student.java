import java.util.Scanner;
import java.util.Arrays;

public class Student {

    int age;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Student[] student = new Student[3]; // created an array

        for (int i = 0; i < student.length; i++) {
            student[i] = new Student();
            student[i].age = scanner.nextInt();
//            System.out.println("Student is " + student[i].age);
        }

        System.out.println(Arrays.toString(student));
    }

    @Override
    public String toString() {
        return "" + age;
    }
}
