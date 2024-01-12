public class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, int pagesPrinted, boolean duplex) {
        if (tonerLevel >= 100) this.tonerLevel = 100;
        else this.tonerLevel = Math.max(tonerLevel, 0);
        this.pagesPrinted = pagesPrinted;
        this.duplex = duplex;
    }

    public int getPagesPrinted() {
        return this.pagesPrinted;
    }

    public Printer(int pagesPrinted) {
        this(100, pagesPrinted, true);
    }

    public int addToner(int tonerAmount) {
        if (tonerAmount + this.tonerLevel > 100) return -1;
        else if (tonerAmount + this.tonerLevel < 0) return -1;
        else return this.tonerLevel + tonerAmount;
    }

    public int printPages(int pages) {
        if (this.duplex) {
            System.out.println("It's a duplex printer");
            this.pagesPrinted = Math.round((float) pages / 2);
        } else {
            System.out.println("It's not a duplex printer");
            this.pagesPrinted = pages;
        }
        return this.pagesPrinted;
    }
}
