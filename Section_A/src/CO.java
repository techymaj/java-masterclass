public class CO {

    String name;

    public CO(String name) {
        this.name = name;
    }

    public CO() {
        this.name = "John";
    }

    public static void main(String[] args) {

        CO co = new CO();
        System.out.println(co.name);

        CO co2 = new CO("Terry");
        System.out.println(co2.name);
    }
}
