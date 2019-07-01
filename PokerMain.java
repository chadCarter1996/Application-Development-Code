//Chad Carter and Joey Gale
//1/18/19

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Image;

public class PokerMain extends Poker implements Cards {

    private UserInterface gameScreen = new UserInterface();

    public PokerMain() {

        PokerDisplayPanel playerActions = new PokerDisplayPanel();
        UserInterface table = new UserInterface();

    }

    //CHANGE TO playPreFlop(int numPlayers), playFlop(int numPlayers), playTurn(int numPlayers),
    // playRiver(int numPlayers)
    private static void choosePoker(int numPlayers) {

        final HashMap<String, String> imageLinks = GetCardPath.createMap(allCards, cardImageLocations);
        final String[] newDeck;
        String[] shuffled;

        final int spade = 0;
        final int club = 1;
        final int diamond = 2;
        final int heart = 3;

        int numDealtCardsPostFlop = (2*numPlayers) + 3;
        int positionOfCardPostFlop = 0; //position value for inserting remaining post flop cards into
        // array of remaining cards
        int numDealtCardsPostTurn = (2*numPlayers) + 4;
        int positionOfCardPostTurn = 0; //position value for inserting remaining post turn cards into
        // array of remaining cards


        String[] undealtCardsPostFlop = new String[numCardsInDeck - numDealtCardsPostFlop];
        String[] undealtCardsPostTurn = new String[numCardsInDeck - numDealtCardsPostTurn];


        newDeck = PokerMain.createDeck(4, 13);
        shuffled = PokerMain.shuffleDeck(newDeck);
        System.out.println(Arrays.toString(shuffled));
        String[][] playerHands = Poker.preFlop(shuffled, numPlayers);


        //System.out.println(Arrays.deepToString(playerHands));
        //String[][] playerHandsAfterPreFlop = Poker.playPreFlop(playerHands, numPlayers);
        String[] board = Poker.river(shuffled, numPlayers);
        String[][] boardAndHoleCards = CardClassifier.playerCards(numPlayers, playerHands, board);
        //PokerMain.playHand(preFlop(shuffled, numPlayers), PokerMain.river(shuffled, numPlayers), numPlayers);

        for (int card = (numCardsInDeck - 1); card >= numDealtCardsPostFlop; card--) {
            //System.out.println(card);
            undealtCardsPostFlop[positionOfCardPostFlop] = shuffled[card];
            positionOfCardPostFlop++;
        }
        //System.out.println(Arrays.toString(undealtCardsPostFlop));

        for (int card = (numCardsInDeck - 1); card >= numDealtCardsPostTurn; card--) {
            //System.out.println(card);
            undealtCardsPostTurn[positionOfCardPostTurn] = shuffled[card];
            positionOfCardPostTurn++;
        }
        //System.out.println(Arrays.toString(undealtCardsPostTurn));

        for (int i = 0; i < boardAndHoleCards.length; i++) {

            String[] playerCardLinks = new String[2];
            String[] preFlopBoardCardLocations = {};
            String[] cardsAfterPreFlop = {};

            playerCardLinks[0] = imageLinks.get(boardAndHoleCards[i][0]);
            playerCardLinks[1] = imageLinks.get(boardAndHoleCards[i][1]);
            //new UserInterface.PlayGame(preflopHand);

            ImageIcon tableImage = new ImageIcon("C:\\Users\\Chad Carter\\Pictures\\PokerTable.png");

            JFrame frame = new JFrame("Main Menu");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(700, 500);
            JLabel background = new JLabel(tableImage);
            JPanel mainPanel = new JPanel();
            mainPanel.add(background);
            frame.add(mainPanel);
            JButton playButton = new JButton("Go to Hold 'Em Game Screen?");
            //JButton viewCardsButton = new JButton("View Cards");
            //JButton seeWinnersButton = new JButton("See the winning hand");
            //mainPanel.add(viewCardsButton);
            mainPanel.add(playButton);
            //mainPanel.add(seeWinnersButton);
            DrawCards cardsInHand = new DrawCards(playerCardLinks, "preFlop");
            //viewCardsButton.addActionListener(new Card.DisplayCards(playerCardLinks, /*cardsInHand,*/ "preflop"));
            //For Button Usability: https://netbeans.org/kb/articles/gui-functionality.html#Exercise_3
            String flopStage = "Flop";
            String turnStage = "Turn";
            String riverStage = "River";


            Scanner scan = new Scanner(System.in);
            String flopResponse = "yes";
            System.out.println("Call to the flop? (yes/no)");
            //flopResponse = scan.next();

            //if (playButton.

            if (flopResponse.equalsIgnoreCase("yes")) {

                mainPanel.add(playButton);
                String[] cardsAfterFlop = new String[5];
                for (int j = 0; j < 5; j++) {
                    cardsAfterFlop[j] = boardAndHoleCards[i][j];
                }

                String[] cardFlopImageLinks = new String[3];
                for (int k = 0; k < cardFlopImageLinks.length; k++) {
                    cardFlopImageLinks[k] = imageLinks.get(boardAndHoleCards[i][2 + k]);
                }
                playButton.addActionListener(new UserInterface.PlayGame(playerCardLinks, cardsInHand, cardsAfterFlop,
                        flopStage, cardFlopImageLinks));
                //playButton.addActionListener(new Flop.DisplayBoardCards(cardFlopImageLinks, flopStage));
            }
            else System.out.println("Player " + (i+1) + " has folded.");


            String turnResponse = "yes";
            System.out.println("Call to the turn? (yes/no)");
            //turnResponse = scan.next();

            if (turnResponse.equalsIgnoreCase("yes")) {
                mainPanel.add(playButton);
                String[] cardsAfterTurn = new String[6];
                for (int j = 0; j < 6; j++) {
                    cardsAfterTurn[j] = boardAndHoleCards[i][j];
                }
                String[] cardTurnImageLinks = new String[4];
                for (int k = 0; k < cardTurnImageLinks.length; k++) {
                    cardTurnImageLinks[k] = imageLinks.get(boardAndHoleCards[i][2 + k]);
                }
                playButton.addActionListener(new UserInterface.PlayGame(playerCardLinks, cardsInHand, cardsAfterTurn,
                        turnStage, cardTurnImageLinks));
            }
            else System.out.println("Player " + (i+1) + " has folded.");

            String riverResponse = "yes";
            System.out.println("Call to the river? (yes/no)");
            //riverResponse = scan.next();

            if (riverResponse.equalsIgnoreCase("yes")) {
                mainPanel.add(playButton);
                String[] cardsAfterRiver = new String[7];
                for (int j = 0; j < 7; j++) {
                    cardsAfterRiver[j] = boardAndHoleCards[i][j];
                }
                String[] cardRiverImageLinks = new String[5];
                for (int k = 0; k < cardRiverImageLinks.length; k++) {
                    cardRiverImageLinks[k] = imageLinks.get(boardAndHoleCards[i][2 + k]);
                }
                playButton.addActionListener(new UserInterface.PlayGame(playerCardLinks, cardsInHand, cardsAfterRiver,
                        riverStage, cardRiverImageLinks));
            }
            else System.out.println("Player " + (i+1) + " has folded.");

            /*System.out.println();
            System.out.println("Player " + i + "'s cards: " +
                    boardAndHoleCards[i][0] + ", " + boardAndHoleCards[i][1] + ", " + boardAndHoleCards[i][2] + ", " +
                    boardAndHoleCards[i][3] + ", " + boardAndHoleCards[i][4] + ", "  + boardAndHoleCards[i][5] + ", " +
                    boardAndHoleCards[i][6]
            );*/
            //can delete + Arrays.toString(boardAndHoleCards[i]) after presentation
            System.out.println("S: " + Arrays.toString(CardClassifier.representHand(boardAndHoleCards[i])[spade]));
            System.out.println("C: " + Arrays.toString(CardClassifier.representHand(boardAndHoleCards[i])[club]));
            System.out.println("D: " + Arrays.toString(CardClassifier.representHand(boardAndHoleCards[i])[diamond]));
            System.out.println("H: " + Arrays.toString(CardClassifier.representHand(boardAndHoleCards[i])[heart]));
            System.out.println();

            System.out.println();
            String[] playerHandPostFlop = new String[5];
            for (int j = 0; j < 5; j++) {
                playerHandPostFlop[j] = boardAndHoleCards[i][j];
            }

            System.out.println("Player " + (i+1) + " hand probabilities post flop:" +
                    PokerProbability.displayProbabilities(
                            PokerProbability.allCombos5cards(remainingCardsInDeck(CardClassifier.representHand(playerHandPostFlop), 5),
                                    playerHandPostFlop)));

            String[] playerHandPostTurn = new String[6];

            for (int k = 0; k < 6; k++) {
                playerHandPostTurn[k] = boardAndHoleCards[i][k];
            }

            System.out.println("Player " + (i+1) + " hand probabilities post turn" +
                    PokerProbability.displayProbabilities(
                            PokerProbability.allCombos6cards(remainingCardsInDeck(CardClassifier.representHand(playerHandPostTurn), 6),
                                    playerHandPostTurn)));

            System.out.println();

            boolean bestHandFound = false;
            boolean hasRoyalFlush = HasHand.checkForHand(RoyalFlush.isRoyalFlush(boardAndHoleCards[i]));
            boolean hasStraightFlush = HasHand.checkForHand(StraightFlush.isStraightFlush(boardAndHoleCards[i]));
            boolean hasQuads = HasHand.checkForHand(Quads.is4ofAKind(boardAndHoleCards[i]));
            boolean hasFullHouse = HasHand.checkForHand(FullHouse.isFullHouse(boardAndHoleCards[i]));
            boolean hasFlush = HasHand.checkForHand(Flush.isFlush(boardAndHoleCards[i]));
            boolean hasStraight = HasHand.checkForHand(Straight.isStraight(boardAndHoleCards[i]));
            boolean hasTrips = HasHand.checkForHand(Trips.is3ofAKind(boardAndHoleCards[i]));
            boolean hasTwoPair = HasHand.checkForHand(TwoPair.isTwoPair(boardAndHoleCards[i]));
            boolean hasPair = HasHand.checkForHand(Pair.isPair(boardAndHoleCards[i]));
            boolean hasNothing = HasHand.checkForHand(HighCard.isHighCard(boardAndHoleCards[i]));

            //if (!bestHandFound){

                if (hasRoyalFlush){
                    System.out.print("Royal flush: ");
                    System.out.println(Arrays.toString(RoyalFlush.isRoyalFlush(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasStraightFlush && !bestHandFound) {
                    System.out.print("Straight flush: ");
                    System.out.println(Arrays.toString(StraightFlush.isStraightFlush(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasQuads && !bestHandFound) {
                    System.out.print("Quads: ");
                    System.out.println(Arrays.toString(Quads.is4ofAKind(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasFullHouse && !bestHandFound) {
                    System.out.print("Full House: ");
                    System.out.println(Arrays.toString(FullHouse.isFullHouse(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasFlush && !bestHandFound) {
                    System.out.print("Flush: ");
                    System.out.println(Arrays.toString(Flush.isFlush(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasStraight && !bestHandFound) {
                    System.out.print("Straight: ");
                    System.out.println(Arrays.toString(Straight.isStraight(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasTrips && !bestHandFound) {
                    System.out.print("Trips: ");
                    System.out.println(Arrays.toString(Trips.is3ofAKind(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasTwoPair && !bestHandFound) {
                    System.out.print("Two pair: ");
                    System.out.println(Arrays.toString(TwoPair.isTwoPair(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasPair && !bestHandFound) {
                    System.out.print("Pair: ");
                    System.out.println(Arrays.toString(Pair.isPair(boardAndHoleCards[i])));
                    bestHandFound = true;
                }

                if (hasNothing && !bestHandFound) {
                    System.out.println("High card: ");
                    System.out.println(Arrays.toString(HighCard.isHighCard(boardAndHoleCards[i])));
                    bestHandFound = true;
                }
            bestHandFound = false;
        }

        String[][] winners = new String[numPlayers][5];
        winners = Winner.handWinner(boardAndHoleCards);
        //Winner.handWinner(boardAndHoleCards);

        //System.out.println(Arrays.toString(winners));
        // Winner.handWinner(boardAndHoleCards)));
        //add code that displays each winner(s) hand(s)!

        JFrame winnersWindow = new JFrame("View Winners");
        winnersWindow.setVisible(true);
        winnersWindow.setSize(700, 500);
        winnersWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel cardViewingOptions = new JPanel();
        winnersWindow.add(cardViewingOptions);
        JButton viewWinner = new JButton("Go to hand winners screen?");
        cardViewingOptions.add(viewWinner);

        for (int i = 0; i < winners.length; i++) {
            if (!Arrays.equals(winners[i], emptyHand)) {
                System.out.println(Arrays.toString(winners[i]));
                viewWinner.addActionListener(new Winner.DisplayWinnerCards(winners[i], "winner"));
            }
        }



        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String answer;
        System.out.println("Ready to play? (yes or no)");
        answer = scan.next();
        System.out.println();

        if (answer.equalsIgnoreCase("yes")) {

            do {
                System.out.println("Hello. What would you like to play today? (Poker, craps, roulette, or blackjack)");
                String input = scan.next();

                if (input.equalsIgnoreCase("Poker")) {

                    System.out.println("Welcome to Texas hold 'em.");
                    String anotherHand = "yes";
                    System.out.println("How many players? (Choose a number from 2 to 8)");
                    int numPlayers = scan.nextInt();
                    System.out.println();

                    //The calculated number of hands would be...
                    // First, let x = 52 - (numPlayers*2)
                    // let n = x - (number of board cards to deal [always 5 in this case])
                    // number of possible hands = x!/(n!(x-n!)) (combinations calculation)

                    do {

                        System.out.println();
                        System.out.println();
                        choosePoker(numPlayers);
                        System.out.println("Play another hand? (yes/no)");
                        anotherHand = scan.next();
                        if (!anotherHand.equalsIgnoreCase("yes")) {
                            break;
                        }
                        else {
                            System.out.println("How many players?");
                            numPlayers = scan.nextInt();
                        }
                        //System.out.println("Number of possible hands dealable: " + Combinations.combinations((52-(numPlayers*2)), 5));

                    } while (anotherHand.equals("yes"));

                    System.out.println("Thank you for playing Hold'em");

                }
                else System.out.println("Good bye.");

                System.out.println();
                System.out.println("Keep playing? (yes/no)");
                answer = scan.next();
                System.out.println();
            } while (answer.equalsIgnoreCase("yes"));
        }
        else System.out.println("Have a great day :)");

    }
}
