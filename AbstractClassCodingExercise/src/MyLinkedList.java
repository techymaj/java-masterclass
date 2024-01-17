public class MyLinkedList implements NodeList {
    // write code here
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem item) {
        // implement this
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        // implement this
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        // implement this
    }

}