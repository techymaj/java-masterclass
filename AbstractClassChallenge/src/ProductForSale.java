public class ProductForSale {
    private String type;
    private double price;
    private String description;

    public double getSalesPrice(int quantity) {
        return quantity * this.price;
    }

    public void printPricedLineItem(int quantity) {
        System.out.println();
    }

    public abstract showDetails()
}
