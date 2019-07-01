import java.util.Arrays;
import java.util.Random;
import java.util.*;
import java.*;
import java.io.File;
import java.io.IOException;


public class Poker implements Cards{

    public static String[] createDeck(int numSuits, int numCardValues) {
        String[] cardValues = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] suits = {"hearts", "diamonds", "spades", "clubs"};
        String[] finalDeck = new String[numSuits*numCardValues];
        int numCards = 0;

        for (int i=0; i < numCardValues; i++) {
            for (int j = 0; j < numSuits; j++) {
                finalDeck[numCards] = "" + cardValues[i] + "-" + suits[j] + "";
                numCards++;
            }
        }
        return finalDeck;
    }

    public static String[] shuffleDeck(String[] deck) {

        int numCards = deck.length;
        String[] shuffledDeck = new String[numCards];
        LinkedList<Integer> orderOfCards = new LinkedList<>();
        Random r = new Random();
        int num;
        while (orderOfCards.size() < numCards) {
            num = r.nextInt(numCards);
            if (!orderOfCards.contains(num)) {
                orderOfCards.add(num);
            }
        }

        for (int i = 0; i < numCards; i++) {
            shuffledDeck[i] = deck[orderOfCards.get(i)];
        }

        return shuffledDeck;
    }

    public static String[][] preFlop(String[] shuffledDeck, int numPlayers) {

        int dealtCards = 0;
        String[][] playersHands = new String[numPlayers][2];
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < numPlayers; i++) {
                playersHands[i][j] = shuffledDeck[dealtCards];
                dealtCards++;
            }
        }
        return playersHands;
    }


    private static boolean playersBetsEqual(ArrayList<Integer> bets) {

        boolean allSameBets = true;

        for (Integer bet: bets) {
            if (!bet.equals(bets.get(0)))
                allSameBets = false;
        }

        return allSameBets;

    }


    public static ArrayList<Integer> bettingCycle(ArrayList<Integer> playersRemaining, String[][] playerHands,
                                                  ArrayList<Integer> bets, int totalPot) {

        Scanner playerInput = new Scanner(System.in);
        String playerDecision="";
        int currentBet = bets.get(bets.size() - 1);
        int updatedCurrentBet = currentBet;
        System.out.println(updatedCurrentBet);

        for (int i = 0; i < playersRemaining.size(); i++) { //make this affected by betting now!

            if (playersBetsEqual(bets)) {
                System.out.println(bets);
                System.out.println(totalPot);
                break;
            }

            else {
                System.out.println("Player " + i + ": " + Arrays.toString(playerHands[i]));
                System.out.println("Do you wish to call, fold or raise? ");
                playerDecision = playerInput.next();

                if (playerDecision.equalsIgnoreCase("call")) {

                    System.out.println("Player " + i + " has called");
                    totalPot += currentBet;
                    playersRemaining.set(i, i);
                    System.out.println(playersRemaining);
                    bets.set(i, updatedCurrentBet);
                } else if (playerDecision.equalsIgnoreCase("fold")) {
                    System.out.println("Player " + i + " has folded");
                    playersRemaining.set(i, -1);
                    bets.set(i, currentBet);
                    System.out.println(playersRemaining);

                } else if (playerDecision.equalsIgnoreCase("raise")) {

                    System.out.println("How much do you raise? ");

                    updatedCurrentBet = playerInput.nextInt() + updatedCurrentBet;
                    totalPot = totalPot + updatedCurrentBet;

                    System.out.println("Player " + i + " has raised to $" + updatedCurrentBet);
                    System.out.println("The total pot is now $" + totalPot);
                    playersRemaining.set(i, i);
                    bets.set(i, updatedCurrentBet);
                    System.out.println(totalPot);
                }
            }
        } //see if there's a way to pass through the total pot

        return playersRemaining;

    }

    public static ArrayList<Integer> getRidOfPlayers(ArrayList<Integer> playersInHand) {
        int numFoldedPlayers = 0;

        for (int i = 0; i < playersInHand.size(); i++) {
            if (playersInHand.get(i).equals(-1)) {
                numFoldedPlayers ++;
            }
        }
        Collections.sort(playersInHand);

        for (int i = 0; i < numFoldedPlayers; i++) {
            playersInHand.remove(0);
        }

        return playersInHand;
    }


    public static String[][] playPreFlop(String[][] playersHands, int numPlayers) { //this ruins everything!
        // add in changeable betting variables

        ArrayList<Integer> playersInHand = new ArrayList<>();
        Scanner playerInput = new Scanner(System.in);
        String playerDecision = "";
        ArrayList<Integer> playerBets = new ArrayList<>();
        String[][] finalPlayersHands = new String[numPlayers][2];

        int pot = 0;
        int bet = 10; //assumes big blind is $10

        while (true) { //this doesn't update the bets :(

            for (int i = 0; i < numPlayers; i++) { //make this affected by betting now!

                System.out.println("Player " + i + ": " + Arrays.toString(playersHands[i]));
                System.out.println("Do you wish to call, fold or raise? ");
                playerDecision = playerInput.next();

                if (playerDecision.equalsIgnoreCase("call")) {
                    System.out.println("Player " + i + " has called");
                    playersInHand.add(i);
                    System.out.println(playersInHand);
                    pot += bet;
                    playerBets.add(bet);
                } else if (playerDecision.equalsIgnoreCase("fold")) {
                    System.out.println("Player " + i + " has folded");
                    System.out.println(playersInHand);
                    //numPlayers--;
                } else if (playerDecision.equalsIgnoreCase("raise")) {
                    System.out.println("How much do you raise? ");
                    bet += playerInput.nextInt();
                    pot = pot + bet;
                    System.out.println("Player " + i + " has raised to $" + bet);
                    System.out.println("The total pot is now $" + pot);
                    playersInHand.add(i);
                    playerBets.add(bet);
                } //got to get this adjusted to keep looping until all players bets are the same.
            }
            numPlayers = playersInHand.size();

            for (int i = 0; i < numPlayers; i++) {

                finalPlayersHands[i][0] = playersHands[playersInHand.get(i)][0];
                finalPlayersHands[i][1] = playersHands[playersInHand.get(i)][1];
                //System.out.println(Arrays.toString(playersHands[i]) + ": player " + i);
            }

            if (!playersBetsEqual(playerBets)) { //fix the playersBetsEqual crap.
                //changedPot.println((String)pot);
                bettingCycle(playersInHand, finalPlayersHands, playerBets, pot); //figure out how to input these
                //changed within the method values to save to somewhere.
                System.out.println(playerBets);
                System.out.println(pot);
            }
            break;

        }

        getRidOfPlayers(playersInHand);

        String[][] finalHands = new String[playersInHand.size()][2];

        for (int i = 0; i < playersInHand.size(); i++) {

            finalHands[i][0] = finalPlayersHands[i][0];
            finalHands[i][1] = finalPlayersHands[i][1];

        }

        System.out.println(Arrays.deepToString(finalHands));
        return finalHands;

    }


    public static String playerHands(String[][] dealtCards) {
        int numOfPlayers = dealtCards.length;
        String playerHands = "";

        for (int i = 0; i < numOfPlayers; i++) {
            playerHands += "Player " + (i+1) + "'s hand is: " + dealtCards[i][0] + ", " + dealtCards[i][1] + "\n";
        }
        return playerHands;
    }


    public static String[] flop(String[] shuffledDeck, int numPlayers) {
        String[] flop = new String[3];
        for (int i=0; i<flop.length; i++) {
            flop[i] = shuffledDeck[(numPlayers*2) + i];
        }
        return flop;
    }

    public static String[] turn(String[] shuffledDeck, int numPlayers) { //add input of numplayers

        int turnCard = 3;
        int numOfBoardCards = 4;

        String[] turn = new String[numOfBoardCards];
        for (int i=0; i<turn.length - 1; i++) { //The flop cards...
            turn[i] = shuffledDeck[(numPlayers*2) + i];
        }

        turn[turnCard] = shuffledDeck[(numPlayers*2) + 4]; //The turn card
        return turn;
    }

    public static String[] river(String[] shuffledDeck, int numPlayers) {

        int turnCard = 3;
        int riverCard = 4;
        int numOfBoardCards = 5;

        String[] river = new String[numOfBoardCards];
        for (int i=0; i<river.length - 2; i++) { //The flop cards...
            river[i] = shuffledDeck[(numPlayers*2) + i];
        }

        river[turnCard] = shuffledDeck[(numPlayers*2) + 4]; //The turn card [add explanation of numbers]
        river[riverCard] = shuffledDeck[(numPlayers*2) + 5]; //The river card
        return river;
    }

    //implement a remove player method:

    public static void playHand(String[][] preflop, String[] board, int numPlayers) {
        //will need to be public static String[]

        playPreFlop(preflop, numPlayers);

        System.out.println();
        System.out.println("Here's the flop...");
        System.out.println("|" + board[0] + "| |" + board[1] + "| |" + board[2] + "|");
        System.out.println();
        System.out.println("Here's the board post turn... " );
        System.out.println("|" + board[0] + "| |" + board[1] + "| |" + board[2] + "| |" + board[3] + "|");
        System.out.println();
        System.out.println("Here's the board post river... ");
        System.out.println("|" + board[0] + "| |" + board[1] + "| |" + board[2] + "| |" + board[3] + "| |" + board[4] + "|");



    }

    public static String[] remainingCardsInDeck(int[][] cardsDealt, int numCards) {

        int cardPosition = 0;
        String[] cardsDealable = new String[52 - numCards];
        for (int i = 0; i < numSuits; i++) {
            for (int j = 0; j < numRanks; j++) {
                if (cardsDealt[i][j] == 0) {
                    cardsDealable[cardPosition] = allCards[i][j];
                    cardPosition++;
                }
            }
        }
        return cardsDealable;
    }


    public static void main(String[] args) throws IOException{
        String[] firstDeck = createDeck(4,13);

        System.out.println("Welcome to Texas Hold'Em :) Here are your cards.");
        System.out.println();
        String[] shuffledDeck = shuffleDeck(firstDeck);

        //System.out.println(Arrays.toString(shuffledDeck));
        System.out.println();

        System.out.println(playerHands(preFlop(shuffledDeck, 5)));
        playPreFlop(preFlop(shuffledDeck, 5), 5);

        System.out.println("Here's the board after the flop...");
        System.out.println(Arrays.toString(flop(shuffledDeck, 5)));
        System.out.println("Here's the board after the turn...");
        System.out.println(Arrays.toString(turn(shuffledDeck, 5)));
        System.out.println("Here's the board after the river...");
        System.out.println(Arrays.toString(river(shuffledDeck, 5)));



        ArrayList<Integer> notEqualBets = new ArrayList<>();
        notEqualBets.add(75);
        notEqualBets.add(75);
        notEqualBets.add(100);
        notEqualBets.add(100);
        notEqualBets.add(125);
        notEqualBets.add(125);
        System.out.println();
        System.out.println(playersBetsEqual(notEqualBets));

        String[][] preflopCards = new String[6][2];
        for (int i = 0; i < 6; i++) {
            preflopCards[i][0] = shuffledDeck[i];
            preflopCards[i][1] = shuffledDeck[(i + 5)];
        }
        ArrayList<Integer> players = new ArrayList<>();
        players.add(0);
        players.add(1);
        players.add(2);
        players.add(3);
        players.add(4);
        players.add(5);
        bettingCycle(players, preflopCards, notEqualBets, 600);

        ArrayList<Integer> equalBets = new ArrayList<>();
        equalBets.add(50);
        equalBets.add(50);
        equalBets.add(50);
        equalBets.add(50);
        System.out.println();
        System.out.println(playersBetsEqual(equalBets));

    }

}

