import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var array = getList(1, 2,   3,4,5);
        System.out.println(array);
    }

    public static ArrayList<Integer> getList(Integer... varargs) {
        ArrayList<Integer> aList = new ArrayList<>();
        for (var i : varargs) {
            aList.add(i);
        }
        return aList;
    }
}
