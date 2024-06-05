import java.util.Optional;

public class Main {

    record Student (int age, String status) {}

    public static void main(String[] args) {
        Optional<Student> student = getStudentStatus();
        var getStatus = student.map(Student::status);
        System.out.println(getStatus.orElse("nothing here"));
    }

    static Optional<Student> getStudentStatus() {
        var value = new Student(29, null);
        return Optional.of(value);
    }
}
