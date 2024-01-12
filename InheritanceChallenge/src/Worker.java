public class Worker {
    private String name;
    private String birthDate;
    private String endDate;

    public Worker(String name, String birthDate, String endDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.endDate = endDate;
    }

    public String getName() {
        return this.name;
    }

    public void collectPay() {
        System.out.println("What pay?");
    }

    public void terminate(String endDate) {
        System.out.println(this.name + " has been terminated");
    }
}
