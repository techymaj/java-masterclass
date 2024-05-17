public class B implements Z {

    @Override
    public int walk(int times) {
        return times;
    }

    public static void main(String[] args) {

        B object = new B();
        int times = object.walk(12);
        System.out.println("I walk " + times + " times a day");
    }
}

interface Z {
    public abstract int walk(int times);
}
