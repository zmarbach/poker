import java.util.List;

public class HandResult {

    private final PokerHandsEnum handType;
    private final Faces highCardInType;
    private final List<Faces> remainingFaces;

    public HandResult(PokerHandsEnum handType, Faces highCardInType, List<Faces> remainingFaces) {
        this.handType = handType;
        this.highCardInType = highCardInType;
        this.remainingFaces = remainingFaces;
    }

    public PokerHandsEnum getHandType() {
        return handType;
    }

    public Faces getHighCardInType() {
        return highCardInType;
    }

    public List<Faces> getRemainingFaces() {
        return remainingFaces;
    }
}
