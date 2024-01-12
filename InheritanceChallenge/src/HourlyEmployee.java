public class HourlyEmployee extends Employee {
    private double hourlyPayRate;

    public HourlyEmployee(String name, String birthDate, String endDate, long employeeId, String hireDate, double hourlyPayRate) {
        super(name, birthDate, endDate, employeeId, hireDate);
        this.hourlyPayRate = hourlyPayRate;
    }

    public double getDoublePay() {
        return this.hourlyPayRate * 2;
    }
}
