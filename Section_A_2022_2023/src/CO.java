public class CO {

    String name;

    // constructor - used to construct objects

    public CO() {
        this.name = "Terry";
    }

    public CO(String s) {
        this.name = s;
    }

    public static void main(String[] args) {

        CO object1 = new CO();
        System.out.println(object1.name);

        CO object2 = new CO("John");
        System.out.println(object2.name);
    }
}
