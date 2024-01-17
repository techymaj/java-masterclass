public interface Thoughts {

    int MY_CONSTANT = 32;
    void goodThoughts();

    static void badThoughts() {
        System.out.println("I am a bad liar.");
    }

    default void goodOrBad() {
        System.out.println("well, well, well");
        Thoughts.TODO();
        Thoughts t = new Thoughts() {
            @Override
            public void goodThoughts() {
            }
        }; t.no();
    }

//    static void very(); should have a body

//    default void me(); won't compile, extension (default) methods must have a body
//
    private static void TODO() {
        // since this cannot be accessed anywhere else other than this interface,
        // it can be private and nothing else but private
        System.out.println("TODO");
    }

    private void no() {
        // since this cannot be accessed anywhere else other than this interface,
        // it can be private and nothing else but private
        // can only be accessed in default and static methods
        System.out.println("No");
    }
}
