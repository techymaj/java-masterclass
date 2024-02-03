package tech.majaliwa;

public record Card(
        Face face,
        Suit suit,
        CardValue rank
) {


    @Override
    public String toString() {
//        return face.getFace() + " " + this.suit.getUnicode() + " " + "(" + this.rank.getRank() + ")";
        return face.getFace() + this.suit.getUnicode();
    }
}