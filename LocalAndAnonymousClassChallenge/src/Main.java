import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jax", "Maj", "2021-01-01"));
        employees.add(new Employee("Jane", "Doe", "2021-01-01"));
        employees.add(new Employee("John", "Doe", "1998-03-08"));
        employees.add(new Employee("John", "Doe", "1998-03-08"));
        take(employees);
    }

    public class Local {
        String fullName;
        double yearsWorked;
        Employee e;
        List<Employee> employees;
        public Local(Employee e) {
            this.e = e;
        }

        Local localSort = new Local() {

        };
    }

    private static void take(List<Employee> list) {

    }
}
