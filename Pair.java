//Created by Chad Carter. Edited further by Joey Gale
//2/6/19

import java.util.Arrays;

public class Pair extends CardClassifier implements Cards {

    public static String[] isPair(String[] playerHand) { //good to go!

        int cardsInFinalHand = 5;
        boolean pair = false;
        boolean straightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(playerHand));
        boolean flush = HasHand.checkForHand(Flush.isFlush(playerHand));
        boolean royalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(playerHand));
        boolean straight = HasHand.checkForHand(Straight.isStraight(playerHand));
        boolean quads = HasHand.checkForHand(Quads.is4ofAKind(playerHand));

        int[][] playerCards = representHand(playerHand);
        int[] pairOrTripsTracker = new int[2]; //Keeps track of the number of pairs and 3-of-a-kinds (trips)
                                               //pairOrTripsTracker[0] = count of pairs, pairOrTripsTracker[1] = count of trips
        int[] countOfEachRank = computeRankCount(playerCards);
        int numRanks = 13;
        int numSuits = 4;
        int cardOrder = 0;
        int positionOfPair = 0;
        String[] returnedPair = new String[cardsInFinalHand];

        //System.out.print("Pair? ");
        for (int i = 0; i < numRanks; i++) {

            if (countOfEachRank[i] == 2) {
                pairOrTripsTracker[0]++; //increases pair count by 1
                positionOfPair = i;
            }

            else if (countOfEachRank[i] == 3) {
                pairOrTripsTracker[1]++; //increases trips count by 1
            }

            else pairOrTripsTracker[0]+=0;
        }

        if (pairOrTripsTracker[0] == 1 && pairOrTripsTracker[1] == 0) {
            pair = true;
            for (int i = 0; i < numSuits; i++) {
                if (playerCards[i][positionOfPair] == 1) {
                    returnedPair[cardOrder] = allCards[i][positionOfPair];
                    cardOrder++;
                }
            }
        }

        for (int m = numRanks - 1; m >= 0; m--) { //checks ranks from highest ranked card to lowest ranked for final 2 cards of hand
            if (m != positionOfPair && countOfEachRank[m] == 1 && cardOrder < 5) { //highest card ranks not of the pair are found
                //terminates when returnedPair is full
                for (int n = 0; n < numSuits; n++) {

                    if (playerCards[n][m] == 1) {
                        returnedPair[cardOrder] = allCards[n][m];
                        cardOrder++;
                    }

                }
            }
        }

        if (pair && !royalFlush && !straightFlush && !flush && !straight &&!quads) {
            return returnedPair;
        }
        else returnedPair = emptyHand;

        return returnedPair;
    }

    public static void main(String[] args) {
        String[] bestPair = {"2-spades", "4-diamonds", "3-spades", "7-clubs", "K-spades", "A-diamonds", "A-spades"};
        String[] weakPair = {"2-spades", "2-diamonds", "4-spades", "7-clubs", "K-spades", "A-diamonds", "9-spades"};
        String[] twoPair1 = {"2-spades", "4-diamonds", "4-spades", "7-clubs", "7-spades", "J-diamonds", "J-spades"};
        String[] twoPair2 = {"2-spades", "2-diamonds", "3-spades", "3-clubs", "4-spades", "4-diamonds", "5-spades"};
        String[] trips1 = {"A-hearts", "A-spades", "A-diamonds", "4-hearts", "5-clubs", "K-clubs", "7-hearts"};
        String[] straight1 = {"6-spades", "5-spades", "4-clubs", "3-diamonds", "2-hearts", "A-clubs", "2-diamonds"};
        String[] straightFlush = {"6-spades", "5-spades", "4-spades", "3-spades", "2-spades", "A-clubs", "2-diamonds"};
        String[] royalFlush = {"A-spades", "K-spades", "Q-spades", "J-spades", "10-spades", "A-clubs", "2-diamonds"};
        String[] flush = {"6-spades", "5-spades", "4-hearts", "3-spades", "2-spades", "A-spades", "2-diamonds"};
        String[] fullHouse = {"A-spades", "A-clubs", "A-hearts", "J-diamonds", "K-clubs", "Q-hearts", "J-hearts"};
        System.out.println(Arrays.toString(isPair(bestPair)) + "   " + HasHand.checkForHand(isPair(bestPair)));
        System.out.println(Arrays.toString(isPair(twoPair1)) + "   " + HasHand.checkForHand(isPair(twoPair1)));
        System.out.println(Arrays.toString(isPair(twoPair2)) + "   " + HasHand.checkForHand(isPair(twoPair2)));
        System.out.println(Arrays.toString(isPair(weakPair)) + "   " + HasHand.checkForHand(isPair(weakPair)));
        System.out.println(Arrays.toString(isPair(trips1)) + "   " + HasHand.checkForHand(isPair(trips1)));
        System.out.println(Arrays.toString(isPair(straight1)) + "   " + HasHand.checkForHand(isPair(straight1)));
        System.out.println(Arrays.toString(isPair(straightFlush)) + "   " + HasHand.checkForHand(isPair(straightFlush)));
        System.out.println(Arrays.toString(isPair(royalFlush)) + "   " + HasHand.checkForHand(isPair(royalFlush)));
        System.out.println(Arrays.toString(isPair(flush)) + "   " + HasHand.checkForHand(isPair(flush)));
        System.out.println(Arrays.toString(isPair(fullHouse)) + "   " + HasHand.checkForHand(isPair(fullHouse)));
    }

}
