//created by Chad Carter and Joey Gale
//2/4/19

import java.util.Arrays;

public class Quads extends CardClassifier implements Cards {

    public static String[] is4ofAKind(String[] playerHand) { //good to go!

        int handSize = 5;
        int numRanks = 13;
        boolean quads = false;
        int numSuits = 4;
        int[][] playerCards = representHand(playerHand);
        int[] countOfEachRank = computeRankCount(playerCards);
        String[] returnedQuads = new String[handSize];
        int cardOrder = 0;

        //System.out.print("Quads? ");
        for (int i = 0; i < numRanks; i++) {
            //player must have 4 of a kind (quads) because they have all 4 cards of that rank
            if (countOfEachRank[i] == 4) {
                quads = true;
                for (int j = 0; j < numSuits; j++) {
                    returnedQuads[cardOrder] = allCards[j][i];
                    cardOrder++;
                    //System.out.println(cardOrder);
                }
            }
        }

        for (int m = numRanks - 1; m > 0; m--) { //gets the players highest remaining card

                if (countOfEachRank[m] >= 1 && countOfEachRank[m] < 4) { //highest card rank not part of quads is found
                    for (int n = 0; n < numSuits; n++) {
                        if (playerCards[n][m] == 1 && cardOrder < 5) {
                            returnedQuads[cardOrder] = allCards[n][m];
                            cardOrder++;
                        }
                    }
                    m = 0;
                }
        }

        if (!quads) {
            returnedQuads = emptyHand;
        }
        return returnedQuads;

    }

    public static void main(String[] args) {

        String[] hand1 = {"3-spades", "3-clubs", "3-hearts", "3-diamonds", "4-spades", "5-spades", "6-spades"};
        String[] hand2 = {"A-spades", "A-clubs", "A-hearts", "A-diamonds", "K-spades", "K-clubs", "Q-hearts"};
        String[] hand3 = {"A-spades", "K-diamonds", "A-clubs", "9-hearts", "A-hearts", "K-clubs", "2-diamonds"};

        System.out.println(Arrays.toString(is4ofAKind(hand1)));
        System.out.println(Arrays.toString(is4ofAKind(hand2)));
        System.out.println(Arrays.toString(is4ofAKind(hand3)));


    }


}
