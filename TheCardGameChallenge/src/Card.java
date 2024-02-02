public record Card(
        Face face,
        Suit suit,
        Rank rank
) {
    public Card getFaceCard(Face face, Suit suit) {
        return new Card(face, suit, this.rank);
    }

    public Card getRankCard(Face face) {
        return new Card(face, this.suit, this.rank);
    }

    public Card getSuitCard(Suit suit) {
        return new Card(this.face, suit, this.rank);
    }


    @Override
    public String toString() {
//        return face.getFace() + " " + this.suit.getUnicode() + " " + "(" + this.rank.getRank() + ")";
        return face.getFace() + this.suit.getUnicode();
    }
}
