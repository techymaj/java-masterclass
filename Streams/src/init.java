public class init {

    {
        System.out.println("init");
    }

    {
        System.out.println("init too");
    }

    static {
        System.out.println("static");
    }

    public init() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        init i = new init();
    }
}
