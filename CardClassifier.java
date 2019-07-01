//by Chad Carter and Joey Gale
//1/25/19


import java.io.FileNotFoundException;
import java.util.Arrays;

public class CardClassifier extends Poker implements Cards {

    public static String[][] playerCards(int numPlayers, String[][] playerHands, String[] boardCards){
        //this method combines the players preflop cards with the board cards (the community cards everyone can use to
        //help make the best hand). The returned 2D string array will represent every players combined pre-flop and board
        //cards. Hence the name of the returned array is boardAndHoleCards.

        String[][] boardAndHoleCards = new String[numPlayers][7];
        for (int i=0; i<numPlayers; i++) {
            for (int j = 0; j < 2; j++) {
                boardAndHoleCards[i][j] = playerHands[i][j];
            }
        }

        for (int i=0; i<numPlayers; i++) {

            for (int j=0; j<numFinalCards; j++) {
                boardAndHoleCards[i][j+2] =  boardCards[j];
            }

        }

        return boardAndHoleCards;

    }

    public static int[][] representHand(String[] playerHand) { //change to representHand
        //returns the players hand represented by a 4 x 13 2D array (or matrix) of 1s and 0s.
        //Array/matrix columns represent card ranks (2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A)
        //Array/matrix rows represent card suits (r0 = spades, r1 = clubs, r2 = diamonds, r3 = hearts)

        int numCards = playerHand.length;

        int[][] cardMatrix = new int[numSuits][numRanks];

        for (int i = 0; i < numSuits; i++) {
            for (int j = 0; j < numRanks; j++) {
                    for (int k = 0; k < numCards; k++) {
                        if (playerHand[k].equals(allCards[i][j])) { // If the player's card matches the card prepositioned in
                            //the sorted deck "allCards" at column j, row i, then the if statement will be true.
                            cardMatrix[i][j] = 1; //when the player's card matches the positional card, a 1 is placed
                            //into the player's hand matrix at column j, row i of said matrix. The player has that card!
                        }
                    }
            }
        }
        return cardMatrix;
    }

    public static String[] findHighCards(int[][] handMatrix, int numCardsReturned) { //numCardsReturned will always be 5
        //Sorts the player's cards and returns the 5 best/strongest cards.
        //rename findHighCards
        int numCards = 7; //number of cards in player hand (2) AND community cards (5) 2 + 5 = 7
        String[] bestCards = new String[numCardsReturned]; //since a player will only have 5 cards in their final hand,
        // only a String array of size 5 will be returned. This method plays a major role in the HighCard class.
        int cardOrder = 4; //This is used for positioning the strongest cards into the returned best cards array

        for (int i = 0; i < numRanks; i++) {
            for (int j = 0; j < numSuits; j++) {
                if (handMatrix[j][i] == 1){
                    numCards --;
                }
                if (numCards < numCardsReturned && handMatrix[j][i] == 1) {
                    bestCards[cardOrder] = allCards[j][i]; //places the "best" card in its appropriate position
                    cardOrder--; //decrements so that the best cards don't overlap.
                }
            }
        }

        return bestCards;
    }

    public static int[] computeSuitCount(int[][] playerHand) {
        //This method is especially useful for helping determined whether a player has a flush or not. It returns an
        //array with the count of each suit. f.e. a player has 1 spade, 1 heart and 5 diamonds. The returned suit count
        //array would be [1, 0, 5, 1]
        //computeSuitCount
        int[] suitCount = new int[numSuits];
        for (int i = 0; i < numRanks; i++) {
            for (int j = 0; j < numSuits; j++) {
                if (playerHand[j][i] == 1) {
                    suitCount[j] ++;
                }
            }
        }

        return suitCount;
    }

    public static int[] computeRankCount(int[][] playerHand) {
        //This method returns an array that represents how many of each rank of card a player has in their hand.
        //f.e. a player has A, A, K, K, 10, 9, 8, 6 in their combined hand. Result: [0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 2, 2]

        int[] rankCount = new int[numRanks];
        for (int i = 0; i < numRanks; i++) {
            for (int j = 0; j < numSuits; j++) {
                if (playerHand[j][i] == 1) {
                    rankCount[i] ++;
                }
            }
        }

        return rankCount;
    }

    public static int[] scoreHand(String[] playerHand, int card1, int card2, int card3, int card4, int card5) {
        //this method helps with breaking ties in a game. For hands like 2-pair or trips, these tiebreakers are especially
        //relevant.

        int[] cardsScored = {card1, card2, card3, card4, card5};
        int[] playerHandScored = new int[numFinalCards];

        for (int i = 0; i < numFinalCards; i++) {
            for (int rank = numRanks - 1; rank >= 0; rank--){
                if (cardsScored[i] != -1 && (playerHand[i].charAt(0) == allCards[0][rank].charAt(0))) {
                    playerHandScored[i] = rank + 1;
                    rank = 0;
                }
            }

        }
        return playerHandScored;
    }

    public static void main(String[] args) throws FileNotFoundException {

        int numPlayers = 6;
        String[] firstDeck = CardClassifier.createDeck(4,13);
        String[] shuffledDeck = CardClassifier.shuffleDeck(firstDeck);
        String[][] playerHands = CardClassifier.preFlop(shuffledDeck, numPlayers);
        String[] board = CardClassifier.river(shuffledDeck, numPlayers);

        String[][] boardAndHoleCards = playerCards(numPlayers, playerHands, board);

        for (int i = 0; i < boardAndHoleCards.length; i++) {
            System.out.println("Player " + i + "'s hand:");
            System.out.println("S: " + Arrays.toString(representHand(boardAndHoleCards[i])[0]));
            System.out.println("C: " + Arrays.toString(representHand(boardAndHoleCards[i])[1]));
            System.out.println("D: " + Arrays.toString(representHand(boardAndHoleCards[i])[2]));
            System.out.println("H: " + Arrays.toString(representHand(boardAndHoleCards[i])[3]));
            System.out.println();

        }

    }
}

