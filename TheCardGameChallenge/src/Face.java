public enum Face {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A"),
//    KIZIKIZA("Kizikiza"),
    JOKER("Joker");

    private final String face;

    private Face(String face) {
        this.face = face;
    }

    public static Face getFace(int rank) {
        return Face.values()[rank];
    }

    public String getFace() {
        return this.face;
    }

}
