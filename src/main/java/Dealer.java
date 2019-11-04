import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private Deck deck = new Deck();

    public List<Card> deal (int numOfCardsToDeal) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = deck.draw();
            hand.add(card);
        }
        return hand;
    }
}
