//created by Chad Carter and Joey Gale
//1/30/19

import java.util.Arrays;

public class RoyalFlush extends CardClassifier implements Cards{

    public static String[] isRoyalFlush(String[] playerHand) { //good to go!

        int numSuits = 4;
        boolean royalFlush = false;
        int[][] playerCards = representHand(playerHand);
        String[] returnedRoyalFlush = new String[5];
        //returnedRoyalFlush = emptyHand;
        int suitOfRF = -1;

        //System.out.print("Royal flush? ");
        for (int i = 0; i < numSuits; i++) {
            if (playerCards[i][8] == 1 && playerCards[i][9] == 1 && playerCards[i][10] == 1 && playerCards[i][11] == 1 && playerCards[i][12] ==1) {
                royalFlush = true; //royal flush is found
                suitOfRF = i;
                break;
            }
        }

        if (royalFlush) {
            returnedRoyalFlush[0] = allCards[suitOfRF][12]; //A-suit
            returnedRoyalFlush[1] = allCards[suitOfRF][11]; //K-suit
            returnedRoyalFlush[2] = allCards[suitOfRF][10]; //Q-suit
            returnedRoyalFlush[3] = allCards[suitOfRF][9]; //J-suit
            returnedRoyalFlush[4] = allCards[suitOfRF][8]; //10-suit
        //    System.out.println("|" + returnedRoyalFlush[0] + "| |" + returnedRoyalFlush[1] + "| |" + returnedRoyalFlush[2] +
        //            "| |" + returnedRoyalFlush[3] + "| |" + returnedRoyalFlush[4] + "|"); //displays the royal flush
        }
        else returnedRoyalFlush = emptyHand;

        return returnedRoyalFlush;

    }

    public static void main(String[] args) {

        String[][] hand1 = {{"2-hearts", "6-clubs", "10-spades", "J-spades", "Q-spades", "K-spades", "A-spades"}, //royal flush

        {"2-hearts", "6-clubs", "10-spades", "J-spades", "Q-spades", "K-spades", "A-hearts"}, //not

        {"10-spades", "J-spades", "6-clubs", "Q-spades", "K-spades", "A-spades", "2-hearts"}, //royal flush

        {"2-hearts", "10-hearts", "J-hearts", "Q-hearts", "6-clubs", "K-hearts", "A-hearts"}, //royal flush

        {"9-hearts", "10-hearts", "J-hearts", "Q-hearts", "6-clubs", "K-hearts", "A-spades"}, //not

        {"9-diamonds", "10-diamonds", "J-diamonds", "Q-diamonds", "6-diamonds", "K-diamonds", "A-diamonds"}, //royal flush

        {"10-diamonds", "10-clubs", "J-diamonds", "Q-clubs", "J-clubs", "K-clubs", "A-clubs"}}; //royal flush

        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.toString(RoyalFlush.isRoyalFlush(hand1[i])));
        }
        //System.out.println(Arrays.toString(isRoyalFlush(hand1)));
        //System.out.println(Arrays.toString(isRoyalFlush(hand2)));
        //System.out.println(Arrays.toString(isRoyalFlush(hand3)));
        //System.out.println(Arrays.toString(isRoyalFlush(hand4)));
        //System.out.println(Arrays.toString(isRoyalFlush(hand2)));
        //System.out.println(Arrays.toString(isRoyalFlush(hand5)));
        //System.out.println(Arrays.toString(isRoyalFlush(hand6)));
        //System.out.println(Arrays.toString(isRoyalFlush(hand7)));

    }
}
