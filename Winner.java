//created by Chad Carter. Further edited by Joey Gale.
//2/10/19

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Arrays;

public class Winner extends CardClassifier implements Cards {


    //everything works fine now :)
    public static String[][] handWinner(String[][] allPlayerHands) {

        HashMap<String, String> imageLinks = GetCardPath.createMap(allCards, cardImageLocations);
        int numFinalCards = 5;
        int totalBoardAndHoleCards = 7;
        int numPlayers = allPlayerHands.length;
        int handRank = 0;
        String[][] bestHands = new String[numPlayers][totalBoardAndHoleCards];
        String[][] handWinnerCardLocations = new String[numPlayers][5];
        int[][] playerHandInfo = new int[numPlayers][2];
        int bestHandRank = -1;
        String[] newBestHand = new String[numFinalCards];
        int[] bestCardHandScores = new int[numPlayers];

        for (int i = 0; i < numPlayers; i++) { //begins to rank each player's hand with respect to other player's hands

            boolean playerHand = false;
            boolean hasRoyalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(allPlayerHands[i]));
            boolean hasStraightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(allPlayerHands[i]));
            boolean hasQuads = HasHand.checkForHand(Quads.is4ofAKind(allPlayerHands[i]));
            boolean hasFullHouse = HasHand.checkForHand(FullHouse.isFullHouse(allPlayerHands[i]));
            boolean hasFlush = HasHand.checkForHand(Flush.isFlush(allPlayerHands[i]));
            boolean hasStraight = HasHand.checkForHand(Straight.isStraight(allPlayerHands[i]));
            boolean hasTrips = HasHand.checkForHand(Trips.is3ofAKind(allPlayerHands[i]));
            boolean hasTwoPair = HasHand.checkForHand(TwoPair.isTwoPair(allPlayerHands[i]));
            boolean hasPair = HasHand.checkForHand(Pair.isPair(allPlayerHands[i]));
            boolean hasNothing = HasHand.checkForHand(HighCard.isHighCard(allPlayerHands[i]));

            //checks for a player having a royal flush
            if (hasRoyalFlush){
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 10;
                playerHand = true;
            }

            //checking for straight flush
            if (hasStraightFlush && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 9;
                playerHand = true;
            }

            //etc.
            if (hasQuads && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 8;
                playerHand = true;
            }

            if (hasFullHouse && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 7;
                playerHand = true;
            }

            if (hasFlush && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 6;
                playerHand = true;
            }

            if (hasStraight && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 5;
                playerHand = true;
            }

            if (hasTrips && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 4;
                playerHand = true;
            }

            if (hasTwoPair && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 3;
                playerHand = true;
            }

            if (hasPair && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 2;
                playerHand = true;
            }

