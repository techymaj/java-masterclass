package tech.majaliwa;

public record Card(
        Face face,
        Suit suit,
        int cardValue
) {
    @Override
    public String toString() {
        return face.getFace() + this.suit.getUnicode();
    }
}
