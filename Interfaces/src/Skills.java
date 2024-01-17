public interface Skills extends Thoughts, Assets {

    enum DIGITS implements FuckME {
        ONE,
        TWO;

        @Override
        public boolean fucked() {
            return false;
        }
    }
    boolean knowJava();
    boolean knowDart();
    boolean knowCPlusPlus();

    // won't make sense hence won't compile, final for what?
//    private final doOver() {
//
//    }

    static void enumerate() {
        var one = DIGITS.ONE;
        System.out.println(one);
        var ami = one.fucked();
        System.out.println(ami);
        one.ordinal();
    }
}
