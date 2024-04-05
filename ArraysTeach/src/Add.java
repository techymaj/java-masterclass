public class Add {

    public int add(int x, int y) {
        return x + y;
    }

    public int add(int x, int y, int z) {
        return x + y + z;
    }

    public static void main(String[] args) {
        Add obj = new Add();
        int value = obj.add(3, 2);
        int val2 = obj.add(3, 4, 5);
        System.out.println(value);
        System.out.println(val2);

    }
}
