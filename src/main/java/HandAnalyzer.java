
import java.util.*;
import java.util.stream.Collectors;

public class HandAnalyzer {
    private List<Card> hand = new ArrayList<>();

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> sort(List<Card> hand) {

//        List<Faces> sortedFaces = hand.stream().map(card -> card.getFace()).sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).collect(Collectors.toList());
//        List<Card> sortedHand = new ArrayList<>();
//
//        for (var face : sortedFaces) {
//            sortedHand.add(new Card(face, face.getValue()));
//        }

        Collections.sort(hand, (o1, o2) -> {
            Integer o1Value = o1.getFace().getValue();
            Integer o2Value = o2.getFace().getValue();
            return o2Value.compareTo(o1Value);

        });
        return hand;
    }

    public HandResult analyze(List<Card> hand) {

        //start off with null handResult
        HandResult handResult = null;

        //check against all hands, if hand is any of the types then return that handResult...otherwise return null, so it continues with other checks

        handResult = containsRoyalFlush(hand);
        if (handResult != null) return handResult;

        handResult = containsStraightFlush(hand);
        if (handResult != null) return handResult;

        handResult = containsFourOfAKind(hand);
        if (handResult != null) return handResult;

        handResult = containsFullHouse(hand);
        if (handResult != null) return handResult;


        handResult = containsFlush(hand);
        if (handResult != null) return handResult;

        handResult = containsStraight(hand);
        if (handResult != null) return handResult;

        handResult = containsThreeOfAKind(hand);
        if (handResult != null) return handResult;

        handResult = containsTwoPair(hand);
        if (handResult != null) return handResult;

        handResult = containsPair(hand);
        if (handResult != null) return handResult;

        //if get all the way here, then hand is highcard
        Faces maxFace = hand.stream().map(card -> card.getFace()).sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).findFirst().orElse(null);
        List<Faces> remainingCards = hand.stream().filter(card -> card.getFace() != maxFace).map(card -> card.getFace()).collect(Collectors.toList());
        return new HandResult(PokerHandsEnum.HIGH_CARD, maxFace, remainingCards);
    }

    private HandResult containsRoyalFlush(List<Card> hand) {
        Integer maxValue = hand.stream().map(c -> c.getFace().getValue()).max(Integer::compare).orElse(0);
        var straightFlushResult = containsStraightFlush(hand);
        if (straightFlushResult != null && maxValue == 14) {
            return new HandResult(PokerHandsEnum.ROYAL_FLUSH, Faces.ACE, null);
        } else return null;
    }

    private HandResult containsStraightFlush(List<Card> hand) {
        var straightResult = containsStraight(hand);
        var flushResult = containsFlush(hand);
        if (straightResult != null && flushResult != null) {
            Faces highCardInType = hand.stream().map(card -> card.getFace()).sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).findFirst().orElseThrow();

            return new HandResult(PokerHandsEnum.STRAIGHT_FLUSH, highCardInType, null);
        } else return null;
    }

    private HandResult containsFourOfAKind(List<Card> hand) {
        Map<Faces, Integer> map = mapHand(hand);

        if (map.entrySet().stream().anyMatch(e -> e.getValue() == 4)) {
            Faces highCardInType = map.entrySet().stream().filter(card -> card.getValue() == 4).map(card -> card.getKey()).findFirst().orElseThrow();
            var remainingCards = hand.stream().filter(card -> card.getFace() != highCardInType).map(card -> card.getFace()).collect(Collectors.toList());
            return new HandResult(PokerHandsEnum.FOUR_OF_A_KIND, highCardInType, remainingCards);
        } else return null;
    }

    private HandResult containsFullHouse(List<Card> hand) {
        var threeOFAKindResult = containsThreeOfAKind(hand);
        var pairResult = containsPair(hand);
        if (threeOFAKindResult != null && pairResult != null) {
            var mappedHand = mapHand(hand);
            Faces highCardInType = mappedHand.entrySet().stream().filter(e -> e.getValue() == 3).map(e -> e.getKey()).findFirst().orElseThrow();
            //var remainingCards = hand.stream().filter(card -> card.getFace() != highCardInType).map(card -> card.getFace()).collect(Collectors.toList());
            return new HandResult(PokerHandsEnum.FULL_HOUSE, highCardInType, null);
        } else return null;
    }

    private HandResult containsFlush(List<Card> hand) {
        var suit = hand.get(0).getSuit();
        if (hand.stream().allMatch(e -> e.getSuit().equals(suit))) {
            Faces highCardInType = hand.stream().map(card -> card.getFace()).sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).findFirst().orElseThrow();
            return new HandResult(PokerHandsEnum.FLUSH, highCardInType, null);
        } else return null;
    }

    private HandResult containsStraight(List<Card> hand) {
        var minValue = hand.stream().map(c -> c.getFace().getValue()).min(Integer::compare).orElse(0);
        var maxValue = hand.stream().map(c -> c.getFace().getValue()).max(Integer::compare).orElse(0);
        var distinctCount = hand.stream().map(c -> c.getFace()).distinct().count();
        if (distinctCount == 5 && (maxValue - minValue) == 4) {
            Faces highCardInType = hand.stream().map(card -> card.getFace()).sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).findFirst().orElseThrow();
            return new HandResult(PokerHandsEnum.STRAIGHT, highCardInType, null);
        } else return null;
    }

    private HandResult containsThreeOfAKind(List<Card> hand) {
        Map<Faces, Integer> map = mapHand(hand);

        if (map.entrySet().stream().anyMatch(e -> e.getValue() == 3)) {
            Faces highCardInType = hand.stream().map(card -> card.getFace()).sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).findFirst().orElseThrow();
            var remainingCards = hand.stream().filter(card -> card.getFace() != highCardInType).map(card -> card.getFace()).collect(Collectors.toList());
            return new HandResult(PokerHandsEnum.THREE_OF_A_KIND, highCardInType, remainingCards);
        } else return null;
    }

    private HandResult containsTwoPair(List<Card> hand) {
        List<Faces> allFaces = new ArrayList<>();
        for (Card card : hand) {
            allFaces.add(card.getFace());
        }

        List<Faces> seenFaces = new ArrayList<>();
        int numOfPairs = 0;
        for (Faces face : allFaces) {
            if (seenFaces.contains(face)) {
                numOfPairs++;
            }
            seenFaces.add(face);
        }
        if (numOfPairs == 2) {
            Faces highCardInType = mapHand(hand).entrySet().stream().filter(e -> e.getValue() == 2).map(e -> e.getKey()).sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).findFirst().orElseThrow();
            var remainingCards = mapHand(hand).entrySet().stream().filter(e -> e.getValue() != 2).map(e -> e.getKey()).collect(Collectors.toList());
            return new HandResult(PokerHandsEnum.TWO_PAIR, highCardInType, remainingCards);
        } else return null;
    }


    private HandResult containsPair(List<Card> hand) {
        Map<Faces, Integer> map = mapHand(hand);

        if (mapHand(hand).entrySet().stream().anyMatch(e -> e.getValue() == 2)) {
            Faces highCardInType = mapHand(hand).entrySet().stream().filter(e -> e.getValue() == 2).map(e -> e.getKey()).findFirst().orElseThrow();
            var remainingCards = hand.stream().filter(card -> card.getFace() != highCardInType).map(card -> card.getFace()).collect(Collectors.toList());
            return new HandResult(PokerHandsEnum.PAIR, highCardInType, remainingCards);
        } else return null;
    }

    private Map<Faces, Integer> mapHand(List<Card> hand) {
        Map<Faces, Integer> map = new HashMap<>();
        for (Card card : hand) {
            if (map.containsKey(card.getFace())) {
                map.put(card.getFace(), map.get(card.getFace()) + 1);
            } else map.put(card.getFace(), 1);
        }
        return map;
    }

    public int compare(List<Card> x, List<Card> y) {
        //analyze both hands
        HandResult xHand = this.analyze(x);
        HandResult yHand = this.analyze(y);

        //if both are same handType (ex: Pair)
        if (xHand.getHandType() == yHand.getHandType()) {
            //if highcards included in handtype are equal (e.g. both have a pair of 3s) check remaining cards against each other one hand has a higher card
            //ONLY applicable to High Card, Pair, and Two Pair
            if ((xHand.getHighCardInType() == yHand.getHighCardInType()) && ((xHand.getHandType().equals(PokerHandsEnum.HIGH_CARD)
                            || xHand.getHandType().equals(PokerHandsEnum.PAIR)
                            || xHand.getHandType().equals(PokerHandsEnum.TWO_PAIR)))) {

                var xValuesOfRemainingFaces = new ArrayList<Integer>();
                var yValuesOfRemainingFaces = new ArrayList<Integer>();

                for(var item : xHand.getRemainingFaces()) {
                    xValuesOfRemainingFaces.add(item.getValue());
                }

                for(var item : yHand.getRemainingFaces()) {
                    yValuesOfRemainingFaces.add(item.getValue());
                }

                for (int i = 0; i < xValuesOfRemainingFaces.size(); i++) {
                    if (xValuesOfRemainingFaces.get(i) != yValuesOfRemainingFaces.get(i)) {
                        return Integer.compare(xValuesOfRemainingFaces.get(i), yValuesOfRemainingFaces.get(i));
                    }
                }
            }
            //if highcards are different, then just compare the highcards in type (eg: pair of 3s is higher than pair of 2s)
            return Integer.compare(xHand.getHighCardInType().getValue(), yHand.getHighCardInType().getValue());
        }
        //otherwise compare the handtypes and return greater value (Flush better than Pair)
        return Integer.compare(xHand.getHandType().getValue(), yHand.getHandType().getValue());
    }
}

