public class Main {
    public static void main(String[] args) {
        Printer duplex = new Printer(29);
        int papersPrinted = duplex.printPages(duplex.getPagesPrinted());
        System.out.println(papersPrinted + " pages printed");
        int tonerlevel = duplex.addToner(300);
        System.out.println(tonerlevel);

        System.out.println("*".repeat(100));

        Printer notDuplex = new Printer(100,29,false);
        int papers = notDuplex.printPages(notDuplex.getPagesPrinted());
        System.out.println(papers + " pages printed");
    }
}