            if (hasNothing && !playerHand) {
                handRank++;
                playerHandInfo[i][0] = i;
                playerHandInfo[i][1] = 1;
                playerHand = true;
            }
            playerHand = false;

        }

        for (int player = 0; player < numPlayers; player++) {

            if (playerHandInfo[player][1] > bestHandRank) {
                bestHandRank = playerHandInfo[player][1];
            }

        }

        int[] bestPlayers = new int[numPlayers];

        int[][] cardRanks = new int[numPlayers][numFinalCards];

        for (int player = 0; player < numPlayers; player++) {

            if (playerHandInfo[player][1] == bestHandRank) {
                if (bestHandRank == 10) {
                    bestHands[player] = RoyalFlush.isRoyalFlush(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, -1, -1,-1,-1);
                    //playerHandPosition++;
                }
                if (bestHandRank == 9) {
                    bestHands[player] = StraightFlush.isStraightFlush(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, -1, -1,-1,-1);
                    //playerHandPosition++;

                }
                if (bestHandRank == 8) {
                    bestHands[player] = Quads.is4ofAKind(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, 1, 2,3,4);
                    //playerHandPosition++;
                }
                if (bestHandRank == 7) {
                    bestHands[player] = FullHouse.isFullHouse(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, 1, 2,3,4);
                    //playerHandPosition++;
                }
                if (bestHandRank == 6) {
                    bestHands[player] = Flush.isFlush(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, 1, 2,3,4);
                    //playerHandPosition++;
                }
                if (bestHandRank == 5) {
                    bestHands[player] = Straight.isStraight(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, -1, -1,-1,-1);
                    //playerHandPosition++;
                }
                if (bestHandRank == 4) {
                    bestHands[player] = Trips.is3ofAKind(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, 1, 2,3,4);
                    //playerHandPosition++;
                }
                if (bestHandRank == 3) {
                    bestHands[player] = TwoPair.isTwoPair(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, 1, 2,3,4);
                    //playerHandPosition++;
                }
                if (bestHandRank == 2) {
                    bestHands[player] = Pair.isPair(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, 1, 2,3,4);
                    //playerHandPosition++;
                }
                if (bestHandRank == 1) {
                    bestHands[player] = HighCard.isHighCard(allPlayerHands[player]);
                    cardRanks[player] = CardClassifier.scoreHand(bestHands[player], 0, 1, 2,3,4);
                    //playerHandPosition++;
                }
            }
        }

        newBestHand = bestHands[0]; 
        bestCardHandScores = cardRanks[0]; //all issues fixed!
        for (int i = 1; i < numPlayers; i++) {
            for (int j = 0; j < 5; j++) {
                if (bestCardHandScores[j] > cardRanks[i][j]) {
                    bestCardHandScores = bestCardHandScores;
                    break;
                }
                else bestCardHandScores = cardRanks[i];


            }
        }

        int bestPlayerOrder = 0;

        String[][] bestPlayerCards = new String[numPlayers][5];

        for (int player = 0; player < cardRanks.length; player++) {

            if (Arrays.equals(cardRanks[player], bestCardHandScores)){
                bestPlayers[bestPlayerOrder] = player + 1;
                bestPlayerOrder++;
                System.out.println("Player " + player + " has won with " + " |" + bestHands[player][0] + "| |"
                        + bestHands[player][1] + "| |" + bestHands[player][2] + "| |" + bestHands[player][3]
                        + "| |" + bestHands[player][4] + "|"); //Ask Brodie how you fix this mess.

                bestPlayerCards[bestPlayerOrder - 1][0] = imageLinks.get(bestHands[player][0]);
                
                bestPlayerCards[bestPlayerOrder - 1][1] = imageLinks.get(bestHands[player][1]);
                
                bestPlayerCards[bestPlayerOrder - 1][2] = imageLinks.get(bestHands[player][2]);
                
                bestPlayerCards[bestPlayerOrder - 1][3] = imageLinks.get(bestHands[player][3]);
                
                bestPlayerCards[bestPlayerOrder - 1][4] = imageLinks.get(bestHands[player][4]);
                

                new DisplayWinnerCards(bestPlayerCards[player], "winner");
                //displays the winning hand for that player
            }
            else {
                bestPlayers[bestPlayerOrder] = player + 1;
                bestPlayerOrder++;

                bestPlayerCards[bestPlayerOrder - 1][0] = "";
                //System.out.println(imageLinks.get(bestHands[player][0])); //1st card image link stored
                bestPlayerCards[bestPlayerOrder - 1][1] = "";
                //System.out.println(imageLinks.get(bestHands[player][1])); //2nd card
                bestPlayerCards[bestPlayerOrder - 1][2] = "";
                //System.out.println(imageLinks.get(bestHands[player][2])); //3rd card
                bestPlayerCards[bestPlayerOrder - 1][3] = "";
                //System.out.println(imageLinks.get(bestHands[player][3])); //4th card
                bestPlayerCards[bestPlayerOrder - 1][4] = "";
                //System.out.println(imageLinks.get(bestHands[player][4])); //5th card
            }
        }
        
        return bestPlayerCards; //REMEMBER that the RETURNED VALUES are 1 more than the actual player positions!
    }

    static class DisplayWinnerCards implements ActionListener {

        
        private String[] winnerCardLocations;
        private String stage;
       

        public DisplayWinnerCards(String[] winnerCardLocations, String stage) {

            this.winnerCardLocations = winnerCardLocations;
            this.stage = stage;
            //this.cardsInHand = cardsInHand;

        }

        public void actionPerformed(ActionEvent e) {

            JFrame pokerTable = new JFrame("Player Cards");
            pokerTable.setVisible(true);
            pokerTable.setSize(700, 500);
            JPanel playerChoices = new JPanel();
            pokerTable.add(playerChoices);
            JButton showWinners = new JButton("View Winning Hand");
            playerChoices.add(showWinners);
            //pokerTable.add(new DrawCards(winnerCardLocations, stage));
            showWinners.addActionListener(new Card.DisplayCards(winnerCardLocations, stage));

        }
    }

    //add ActionListener to display winning hands!

    public static void main(String[] args) {
        String[][] firstHand = {{"Q-spades", "J-clubs", "Q-hearts", "2-clubs", "9-diamonds", "J-hearts", "6-clubs"}, //winner
                {"A-spades", "6-spades", "Q-hearts", "2-clubs", "9-diamonds", "J-hearts", "6-clubs"},
                {"7-diamonds", "6-hearts", "Q-hearts", "2-clubs", "9-diamonds", "J-hearts", "6-clubs"},
                {"A-clubs", "7-clubs", "Q-hearts", "2-clubs", "9-diamonds", "J-hearts", "6-clubs"},
                {"2-diamonds", "7-hearts", "Q-hearts", "2-clubs", "9-diamonds", "J-hearts", "6-clubs"}};
        System.out.println(Arrays.toString(handWinner(firstHand)));

        String[][] secondHand = {{"4-clubs", "8-spades", "K-clubs", "5-clubs", "9-clubs", "8-diamonds", "K-hearts"}, //winner
                {"6-spades", "J-hearts", "K-clubs", "5-clubs", "9-clubs", "8-diamonds", "K-hearts"},
                {"J-spades", "2-hearts", "K-clubs", "5-clubs", "9-clubs", "8-diamonds", "K-hearts"},
                {"A-hearts", "3-spades", "K-clubs", "5-clubs", "9-clubs", "8-diamonds", "K-hearts"},
                {"5-diamonds", "2-spades", "K-clubs", "5-clubs", "9-clubs", "8-diamonds", "K-hearts"},
                {"8-hearts", "3-clubs", "K-clubs", "5-clubs", "9-clubs", "8-diamonds", "K-hearts"}}; //winner
        System.out.println(Arrays.toString(handWinner(secondHand)));

        String[][] thirdHand = {{"10-hearts", "2-spades", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"}, //winner
                {"K-clubs", "4-spades", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"},
                {"8-clubs", "J-spades", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"},
                {"3-diamonds", "2-hearts", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"},
                {"9-hearts", "6-clubs", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"},
                {"Q-diamonds", "6-hearts", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"}, //2nd best
                {"3-clubs", "4-hearts", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"},
                {"A-hearts", "3-hearts", "2-diamonds", "K-diamonds", "6-diamonds", "10-diamonds", "10-spades"}};
        System.out.println(Arrays.toString(handWinner(thirdHand)));

        String[][] fourthHand = {{"3-spades", "A-hearts", "10-hearts", "J-clubs", "Q-clubs", "8-hearts", "2-clubs"},
                {"2-hearts", "6-spades", "10-hearts", "J-clubs", "Q-clubs", "8-hearts", "2-clubs"}}; //winner
        System.out.println(Arrays.toString(handWinner(fourthHand)));

        String[][] fifthHand =
                            {
                                {"A-spades", "K-clubs", "10-hearts", "J-clubs", "A-clubs", "K-hearts", "2-clubs"},
                                {"A-hearts", "2-spades", "10-hearts", "J-clubs", "A-clubs", "K-hearts", "2-clubs"},
                                {"10-clubs", "J-diamonds", "10-hearts", "J-clubs", "A-clubs", "K-hearts", "2-clubs"},
                                {"A-diamonds", "K-spades", "10-hearts", "J-clubs", "A-clubs", "K-hearts", "2-clubs"},
                                {"K-diamonds", "J-hearts", "10-hearts", "J-clubs", "A-clubs", "K-hearts", "2-clubs"}
                            };
        System.out.println(Arrays.toString(handWinner(fifthHand)));

        String[][] tiedHand = {{"9-spades", "A-hearts", "10-hearts", "J-clubs", "Q-clubs", "8-hearts", "2-clubs"},
                {"9-hearts", "6-spades", "10-hearts", "J-clubs", "Q-clubs", "8-hearts", "2-clubs"}}; //winner
        System.out.println(Arrays.toString(handWinner(tiedHand)));

        String[][] tiedHand2 = {{"10-spades", "2-hearts", "10-hearts", "J-clubs", "Q-clubs", "8-hearts", "2-clubs"},
                {"10-diamonds", "2-spades", "10-hearts", "J-clubs", "Q-clubs", "8-hearts", "2-clubs"}}; //winner
        System.out.println(Arrays.toString(handWinner(tiedHand2)));

        String[][] tiedHand3 = {{"6-hearts", "J-spades", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"},
                {"5-clubs", "Q-hearts", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"},
                {"5-diamonds", "8-clubs", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"},
                {"8-hearts", "K-clubs", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"},
                {"J-clubs", "4-diamonds", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"},
                {"4-clubs", "Q-spades", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"},
                {"9-spades", "Q-clubs", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"},
                {"J-diamonds", "4-spades", "3-diamonds", "A-hearts", "J-hearts", "4-hearts", "3-spades"}};
        System.out.println(Arrays.toString(handWinner(tiedHand3)));

        JFrame winnerScreen = new JFrame("Winners");
        winnerScreen.setVisible(true);
        winnerScreen.setSize(700, 500);
        JPanel playerChoices = new JPanel();
        winnerScreen.add(playerChoices);
        JButton showWinners = new JButton("View Winning Hand");

        //pokerTable.add(new DrawCards(winnerCardLocations, stage));
        //showWinners.addActionListener(new Card.DisplayCards(winnerCardLocations, stage));
        //System.out.println(Arrays.deepToString(CardClassifier.cards(tiedHand3[0])) + "  \n" +  Arrays.toString(tiedHand3[0]));

/*        Player 0: [6-hearts, J-spades]
        Player 1: [5-clubs, Q-hearts]
        Player 2: [5-diamonds, 8-clubs]
        Player 3: [8-hearts, K-clubs]
        Player 4: [J-clubs, 4-diamonds]
        Player 5: [4-clubs, Q-spades]
        Player 6: [9-spades, Q-clubs]
        Player 7: [J-diamonds, 4-spades]

        Here's the board post river...
|3-diamonds| |A-hearts| |J-hearts| |4-hearts| |3-spades|*/

        System.out.println();
        System.out.println();

    }

}
