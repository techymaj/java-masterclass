public class Main {

    public static void main(String[] args) {

        GenericList<Integer> integers = new GenericList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);

        var iterator = integers.iterator();
        // enhanced for is syntactic sugar for an iterator
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (curr.equals(3)) {
                iterator.remove();
                System.out.println(curr + " removed");
            }
            System.out.println(curr);
        }
    }
}
