import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerTests {
    HandAnalyzer handAnalyzer = new HandAnalyzer();


    @Test
    public void Analyze_Should_Recognize_A_Royal_Flush() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.ACE, Suits.SPADES));
        hand.add(new Card(Faces.JACK, Suits.SPADES));
        hand.add(new Card(Faces.QUEEN, Suits.SPADES));
        hand.add(new Card(Faces.TEN, Suits.SPADES));
        hand.add(new Card(Faces.KING, Suits.SPADES));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCardInType = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();


        //Assert
        assertEquals(PokerHandsEnum.ROYAL_FLUSH, result);
        assertEquals(Faces.ACE, highCardInType);
        assertEquals(null, remainingCards);
    }

    @Test
    public void Analyze_Should_Recognize_A_Straight_Flush() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.TWO, Suits.CLUBS));
        hand.add(new Card(Faces.THREE, Suits.CLUBS));
        hand.add(new Card(Faces.FOUR, Suits.CLUBS));
        hand.add(new Card(Faces.FIVE, Suits.CLUBS));
        hand.add(new Card(Faces.SIX, Suits.CLUBS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCard = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingFaces = handAnalyzer.analyze(hand).getRemainingFaces();

        //Assert
        assertEquals(PokerHandsEnum.STRAIGHT_FLUSH, result);
        assertEquals(Faces.SIX, highCard);
        assertEquals(null, remainingFaces);

    }

    @Test
    public void Analyze_Should_Recognize_A_Four_Of_A_Kind() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.ACE, Suits.CLUBS));
        hand.add(new Card(Faces.ACE, Suits.HEARTS));
        hand.add(new Card(Faces.ACE, Suits.DIAMONDS));
        hand.add(new Card(Faces.TEN, Suits.SPADES));
        hand.add(new Card(Faces.ACE, Suits.SPADES));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCardInType = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();
        var expectedListOfRemainingFaces = new ArrayList<Faces>();
        expectedListOfRemainingFaces.add(Faces.TEN);

        //Assert
        assertEquals(PokerHandsEnum.FOUR_OF_A_KIND, result);
        assertEquals(Faces.ACE, highCardInType);
        assertThat(remainingCards, containsInAnyOrder(expectedListOfRemainingFaces.toArray()));
    }

    @Test
    public void Analyze_Should_Recognize_A_Full_House() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.TWO, Suits.CLUBS));
        hand.add(new Card(Faces.TWO, Suits.DIAMONDS));
        hand.add(new Card(Faces.SEVEN, Suits.CLUBS));
        hand.add(new Card(Faces.SEVEN, Suits.SPADES));
        hand.add(new Card(Faces.SEVEN, Suits.DIAMONDS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCardInType = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();


        //Assert
        assertEquals(PokerHandsEnum.FULL_HOUSE, result);
        assertEquals(Faces.SEVEN, highCardInType);
        assertEquals(null, remainingCards);

    }
    @Test
    public void Analyze_Should_Recognize_A_Flush() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.TWO, Suits.CLUBS));
        hand.add(new Card(Faces.TEN, Suits.CLUBS));
        hand.add(new Card(Faces.SEVEN, Suits.CLUBS));
        hand.add(new Card(Faces.FIVE, Suits.CLUBS));
        hand.add(new Card(Faces.JACK, Suits.CLUBS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCardInType = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();


        //Assert
        assertEquals(PokerHandsEnum.FLUSH, result);
        assertEquals(Faces.JACK, highCardInType);
        assertEquals(null, remainingCards);

    }

    @Test
    public void Analyze_Should_Recognize_A_Straight() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.TWO, Suits.DIAMONDS));
        hand.add(new Card(Faces.THREE, Suits.CLUBS));
        hand.add(new Card(Faces.FOUR, Suits.CLUBS));
        hand.add(new Card(Faces.FIVE, Suits.CLUBS));
        hand.add(new Card(Faces.SIX, Suits.CLUBS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCardInType = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();


        //Assert
        assertEquals(PokerHandsEnum.STRAIGHT, result);
        assertEquals(Faces.SIX, highCardInType);
        assertEquals(null, remainingCards);

    }

    @Test
    public void Analyze_Should_Recognize_A_Three_Of_A_Kind() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.THREE, Suits.CLUBS));
        hand.add(new Card(Faces.TWO, Suits.DIAMONDS));
        hand.add(new Card(Faces.SEVEN, Suits.CLUBS));
        hand.add(new Card(Faces.SEVEN, Suits.SPADES));
        hand.add(new Card(Faces.SEVEN, Suits.DIAMONDS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCardInType = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();
        var expectedListOfRemainingFaces = new ArrayList<Faces>();
        expectedListOfRemainingFaces.add(Faces.TWO);
        expectedListOfRemainingFaces.add(Faces.THREE);

        //Assert
        assertEquals(PokerHandsEnum.THREE_OF_A_KIND, result);
        assertEquals(Faces.SEVEN, highCardInType);
        assertThat(remainingCards, containsInAnyOrder(expectedListOfRemainingFaces.toArray()));
    }


    @Test
    public void Analyze_Should_Recognize_A_Two_Pair() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.TWO, Suits.CLUBS));
        hand.add(new Card(Faces.JACK, Suits.DIAMONDS));
        hand.add(new Card(Faces.JACK, Suits.HEARTS));
        hand.add(new Card(Faces.FIVE, Suits.CLUBS));
        hand.add(new Card(Faces.TWO, Suits.HEARTS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCardInType = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();
        var expectedListOfRemainingFaces = new ArrayList<Faces>();
        expectedListOfRemainingFaces.add(Faces.FIVE);

        //Assert
        assertEquals(PokerHandsEnum.TWO_PAIR, result);
        assertEquals(Faces.JACK, highCardInType);
        assertThat(remainingCards, containsInAnyOrder(expectedListOfRemainingFaces.toArray()));
    }

    @Test
    public void Analyze_Should_Recognize_A_Pair() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.TWO, Suits.CLUBS));
        hand.add(new Card(Faces.JACK, Suits.DIAMONDS));
        hand.add(new Card(Faces.QUEEN, Suits.HEARTS));
        hand.add(new Card(Faces.FIVE, Suits.CLUBS));
        hand.add(new Card(Faces.TWO, Suits.HEARTS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCIT = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();


        var expectedListOfRemainingFaces = new ArrayList<Faces>();
        expectedListOfRemainingFaces.add(Faces.JACK);
        expectedListOfRemainingFaces.add(Faces.FIVE);
        expectedListOfRemainingFaces.add(Faces.QUEEN);

        //Assert
        assertEquals(PokerHandsEnum.PAIR, result);
        assertEquals(Faces.TWO, highCIT);
        assertThat(remainingCards, containsInAnyOrder(expectedListOfRemainingFaces.toArray()));

    }

    @Test
    public void Analyze_Should_Recognize_A_High_Card() {
        //Arrange
        List<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Faces.TWO, Suits.CLUBS));
        hand.add(new Card(Faces.KING, Suits.DIAMONDS));
        hand.add(new Card(Faces.JACK, Suits.HEARTS));
        hand.add(new Card(Faces.FIVE, Suits.CLUBS));
        hand.add(new Card(Faces.EIGHT, Suits.HEARTS));

        //Act
        var result = handAnalyzer.analyze(hand).getHandType();
        var highCIT = handAnalyzer.analyze(hand).getHighCardInType();
        var remainingCards = handAnalyzer.analyze(hand).getRemainingFaces();

        var expectedListOfRemainingFaces = new ArrayList<Faces>();
        expectedListOfRemainingFaces.add(Faces.TWO);
        expectedListOfRemainingFaces.add(Faces.FIVE);
        expectedListOfRemainingFaces.add(Faces.EIGHT);
        expectedListOfRemainingFaces.add(Faces.JACK);

        //Assert
        assertEquals(PokerHandsEnum.HIGH_CARD, result);
        assertEquals(Faces.KING, highCIT);
        assertEquals(expectedListOfRemainingFaces, remainingCards);
    }

    @Test
    public void Compare_Should_Return_Neg_One_When_X_Is_Better_And_Enums_Are_Different() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.TWO, Suits.CLUBS));
        x.add(new Card(Faces.TWO, Suits.DIAMONDS));
        x.add(new Card(Faces.JACK, Suits.HEARTS));
        x.add(new Card(Faces.JACK, Suits.CLUBS));
        x.add(new Card(Faces.EIGHT, Suits.HEARTS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.CLUBS));
        y.add(new Card(Faces.TWO, Suits.DIAMONDS));
        y.add(new Card(Faces.JACK, Suits.HEARTS));
        y.add(new Card(Faces.JACK, Suits.CLUBS));
        y.add(new Card(Faces.EIGHT, Suits.HEARTS));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void Compare_Should_Return_One_When_Y_Is_Better_And_Enums_Are_Different() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.THREE, Suits.CLUBS));
        x.add(new Card(Faces.TWO, Suits.DIAMONDS));
        x.add(new Card(Faces.JACK, Suits.HEARTS));
        x.add(new Card(Faces.JACK, Suits.CLUBS));
        x.add(new Card(Faces.EIGHT, Suits.HEARTS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.CLUBS));
        y.add(new Card(Faces.TWO, Suits.DIAMONDS));
        y.add(new Card(Faces.TWO, Suits.HEARTS));
        y.add(new Card(Faces.TWO, Suits.CLUBS));
        y.add(new Card(Faces.THREE, Suits.HEARTS));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }

    @Test
    public void Compare_Should_Return_0_When_X_And_Y_Are_Exact_Same() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.THREE, Suits.CLUBS));
        x.add(new Card(Faces.THREE, Suits.DIAMONDS));
        x.add(new Card(Faces.JACK, Suits.HEARTS));
        x.add(new Card(Faces.JACK, Suits.CLUBS));
        x.add(new Card(Faces.EIGHT, Suits.HEARTS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.SPADES));
        y.add(new Card(Faces.JACK, Suits.SPADES));
        y.add(new Card(Faces.THREE, Suits.HEARTS));
        y.add(new Card(Faces.JACK, Suits.DIAMONDS));
        y.add(new Card(Faces.EIGHT, Suits.CLUBS));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(0, result);
    }

    @Test
    public void Compare_Should_Return_0_When_X_And_Y_Are_Both_Royal_Flush() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.ACE, Suits.CLUBS));
        x.add(new Card(Faces.KING, Suits.CLUBS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));
        x.add(new Card(Faces.JACK, Suits.CLUBS));
        x.add(new Card(Faces.TEN, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.ACE, Suits.SPADES));
        y.add(new Card(Faces.KING, Suits.SPADES));
        y.add(new Card(Faces.QUEEN, Suits.SPADES));
        y.add(new Card(Faces.JACK, Suits.SPADES));
        y.add(new Card(Faces.TEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(0, result);
    }

    @Test
    public void Compare_Should_Return_0_When_X_And_Y_Are_Both_Same_High_Card() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.ACE, Suits.DIAMONDS));
        x.add(new Card(Faces.TWO, Suits.CLUBS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));
        x.add(new Card(Faces.JACK, Suits.CLUBS));
        x.add(new Card(Faces.TEN, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.ACE, Suits.DIAMONDS));
        y.add(new Card(Faces.TWO, Suits.SPADES));
        y.add(new Card(Faces.QUEEN, Suits.SPADES));
        y.add(new Card(Faces.JACK, Suits.SPADES));
        y.add(new Card(Faces.TEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(0, result);
    }

    @Test
    public void Compare_Should_Return_One_When_X_And_Y_Are_Both_High_Card_But_X_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.ACE, Suits.HEARTS));
        x.add(new Card(Faces.KING, Suits.CLUBS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));
        x.add(new Card(Faces.JACK, Suits.CLUBS));
        x.add(new Card(Faces.NINE, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.ACE, Suits.DIAMONDS));
        y.add(new Card(Faces.KING, Suits.SPADES));
        y.add(new Card(Faces.QUEEN, Suits.SPADES));
        y.add(new Card(Faces.JACK, Suits.SPADES));
        y.add(new Card(Faces.THREE, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void Compare_Should_Return_One_When_X_And_Y_Are_Both_Straight_But_Y_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.TWO, Suits.DIAMONDS));
        x.add(new Card(Faces.THREE, Suits.CLUBS));
        x.add(new Card(Faces.FOUR, Suits.CLUBS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.SIX, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.DIAMONDS));
        y.add(new Card(Faces.FOUR, Suits.SPADES));
        y.add(new Card(Faces.FIVE, Suits.SPADES));
        y.add(new Card(Faces.SIX, Suits.SPADES));
        y.add(new Card(Faces.SEVEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }

    @Test
    public void Compare_Should_Return_One_When_X_And_Y_Are_Both_Flush_But_Y_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.FOUR, Suits.CLUBS));
        x.add(new Card(Faces.THREE, Suits.CLUBS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.KING, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.ACE, Suits.SPADES));
        y.add(new Card(Faces.FOUR, Suits.SPADES));
        y.add(new Card(Faces.JACK, Suits.SPADES));
        y.add(new Card(Faces.SIX, Suits.SPADES));
        y.add(new Card(Faces.SEVEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }

    @Test
    public void Compare_Should_Return_One_When_X_And_Y_Are_Both_Straight_Flush_But_Y_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.TWO, Suits.CLUBS));
        x.add(new Card(Faces.THREE, Suits.CLUBS));
        x.add(new Card(Faces.FOUR, Suits.CLUBS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.SIX, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.SPADES));
        y.add(new Card(Faces.FOUR, Suits.SPADES));
        y.add(new Card(Faces.FIVE, Suits.SPADES));
        y.add(new Card(Faces.SIX, Suits.SPADES));
        y.add(new Card(Faces.SEVEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }

    @Test
    public void Compare_Should_Return_Neg_One_When_X_And_Y_Are_Both_Pair_But_Y_is_Better_Pair() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.THREE, Suits.CLUBS));
        x.add(new Card(Faces.THREE, Suits.DIAMONDS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));
        x.add(new Card(Faces.SEVEN, Suits.CLUBS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.SPADES));
        y.add(new Card(Faces.THREE, Suits.HEARTS));
        y.add(new Card(Faces.QUEEN, Suits.SPADES));
        y.add(new Card(Faces.SIX, Suits.SPADES));
        y.add(new Card(Faces.SEVEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }

    @Test
    public void Compare_Should_Return_One_When_X_And_Y_Are_Both_SAME_Pair_But_Y_has_High_Card() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.TWO, Suits.CLUBS));
        x.add(new Card(Faces.TWO, Suits.HEARTS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.SIX, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.TWO, Suits.SPADES));
        y.add(new Card(Faces.TWO, Suits.DIAMONDS));
        y.add(new Card(Faces.FIVE, Suits.SPADES));
        y.add(new Card(Faces.KING, Suits.SPADES));
        y.add(new Card(Faces.SEVEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }

    @Test
    public void Compare_Should_Return_Neg_One_When_X_And_Y_Are_Both_Four_Of_A_Kind_But_X_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.QUEEN, Suits.SPADES));
        x.add(new Card(Faces.QUEEN, Suits.HEARTS));
        x.add(new Card(Faces.QUEEN, Suits.DIAMONDS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.SPADES));
        y.add(new Card(Faces.THREE, Suits.HEARTS));
        y.add(new Card(Faces.THREE, Suits.DIAMONDS));
        y.add(new Card(Faces.THREE, Suits.CLUBS));
        y.add(new Card(Faces.SEVEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void Compare_Should_Return_Neg_One_When_X_And_Y_Are_Both_Three_Of_A_Kind_But_X_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.QUEEN, Suits.SPADES));
        x.add(new Card(Faces.QUEEN, Suits.HEARTS));
        x.add(new Card(Faces.EIGHT, Suits.DIAMONDS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.SPADES));
        y.add(new Card(Faces.THREE, Suits.HEARTS));
        y.add(new Card(Faces.THREE, Suits.DIAMONDS));
        y.add(new Card(Faces.KING, Suits.CLUBS));
        y.add(new Card(Faces.SEVEN, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }


    @Test
    public void Compare_Should_Return_Neg_One_When_X_And_Y_Are_Both_Full_House_But_Xs_Three_Group_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.QUEEN, Suits.SPADES));
        x.add(new Card(Faces.QUEEN, Suits.HEARTS));
        x.add(new Card(Faces.FIVE, Suits.DIAMONDS));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.QUEEN, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.SPADES));
        y.add(new Card(Faces.THREE, Suits.HEARTS));
        y.add(new Card(Faces.THREE, Suits.DIAMONDS));
        y.add(new Card(Faces.KING, Suits.CLUBS));
        y.add(new Card(Faces.KING, Suits.SPADES));

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void Compare_Should_Return_One_When_X_And_Y_Are_Both_Two_Pair_But_One_Of_Ys_Pair_is_Higher() {
        //Arrange
        List<Card> x = new ArrayList<Card>();
        x.add(new Card(Faces.QUEEN, Suits.SPADES));
        x.add(new Card(Faces.QUEEN, Suits.HEARTS));
        x.add(new Card(Faces.FIVE, Suits.SPADES));
        x.add(new Card(Faces.FIVE, Suits.CLUBS));
        x.add(new Card(Faces.ACE, Suits.CLUBS));

        List<Card> y = new ArrayList<Card>();
        y.add(new Card(Faces.THREE, Suits.SPADES));
        y.add(new Card(Faces.THREE, Suits.HEARTS));
        y.add(new Card(Faces.KING, Suits.DIAMONDS));
        y.add(new Card(Faces.KING, Suits.HEARTS));
        y.add(new Card(Faces.TWO, Suits.SPADES));//fails when this is King

        //Act
        var result = handAnalyzer.compare(x,y);

        //Assert
        assertEquals(-1, result);
    }
}
