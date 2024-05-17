public class A<S> {

    S value;

    public <T> S callMe(S val, T exp) {
        if (exp instanceof String s) {
            System.out.println("We have a string: " + s);
        }
        return val;
    }

    public static void main(String[] args) {
        A<String> name = new A<>();
        name.value = "I am";
        System.out.println(name.value);

        A<Integer> value = new A<>();
        int val = value.callMe(2, "inevitability");
        System.out.println(val);
    }
}