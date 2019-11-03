import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final List<Card> discard = new ArrayList<>();
    private final Random random = new Random();


    public Deck() {
        for (var suit : Suits.values()) {
            for (var face : Faces.values()) {
                Card card = new Card(face, suit);
                cards.add(card);
            }
        }
    }

    public Card draw() {
            var ranIndex = random.nextInt(cards.size());
            Card card = cards.get(ranIndex);
            cards.remove(card);
            discard.add(card);
            return card;
        }

}
