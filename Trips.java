//Created by Chad Carter. Further edited by Joey Gale
//2/6/19

import java.util.Arrays;

public class Trips extends CardClassifier implements Cards{

    public static String[] is3ofAKind(String[] playerHand) {

        int cardsInFinalHand = 5;
        boolean trips = false;
        boolean straightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(playerHand));
        boolean flush = HasHand.checkForHand(Flush.isFlush(playerHand));
        boolean royalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(playerHand));
        boolean straight = HasHand.checkForHand(Straight.isStraight(playerHand));
        int[][] playerCards = representHand(playerHand);
        int[] pairOrTripsTracker = new int[2]; //Keeps track of the number of pairs and 3-of-a-kinds (trips)
                                               //pairOrTripsTracker[0] = count of pairs, pairOrTripsTracker[1] = count of trips
        int[] countOfEachRank = computeRankCount(playerCards);
        int numRanks = 13;
        int numSuits = 4;
        int cardOrder = 0;
        String[] returnedTrips = new String[cardsInFinalHand];
        int rankOfTrips = 0;

        //System.out.print("Trips? ");
        for (int i = 0; i < countOfEachRank.length; i++) {
            if (countOfEachRank[i] == 2) {
                pairOrTripsTracker[0]++;
            }
            else if (countOfEachRank[i] == 3) {
                pairOrTripsTracker[1]++;
                rankOfTrips = i;
            }
            else pairOrTripsTracker[0]+=0;
        }

        if (pairOrTripsTracker[1] == 1 && pairOrTripsTracker[0] == 0) {
            trips = true;

            for (int j = 0; j < numSuits; j++) {
                if (playerCards[j][rankOfTrips] == 1) {
                    returnedTrips[cardOrder] = allCards[j][rankOfTrips];
                    cardOrder++;
                }
            }



            for (int m = numRanks - 1; m >= 0; m--) { //checks ranks from highest ranked card to lowest ranked for final 2 cards of hand
                if (m != rankOfTrips && countOfEachRank[m] == 1 && cardOrder < 5) { //highest card rank not part of trips is found
                                                                //terminates when returnedTrips is full
                    for (int n = 0; n < numSuits; n++) {

                        if (playerCards[n][m] == 1) {
                            returnedTrips[cardOrder] = allCards[n][m];
                            cardOrder++;
                        }

                    }
                }
            }
        }

        if (trips && !royalFlush && !straightFlush && !flush && !straight) {
            return returnedTrips;
        }
        else returnedTrips = emptyHand;

        return returnedTrips;
    }

    public static void main(String[] args) {
        String[] trips1 = {"A-hearts", "A-spades", "A-diamonds", "4-hearts", "5-clubs", "K-clubs", "7-hearts"};
        String[] straight1 = {"6-spades", "5-spades", "4-clubs", "3-diamonds", "2-hearts", "A-clubs", "2-diamonds"};
        String[] straightFlush = {"6-spades", "5-spades", "4-spades", "3-spades", "2-spades", "A-clubs", "2-diamonds"};
        String[] royalFlush = {"A-spades", "K-spades", "Q-spades", "J-spades", "10-spades", "A-clubs", "2-diamonds"};
        String[] flush = {"6-spades", "5-spades", "4-hearts", "3-spades", "2-spades", "A-spades", "2-diamonds"};
        String[] fullHouse = {"A-spades", "A-clubs", "A-hearts", "J-diamonds", "K-clubs", "Q-hearts", "J-hearts"};
        //add tests here
        System.out.println(Arrays.toString(is3ofAKind(trips1)) + "   " + HasHand.checkForHand(is3ofAKind(trips1)));
        System.out.println(Arrays.toString(is3ofAKind(straight1)) + "   " + HasHand.checkForHand(is3ofAKind(straight1)));
        System.out.println(Arrays.toString(is3ofAKind(straightFlush)) + "   " + HasHand.checkForHand(is3ofAKind(straightFlush)));
        System.out.println(Arrays.toString(is3ofAKind(royalFlush)) + "   " + HasHand.checkForHand(is3ofAKind(royalFlush)));
        System.out.println(Arrays.toString(is3ofAKind(flush)) + "   " + HasHand.checkForHand(is3ofAKind(flush)));
        System.out.println(Arrays.toString(is3ofAKind(fullHouse)) + "   " + HasHand.checkForHand(is3ofAKind(fullHouse)));
    }


}
