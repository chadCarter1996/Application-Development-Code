//Created by Chad Carter. Edited further by Joey Gale
//2/6/19

import java.util.Arrays;

public class HighCard extends CardClassifier implements Cards{

    public static String[] isHighCard(String[] playerHand) {

        int cardsInFinalHand = 5;
        boolean highCard = false;
        boolean straightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(playerHand));
        boolean flush = HasHand.checkForHand(Flush.isFlush(playerHand));
        boolean royalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(playerHand));
        boolean straight = HasHand.checkForHand(Straight.isStraight(playerHand));
        boolean quads = HasHand.checkForHand(Quads.is4ofAKind(playerHand));
        int[][] playerCards = representHand(playerHand);
        int numRanks = 13;
        int numSuits = 4;
        //int[][] playerCards == cards()
        int[] pairOrTripsTracker = new int[2]; //Keeps track of the number of pairs and 3-of-a-kinds (trips)
        //pairOrTripsTracker[0] = count of pairs, pairOrTripsTracker[1] = count of trips
        int[] countOfEachRank = computeRankCount(playerCards);
        String[] bestCards = new String[cardsInFinalHand];

        //System.out.print("High Card? ");
        for (int i = 0; i < numRanks; i++) {

            if (countOfEachRank[i] == 2) {
                pairOrTripsTracker[0]++; //increases pair count by 1
            }

            else if (countOfEachRank[i] == 3) {
                pairOrTripsTracker[1]++; //increases trips count by 1
            }

            else pairOrTripsTracker[0]+=0;
        }

        if (pairOrTripsTracker[0] == 0 && pairOrTripsTracker[1] == 0 && !royalFlush && !straightFlush && !flush && !straight
        && !quads) {
            highCard = true;
            bestCards = findHighCards(playerCards, 5);
        }

        else bestCards = emptyHand;

        return bestCards;
    }

    public static void main(String[] args) {
        String[] bestPair = {"2-spades", "4-diamonds", "3-spades", "7-clubs", "K-spades", "A-diamonds", "A-spades"};
        String[] weakPair = {"2-spades", "2-diamonds", "4-spades", "7-clubs", "K-spades", "A-diamonds", "9-spades"};
        String[] twoPair1 = {"2-spades", "4-diamonds", "4-spades", "7-clubs", "7-spades", "J-diamonds", "J-spades"};
        String[] twoPair2 = {"2-spades", "2-diamonds", "3-spades", "3-clubs", "4-spades", "4-diamonds", "5-spades"};
        String[] AceHigh = {"2-spades", "A-diamonds", "3-spades", "9-clubs", "4-spades", "Q-diamonds", "K-spades"};
        String[] trips1 = {"A-hearts", "A-spades", "A-diamonds", "4-hearts", "5-clubs", "K-clubs", "7-hearts"};
        String[] straight1 = {"6-spades", "5-spades", "4-clubs", "3-diamonds", "2-hearts", "A-clubs", "2-diamonds"};
        String[] straightFlush = {"6-spades", "5-spades", "4-spades", "3-spades", "2-spades", "A-clubs", "2-diamonds"};
        String[] royalFlush = {"A-spades", "K-spades", "Q-spades", "J-spades", "10-spades", "A-clubs", "2-diamonds"};
        String[] flush = {"6-spades", "5-spades", "4-hearts", "3-spades", "2-spades", "A-spades", "2-diamonds"};
        String[] fullHouse = {"A-spades", "A-clubs", "A-hearts", "J-diamonds", "K-clubs", "Q-hearts", "J-hearts"};
        String[] quads = {"A-spades", "A-clubs", "A-hearts", "A-diamonds", "K-spades", "K-clubs", "Q-hearts"};
        System.out.println(Arrays.toString(isHighCard(bestPair)) + "   " + HasHand.checkForHand(isHighCard(bestPair)));
        System.out.println(Arrays.toString(isHighCard(twoPair1)) + "   " + HasHand.checkForHand(isHighCard(twoPair1)));
        System.out.println(Arrays.toString(isHighCard(twoPair2)) + "   " + HasHand.checkForHand(isHighCard(twoPair2)));
        System.out.println(Arrays.toString(isHighCard(weakPair)) + "   " + HasHand.checkForHand(isHighCard(weakPair)));
        System.out.println(Arrays.toString(isHighCard(AceHigh)) + "   " + HasHand.checkForHand(isHighCard(AceHigh)));
        System.out.println(Arrays.toString(isHighCard(trips1)) + "   " + HasHand.checkForHand(isHighCard(trips1)));
        System.out.println(Arrays.toString(isHighCard(straight1)) + "   " + HasHand.checkForHand(isHighCard(straight1)));
        System.out.println(Arrays.toString(isHighCard(straightFlush)) + "   " + HasHand.checkForHand(isHighCard(straightFlush)));
        System.out.println(Arrays.toString(isHighCard(royalFlush)) + "   " + HasHand.checkForHand(isHighCard(royalFlush)));
        System.out.println(Arrays.toString(isHighCard(flush)) + "   " + HasHand.checkForHand(isHighCard(flush)));
        System.out.println(Arrays.toString(isHighCard(fullHouse)) + "   " + HasHand.checkForHand(isHighCard(fullHouse)));
        System.out.println(Arrays.toString(isHighCard(quads)) + "   " + HasHand.checkForHand(isHighCard(quads)));
    }


}
