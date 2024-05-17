import java.util.Scanner;

public class Parttime extends Staff {

    int hoursWorked;
    static final int rate = 2500;
    double salary;
    double tax;

    public void print(double computedSalary) {
        this.salary = hoursWorked * rate;
        this.tax = 0.3 * this.salary;
        computedSalary = this.salary;
        System.out.printf(
                "StaffNo: %s, " +
                "StaffName: %s, " +
                "StaffAge: %d, " +
                "hoursWorked: %d, " +
                "salary: %.2f, " +
                "tax: %.2f",
                StaffNo,
                StaffName,
                StaffAge,
                hoursWorked,
                computedSalary,
                tax
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parttime parttimeEmployee = new Parttime();
        System.out.print("Enter StaffNo: ");
        parttimeEmployee.StaffNo = scanner.nextLine();
        System.out.print("Enter StaffName: ");
        parttimeEmployee.StaffName = scanner.nextLine();
        System.out.print("Enter StaffAge: ");
        parttimeEmployee.StaffAge = scanner.nextInt();
        System.out.print("Enter hoursWorked: ");
        parttimeEmployee.hoursWorked = scanner.nextInt();
        parttimeEmployee.print(parttimeEmployee.salary);
    }
}
