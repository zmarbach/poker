import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        List<Card> hand = new ArrayList<>();
        HandAnalyzer handAnalyzer = new HandAnalyzer();


        for (int i = 0; i < 5; i++) {
            Card card = deck.draw();
            hand.add(card);
        }

        handAnalyzer.setHand(hand);
        System.out.println("Hand: ");
        for (Card card : hand) {
            System.out.println(card.toString());
        }

        System.out.print("\n");
        System.out.println("Best Result: ");
        PokerHandsEnum bestResult = handAnalyzer.analyze(hand).getHandType();
        System.out.println(bestResult.toString());

    }
}
