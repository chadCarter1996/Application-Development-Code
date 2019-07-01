//Created by Chad Carter. Further edited by Joey Gale
//2/5/19

import java.util.Arrays;

public class Flush extends CardClassifier implements Cards {

    public static String[] isFlush(String[] playerHand) { //good to go!

        int cardsInFinalHand = 5;
        boolean flush = false;
        int[][] playerCards = representHand(playerHand);
        int[] flushEvaluater = computeSuitCount(playerCards);
        int numSuits = 4;
        int numRanks = 13;
        int cardOrder = 0;
        String[] bestFlush = new String[cardsInFinalHand]; //add to SortedDeck interface...
        boolean straightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(playerHand));
        boolean royalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(playerHand));


        //System.out.print("Flush? ");
        for (int i = 0; i < numSuits; i++) { //need to terminate loo when best flush is found...
            if (flushEvaluater[i] >= 5 && !straightFlush && !royalFlush) {
                flush = true;

                for (int j = numRanks - 1; j >= 0; j--) {
                    if (playerCards[i][j] == 1 && cardOrder < 5) {
                        bestFlush[cardOrder] = allCards[i][j];
                        cardOrder++;
                    }
                }
            }
        }

        if (flush) { //or else this will print 8 times
            return bestFlush;
        }

        else bestFlush = emptyHand;

        return bestFlush;

    }

    /*public static String calculateProbabilityOfFlush(String[] playerHand) {

        String finalMessage = "";
        for (card: ) {

        }

    }*/


    public static void main(String[] args) {
        String[] flush = {"2-clubs", "3-clubs", "7-clubs", "8-clubs", "6-clubs", "5-diamonds", "A-clubs"};
        String[] flush2 = {"2-spades", "3-spades", "7-spades", "8-spades", "6-spades", "5-diamonds", "A-clubs"};
        String[] straightFlush = {"2-clubs", "3-clubs", "4-clubs", "5-clubs", "6-clubs", "5-diamonds", "A-clubs"};
        String[] notFlush = {"2-clubs", "3-clubs", "4-diamonds", "5-clubs", "6-clubs", "5-diamonds", "A-spades"};
        String[] royalFlush = {"K-clubs", "Q-clubs", "10-clubs", "J-clubs", "6-clubs", "5-diamonds", "A-clubs"};

        System.out.println(Arrays.toString(isFlush(flush)) + "     " +  HasHand.checkForHand(isFlush(flush)));
        System.out.println(Arrays.toString(isFlush(flush2)) + "     " +  HasHand.checkForHand(isFlush(flush2)));
        System.out.println(Arrays.toString(isFlush(straightFlush)) + "     " +  HasHand.checkForHand(isFlush(straightFlush)));
        System.out.println(Arrays.toString(isFlush(notFlush)) + "     " +  HasHand.checkForHand(isFlush(notFlush)));
        System.out.println(Arrays.toString(isFlush(royalFlush)) + "     " +  HasHand.checkForHand(isFlush(royalFlush)));

        //add tests here...
    }

}
