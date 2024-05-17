public sealed class Parent permits ChildOne, ChildTwo, ChildThree {

    public static void main(String[] args) {

        String name = "M a j aliwa";

        for (String c : name.split(" ")) {
            System.out.println(c);
        }
    }
}

sealed class ChildOne extends Parent permits X {}

final class ChildTwo extends Parent {}

non-sealed class ChildThree extends Parent {}

final class X extends ChildOne {}

//final class ChildWhose extends Parent {}

