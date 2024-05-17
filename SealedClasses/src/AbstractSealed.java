public sealed abstract class AbstractSealed {

    abstract void m();

    static final class X extends AbstractSealed {
        @Override
        void m() {

        }
    }
}
