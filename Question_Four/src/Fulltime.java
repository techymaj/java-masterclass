import java.util.Scanner;

public class Fulltime extends Staff {

    double salary;

    public void print(double salary) {
        System.out.printf(
                "StaffNo: %s, " +
                        "StaffName: %s, " +
                        "StaffAge: %d, " +
                        "salary: %.2f",
                StaffNo,
                StaffName,
                StaffAge,
                salary
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fulltime fulltimeEmployee = new Fulltime();
        System.out.print("Enter StaffNo: ");
        fulltimeEmployee.StaffNo = scanner.nextLine();
        System.out.print("Enter StaffName: ");
        fulltimeEmployee.StaffName = scanner.nextLine();
        System.out.print("Enter StaffAge: ");
        fulltimeEmployee.StaffAge = scanner.nextInt();
        System.out.print("Enter salary: ");
        fulltimeEmployee.salary = scanner.nextDouble();
        fulltimeEmployee.print(fulltimeEmployee.salary);
    }
}
