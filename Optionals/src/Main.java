import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Optional<Student> student = getStudent();
        System.out.println(student.map(Student::status).orElse("nothing here"));
    }

    static Optional<Student> getStudent() {
        return Optional.of(new Student(29, null));
    }
}

record Student (int age, String status) {}
