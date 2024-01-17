public abstract class ListItem {
    // write code here
    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    public abstract ListItem next();

    public abstract ListItem setNext(ListItem item);

    public abstract ListItem previous();

    public abstract ListItem setPrevious(ListItem item);

    public abstract int compareTo(ListItem item);

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }
}