public class Override {

    public void one() {
        System.out.println("one");
    }
}

class Overriden extends Override {

    @java.lang.Override
    public void one() {
        System.out.println("Overriden one");
    }
}
