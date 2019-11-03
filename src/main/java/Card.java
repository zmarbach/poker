public class Card {
    private Faces face;
    private Suits suit;

    public Card(Faces face, Suits suit) {
        this.face = face;
        this.suit = suit;
    }

    public Faces getFace() {
        return face;
    }

    public Suits getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "" + face.toString() + " of " + suit.toString();
    }
}
