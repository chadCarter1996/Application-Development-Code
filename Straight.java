//Created by Chad Carter. Further edited by Joey Gale.
//2/5/19

import java.util.Arrays;

public class Straight extends CardClassifier implements Cards{

    public static String[] isStraight(String[] playerHand) { //good to go!

        int cardsInFinalHand = 5;
        boolean straight = false;
        boolean straightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(playerHand));
        boolean flush = HasHand.checkForHand(Flush.isFlush(playerHand));
        boolean royalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(playerHand));
        int[][] playerCards = representHand(playerHand);
        int[] straightEvaluater = computeRankCount(playerCards);
        final int numRanks = 13;
        final int numSuits = 4;
        int cardOrder = 0;
        String[] bestStraight = new String[cardsInFinalHand];
        int highestStraightSignifier = 0; //helps prevent an override of 5-4-3-2-A over 6-5-4-3-2
                                          // (6-5-4-3-2 is the better straight)

        //System.out.print("Straight? ");
        for (int i = (numRanks - 1); i >= 4; i--) { //fix excessive returned prints

            if (straightEvaluater[i] >= 1 && straightEvaluater[i-1] >= 1 && straightEvaluater[i-2] >= 1
            && straightEvaluater[i-3] >= 1 && straightEvaluater[i-4] >= 1 && !straight) { //checks for a straight
                straight = true; //a straight is found
                for (int j = 0; j < numSuits; j++) {
                    if (playerCards[j][i-4] == 1) { //the lowest card of the straight
                        bestStraight[cardOrder + 4] = allCards[j][i-4];
                    } //repeat for rest of straight cards
                    if (playerCards[j][i-3] == 1) {
                        bestStraight[cardOrder + 3] = allCards[j][i-3]; //2nd lowest card of the straight
                    }
                    if (playerCards[j][i-2] == 1) { //etc
                        bestStraight[cardOrder + 2] = allCards[j][i-2];
                    }
                    if (playerCards[j][i-1] == 1) { //etc
                        bestStraight[cardOrder + 1] = allCards[j][i-1];
                    }
                    if (playerCards[j][i] == 1) { //highest card of the straight
                        bestStraight[cardOrder] = allCards[j][i];
                    }
                }
            }
        }

        if (straightEvaluater[12] >= 1 && straightEvaluater[0] >= 1 && straightEvaluater[1] >= 1
                && straightEvaluater[2] >= 1 && straightEvaluater[3] >= 1 && !straight) { //special case of straight: 5-4-3-2-A

            straight = true; //a 5-4-3-2-A straight is found

            for (int j = 0; j < numSuits; j++) {
                if (playerCards[j][12] == 1) { //the lowest card of the straight
                    bestStraight[cardOrder + 4] = allCards[j][12];
                } //repeat for rest of straight cards
                if (playerCards[j][0] == 1) {
                    bestStraight[cardOrder + 3] = allCards[j][0]; //2nd lowest card of the straight
                }
                if (playerCards[j][1] == 1) { //etc
                    bestStraight[cardOrder + 2] = allCards[j][1];
                }
                if (playerCards[j][2] == 1) { //etc
                    bestStraight[cardOrder + 1] = allCards[j][2];
                }
                if (playerCards[j][3] == 1) { //highest card of the straight
                    bestStraight[cardOrder] = allCards[j][3];
                }
            }
        }

        if (straight && !straightFlush && !flush && !royalFlush) {
            return bestStraight;
        }
        else {
            bestStraight = emptyHand;
        }

        return bestStraight;
    }

    public static void main(String[] args) {
        String[] straight1 = {"6-spades", "5-spades", "4-clubs", "3-diamonds", "2-hearts", "A-clubs", "2-diamonds"};
        String[] straightFlush = {"6-spades", "5-spades", "4-spades", "3-spades", "2-spades", "A-clubs", "2-diamonds"};
        String[] royalFlush = {"A-spades", "K-spades", "Q-spades", "J-spades", "10-spades", "A-clubs", "2-diamonds"};
        String[] flush = {"6-spades", "5-spades", "4-hearts", "3-spades", "2-spades", "A-spades", "2-diamonds"};
        String[] problemStraight = {"K-spades", "Q-clubs", "J-spades", "10-spades", "9-diamonds", "4-hearts", "A-clubs"};

        System.out.println(Arrays.toString(isStraight(straight1)) + "     "  + HasHand.checkForHand(isStraight(straight1)));
        System.out.println(Arrays.toString(isStraight(straightFlush)) + "     "  + HasHand.checkForHand(isStraight(straightFlush)));
        System.out.println(Arrays.toString(isStraight(royalFlush)) + "     "  + HasHand.checkForHand(isStraight(royalFlush)));
        System.out.println(Arrays.toString(isStraight(flush)) + "     "  + HasHand.checkForHand(isStraight(flush)));
        System.out.println(Arrays.toString(isStraight(problemStraight)) + "     "  + HasHand.checkForHand(isStraight(problemStraight)));

    }

}
