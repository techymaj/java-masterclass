public interface Player<T extends Human, U> {
    String name();

}

record One(String name) implements Player<Woman, Under21>, Comparable {
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    // accessor method name() is already implicitly applied to this record
    // so no implementation needed
}

class Woman extends Human {}
class Under21 {}

class Human {}

class Girl {}