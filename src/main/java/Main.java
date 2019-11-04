import java.time.Year;

public class Main {
    public static void main(String[] args) {
        //setup
        Dealer dealer = new Dealer();
        HandAnalyzer handAnalyzer = new HandAnalyzer();

        //deal the hands
        var xHand = dealer.deal(5);
        var yHand = dealer.deal(5);

        //sort hands
        var sortedXHand = handAnalyzer.sort(xHand);
        var sortedYHand = handAnalyzer.sort(yHand);

        //print out hands and best result for user
        System.out.println("X Hand: ");
        for (Card card : sortedXHand) {
            System.out.println(card.toString());
        }
        System.out.println(" -------------- X Hand Best Result: " + handAnalyzer.analyze(sortedXHand).getHandType().toString());
        System.out.print("\n");


        System.out.println("Y Hand: ");
        for (Card card : sortedYHand) {
            System.out.println(card.toString());
        }
        System.out.println(" -------------- Y Hand Best Result: " + handAnalyzer.analyze(sortedYHand).getHandType().toString());
        System.out.print("\n");

        //compare the hands and display winner
        var result = handAnalyzer.compare(xHand, yHand);

        if (result == 1) {
            System.out.println("Winner is X!");
        }

        if (result == -1) {
            System.out.println("Winner is Y!");
        }

        if (result == 0) {
            System.out.println("Tie!");
        }

    }
}
