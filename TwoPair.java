//Created by Chad Carter. Further edited by Joey Gale
//2/6/19

import java.util.Arrays;

public class TwoPair extends CardClassifier implements Cards {

    public static String[] isTwoPair(String[] playerHand) {

        int cardsInFinalHand = 5;
        boolean twoPair = false;
        boolean royalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(playerHand));
        boolean straightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(playerHand));
        boolean quads = HasHand.checkForHand(Quads.is4ofAKind(playerHand));
        boolean fullHouse = HasHand.checkForHand(FullHouse.isFullHouse(playerHand));
        boolean flush = HasHand.checkForHand(Flush.isFlush(playerHand));
        boolean straight = HasHand.checkForHand(Straight.isStraight(playerHand));
        boolean trips = HasHand.checkForHand(Trips.is3ofAKind(playerHand));

        int[][] playerCards = representHand(playerHand);
        int[] pairOrTripsTracker = new int[2]; //Keeps track of the number of pairs and 3-of-a-kinds (trips)
        //pairOrTripsTracker[0] = count of pairs, pairOrTripsTracker[1] = count of trips
        int[] countOfEachRank = computeRankCount(playerCards);
        int numRanks = 13;
        int numSuits = 4;
        int cardOrder = 0;
        String[] returnedTwoPair = new String[cardsInFinalHand];
        int maxNumOfPairs = 3; //a player holding two-pair can at most "hold" 3 pairs
        int[] positionsOfPairs = new int[maxNumOfPairs];

        //System.out.print("Two pair? ");
        for (int i = numRanks - 1; i >= 0 ; i--) {
            if (countOfEachRank[i] == 2) {
                pairOrTripsTracker[0]++;
                maxNumOfPairs--;
                positionsOfPairs[maxNumOfPairs] = i;
            }
            else if (countOfEachRank[i] == 3) {
                pairOrTripsTracker[1]++;
            }
            else pairOrTripsTracker[0]+=0;
        }
        //System.out.println(Arrays.toString(positionsOfPairs));

        if (pairOrTripsTracker[0] >= 2 && pairOrTripsTracker[1] == 0) {
            twoPair = true;

            //had to hard code this because the values got messed up without it.
            if (playerCards[0][positionsOfPairs[2]] == 1) {
                returnedTwoPair[cardOrder] = allCards[0][positionsOfPairs[2]]; //(top pair rank)-Spades
                cardOrder++;
            }

            if (playerCards[1][positionsOfPairs[2]] == 1) {
                returnedTwoPair[cardOrder] = allCards[1][positionsOfPairs[2]]; //(top pair rank)-Clubs
                cardOrder++;
            }

            if (playerCards[2][positionsOfPairs[2]] == 1) {
                returnedTwoPair[cardOrder] = allCards[2][positionsOfPairs[2]]; //(top pair rank)-Diamonds
                cardOrder++;
            }

            if (playerCards[3][positionsOfPairs[2]] == 1) {
                returnedTwoPair[cardOrder] = allCards[3][positionsOfPairs[2]]; //(top pair rank)-Hearts
                cardOrder++;
            }

            if (playerCards[0][positionsOfPairs[1]] == 1) {
                returnedTwoPair[cardOrder] = allCards[0][positionsOfPairs[1]]; //(bottom pair rank)-Spades
                cardOrder++;
            }

            if (playerCards[1][positionsOfPairs[1]] == 1) {
                returnedTwoPair[cardOrder] = allCards[1][positionsOfPairs[1]]; //(bottom pair rank)-Clubs
                cardOrder++;
            }

            if (playerCards[2][positionsOfPairs[1]] == 1) {
                returnedTwoPair[cardOrder] = allCards[2][positionsOfPairs[1]]; //(bottom pair rank)-Diamonds
                cardOrder++;
            }

            if (playerCards[3][positionsOfPairs[1]] == 1) {
                returnedTwoPair[cardOrder] = allCards[3][positionsOfPairs[1]]; //(bottom pair rank)-Hearts
                cardOrder++;
            }

        }



        String lastCardFound = "false";
        for (int m = numRanks - 1; m >= 0; m--) { //checks ranks from highest ranked card to lowest ranked for final 2 cards of hand

            if (m != positionsOfPairs[2] && m != positionsOfPairs[1] && countOfEachRank[m] <= 2 && cardOrder < 5) {
                //Highest card rank not part of two pair is found. Terminates when returnedTwoPair is full
                for (int n = 0; n < numSuits; n++) {

                    if (playerCards[n][m] == 1 && lastCardFound.equals("false")) {
                        returnedTwoPair[cardOrder] = allCards[n][m];
                        cardOrder++;
                        lastCardFound = "true";
                    }

                }
            }
        }

        if (twoPair && !royalFlush && !straightFlush && !flush && !straight &&!quads &&!fullHouse &&!trips) {
            return returnedTwoPair;
        }

        else returnedTwoPair = emptyHand;

        return returnedTwoPair;
    }

    public static void main(String[] args) {
        String[] twoPair1 = {"2-spades", "4-diamonds", "4-spades", "7-clubs", "7-spades", "J-diamonds", "J-spades"};
        String[] twoPair2 = {"2-spades", "2-diamonds", "3-spades", "3-clubs", "4-spades", "4-diamonds", "5-spades"};
        String[] trips1 = {"A-hearts", "A-spades", "A-diamonds", "4-hearts", "5-clubs", "K-clubs", "7-hearts"};
        String[] straight1 = {"6-spades", "5-spades", "4-clubs", "3-diamonds", "2-hearts", "A-clubs", "2-diamonds"};
        String[] straightFlush = {"6-spades", "5-spades", "4-spades", "3-spades", "2-spades", "A-clubs", "2-diamonds"};
        String[] royalFlush = {"A-spades", "K-spades", "Q-spades", "J-spades", "10-spades", "A-clubs", "2-diamonds"};
        String[] flush = {"6-spades", "5-spades", "4-hearts", "3-spades", "2-spades", "A-spades", "2-diamonds"};
        String[] fullHouse = {"A-spades", "A-clubs", "A-hearts", "J-diamonds", "K-clubs", "Q-hearts", "J-hearts"};
        System.out.println(Arrays.toString(isTwoPair(twoPair1)) + "   " + HasHand.checkForHand(isTwoPair(twoPair1)));
        System.out.println(Arrays.toString(isTwoPair(twoPair2)) + "   " + HasHand.checkForHand(isTwoPair(twoPair2)));
        System.out.println(Arrays.toString(isTwoPair(trips1)) + "   " + HasHand.checkForHand(isTwoPair(trips1)));
        System.out.println(Arrays.toString(isTwoPair(straight1)) + "   " + HasHand.checkForHand(isTwoPair(straight1)));
        System.out.println(Arrays.toString(isTwoPair(straightFlush)) + "   " + HasHand.checkForHand(isTwoPair(straightFlush)));
        System.out.println(Arrays.toString(isTwoPair(royalFlush)) + "   " + HasHand.checkForHand(isTwoPair(royalFlush)));
        System.out.println(Arrays.toString(isTwoPair(flush)) + "   " + HasHand.checkForHand(isTwoPair(flush)));
        System.out.println(Arrays.toString(isTwoPair(fullHouse)) + "   " + HasHand.checkForHand(isTwoPair(fullHouse)));

    }


}
