import java.util.Arrays;

public class HasHand extends CardClassifier implements Cards {

    public static boolean checkForHand(String[] playerHand) {

        return !Arrays.equals(playerHand, emptyHand);  //if player has a hand, the inputted array will NOT be empty
        //this checks to see if the player has a hand

    }

    public static void main(String[] args) {

        String[] hand1 = {"2-hearts", "3-clubs", "4-spades", "5-spades", "6-spades", "7-spades", "8-spades"}; //straight flush 8

        String[] hand2 = {"2-hearts", "6-clubs", "10-spades", "J-spades", "Q-spades", "K-spades", "A-spades"}; //straight flush K
        //also a royal flush

        String[] hand3 = {"A-spades", "2-spades", "3-spades", "4-spades", "5-spades", "8-spades", "2-hearts"}; //straight flush 5

        String[] hand4 = {"2-hearts", "3-hearts", "4-hearts", "5-hearts", "6-clubs", "6-hearts", "A-hearts"}; //straight flush 6

        String[] hand5 = {"9-hearts", "10-hearts", "J-hearts", "Q-hearts", "6-clubs", "K-hearts", "A-spades"}; //straight flush K

        String[] hand6 = {"8-diamonds", "9-diamonds", "10-diamonds", "J-diamonds", "6-diamonds", "K-spades", "Q-diamonds"}; //straight flush Q

        String[] hand7 = {"10-diamonds", "7-clubs", "J-diamonds", "6-clubs", "5-clubs", "4-clubs", "3-clubs"}; //straight flush 10

        String[] hand8 = {"9-spades", "8-spades", "7-spades", "6-spades", "5-spades", "4-spades", "3-spades"}; //straight flush 9

        String[] hand9 = {"J-hearts", "10-hearts", "10-spades", "9-hearts", "7-hearts", "8-clubs", "8-hearts"}; //straight flush J

        String[] hand10 = {"7-diamonds", "7-spades", "3-diamonds", "4-diamonds", "5-diamonds", "6-diamonds", "2-diamonds"}; //straight flush 7

        String[] hand11 = {"2-hearts", "3-hearts", "4-hearts", "5-hearts", "6-clubs", "7-hearts", "8-hearts"}; //not

        String[] hand12 = {"9-clubs", "10-hearts", "J-hearts", "Q-spades", "6-clubs", "K-hearts", "A-spades"}; //not

        String[] hand13 = {"9-diamonds", "10-diamonds", "J-diamonds", "Q-diamonds", "6-diamonds", "K-diamonds", "A-diamonds"}; //yes

        String[] hand14 = {"10-diamonds", "10-clubs", "J-diamonds", "Q-clubs", "J-clubs", "K-clubs", "A-clubs"}; //not

        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand1)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand2)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand3)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand4)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand5)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand6)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand7)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand8)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand9)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand10)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand11)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand12)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand13)));
        System.out.println(checkForHand(StraightFlush.isStraightFlush(hand14)));

    }


}
