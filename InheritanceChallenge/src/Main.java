public class Main {
    public static void main(String[] args) {
        SalariedEmployee se = new SalariedEmployee("Joy","22/09/1993","01/01/2024",1234542,"01/01/2022",350_000_000,true);
        se.retire();

        HourlyEmployee he = new HourlyEmployee("Cassie","09/02/2000","23/09/2025",32823238, "23/09/2020",34);
        double hrate = he.getDoublePay();
        System.out.println(he.getName() + " earns $" + hrate + " per hour");
    }
}
