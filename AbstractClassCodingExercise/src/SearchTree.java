public class SearchTree implements NodeList {
    // write code here
    private ListItem root;

    public SearchTree(ListItem root) {
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

    private void performRemoval(ListItem child, ListItem parent) {

    }
}