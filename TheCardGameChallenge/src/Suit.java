public enum Suit {
    HEARTS(9829),
    SPADES(9824),
    CLUBS(9827),
    DIAMONDS(9830);

    private final int ascii;

    private Suit(int ascii) {
        this.ascii = ascii;
    }

    public int getAscii() {
        return this.ascii;
    }
}
