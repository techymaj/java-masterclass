import java.util.Iterator;

public class GenericList<T> implements Iterable {
    private T[] items = (T[]) new Object[10];
    private int count;

    public void add(T item) {
        items[count++] = item;
    }

    public T get(int index) {
        return items[index];
    }

    @Override
    public Iterator iterator() {
        return new ListIterator(this);
    }

    private class ListIterator implements Iterator<T> {
        private final GenericList<T> list;
        private int index;
        public ListIterator(GenericList<T> genericList) {
            this.list = genericList;
        }

        @Override
        public boolean hasNext() {
            return index < list.count;
        }

        @Override
        public T next() {
            return list.items[index++];
        }
    }
}
