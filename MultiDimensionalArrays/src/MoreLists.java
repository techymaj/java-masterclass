import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoreLists {
    public static void main(String[] args) {
        String[] items = {"apples", "bananas", "oranges"};
        System.out.println(Arrays.toString(items));
//        items.add("eggs") can't do this

        List<String> list = List.of(items);
        System.out.println(list);
//        list.add("pineapples"); // list is immutable
        System.out.println(list.getClass().getName());

        ArrayList<String> arrayList = new ArrayList<>(list);
        arrayList.add("Nothing 2");
        System.out.println(arrayList);
        arrayList.removeAll(List.of("bananas"));
        System.out.println(arrayList);
        arrayList.retainAll(List.of("Nothing 2"));
        System.out.println(arrayList);
        arrayList.clear();
        System.out.println(arrayList);
        arrayList.addAll(Arrays.asList("nan","read"));
        arrayList.addAll(List.of("nan","read","me"));
        System.out.println(arrayList);
        arrayList.sort(Comparator.naturalOrder());
        System.out.println(arrayList);

        var groceries = arrayList.toArray();
        System.out.println(Arrays.toString(groceries));
        groceries = arrayList.toArray(new String[10]);
        System.out.println(Arrays.toString(groceries));


    }
}
