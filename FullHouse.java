//Created by Chad Carter. Edited by Joey Gale
//2/5/19

import java.util.Arrays;

public class FullHouse extends CardClassifier implements Cards {

    public static String[] isFullHouse(String[] playerHand) { //good to go!

        int handSize = 5;
        int numSuits = 4;
        int numRanks = 13;
        boolean fullHouse = false;
        boolean royalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(playerHand));
        boolean straightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(playerHand));
        int[][] playerCards = representHand(playerHand);
        int[] pairOrTripsTracker = new int[2]; //Keeps track of the number of pairs and 3-of-a-kinds (trips)
                                               //pairOrTripsTracker[0] = count of pairs, pairOrTripsTracker[1] = count of trips
        int[] countOfEachRank = computeRankCount(playerCards);
        String[] bestHouse = new String[handSize];
        int pairPosition = 0;
        int tripsPosition = 1;
        int beginTrips = 0;
        int beginPair = 3;
        boolean foundTrips = false;
        boolean higherPair = false;


        //System.out.print("Full House? ");
        for (int i = (numRanks - 1); i >= 0; i--) { //consider adding this to a new class...

            if (countOfEachRank[i] == 2 && !higherPair) {
                pairOrTripsTracker[pairPosition]++;
                for (int j= 0; j < numSuits; j++) {
                    if (playerCards[j][i] == 1) {
                        bestHouse[beginPair] = allCards[j][i];
                        beginPair++;
                        higherPair = true;
                    }
                }
                beginPair = 3;
            }

            else if (countOfEachRank[i] == 3 && !foundTrips) {
                pairOrTripsTracker[tripsPosition]++;
                for (int j = 0; j < numSuits; j++) {
                    if (playerCards[j][i] == 1) {
                        bestHouse[beginTrips] = allCards[j][i];
                        beginTrips++;
                        foundTrips = true;
                    }
                }
                beginTrips = 0;
            }

            else if (countOfEachRank[i] == 3 && foundTrips && !higherPair) {
                pairOrTripsTracker[pairPosition]++; //covers rare occasion when player's house is comprised of 2 3-of-a-kinds
                for (int j= 0; j < numSuits; j++) {
                    if (playerCards[j][i] == 1 && beginPair < 5) {
                        bestHouse[beginPair] = allCards[j][i];
                        beginPair++;
                    }
                }
                beginPair = 3;
            }

            else pairOrTripsTracker[0]+=0;
        }

        if (pairOrTripsTracker[pairPosition] >= 1 && pairOrTripsTracker[tripsPosition] >= 1) {
            fullHouse = true;
            /*System.out.println("|" + bestHouse[0] + "| |" + bestHouse[1] + "| |" + bestHouse[2] +
                    "| |" + bestHouse[3] + "| |" + bestHouse[4] + "|"); //displays the full house*/
        }

        else if(pairOrTripsTracker[tripsPosition] == 2) {
            fullHouse = true;
        }

        else bestHouse = emptyHand;

        if (fullHouse && !royalFlush && !straightFlush) {
            return bestHouse;
        }
        else bestHouse = emptyHand;

        return bestHouse;
    }

    public static void main(String[] args) {

        String[] fullHouse = {"A-spades", "A-clubs", "A-hearts", "J-diamonds", "K-clubs", "Q-hearts", "J-hearts"};
        String[] alsoHouse = {"A-spades", "A-clubs", "A-hearts", "J-diamonds", "J-clubs", "Q-hearts", "J-hearts"};
        String[] notAHouse = {"A-spades", "8-clubs", "7-hearts", "J-diamonds", "K-clubs", "Q-hearts", "J-hearts"};
        String[] quads = {"A-spades", "A-clubs", "A-hearts", "A-diamonds", "K-clubs", "K-hearts", "J-hearts"};
        String[] trickyHouse = {"9-clubs", "9-diamonds", "9-hearts", "2-spades", "2-hearts", "10-clubs", "10-diamonds"};

        System.out.println(Arrays.toString(isFullHouse(fullHouse)) + "     " + HasHand.checkForHand(isFullHouse(fullHouse)));
        System.out.println(Arrays.toString(isFullHouse(alsoHouse)) + "     " + HasHand.checkForHand(isFullHouse(alsoHouse)));
        System.out.println(Arrays.toString(isFullHouse(notAHouse)) + "     " + HasHand.checkForHand(isFullHouse(notAHouse)));
        System.out.println(Arrays.toString(isFullHouse(quads)) + "     " + HasHand.checkForHand(isFullHouse(quads)));
        System.out.println(Arrays.toString(isFullHouse(trickyHouse)) + "     " + HasHand.checkForHand(isFullHouse(trickyHouse)));
        //All test cases go here.
    }


}
