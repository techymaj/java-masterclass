public class Ronald extends Student {
    public Ronald(String name, double cgpa) {
        super(name, cgpa);
    }

    @Override
    public void describe() {
        System.out.println(name);
    }
    public static void main(String[] args) {
        Ronald remy = new Ronald("Remy", 4.5);
        remy.describe();
//        remy.privateInfo();
    }
}
