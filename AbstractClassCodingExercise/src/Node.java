public class Node extends ListItem {
    // write code here
    public Node(Object obj) {
        super(obj);
    }

    @Override
    public ListItem next() {
        return this.rightLink;
    }

    @Override
    public ListItem setNext(ListItem item) {
        this.rightLink = item;
        return this.rightLink;
    }

    public ListItem previous() {
        // implement this
        return null;
    }

    @Override
    public ListItem setPrevious(ListItem item) {
        this.leftLink = item;
        return this.leftLink;
    }

    @Override
    public int compareTo(ListItem item) {
        if (this.value > item) {
            return 1;
        } else if (this.value < item) {
            return -1;
        } else {
            return 0;
        }
    }
}