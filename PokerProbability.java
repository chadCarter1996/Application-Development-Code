import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.io.IOException;

public class PokerProbability extends CardClassifier implements Cards{

    public static String[][] probability(String[][] allHands, int numPlayers) {

        final int numPossibleHands = 10;
        int initialProbabilities = 0;
        int[][] probabilities = new int[10][numPlayers];
        String[][] returned = new String[numPossibleHands][numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            for (int j = 0; j < numPossibleHands; j++) {
                probabilities[i][j] = initialProbabilities;
            }
        }

        return returned;

    }

    //Needs to be edited to adjust for ALL player's cards!
    public static double calculateProbabilityOfFlush(int player, String[] playerHand) {

        double probabilityOfFlush;
        int[][] playerCards = representHand(playerHand);
        int[] suitCount = computeSuitCount(playerCards);
        int cardsDealt = playerHand.length;
        int numCardsToDeal = totalBoardAndHoleCards - cardsDealt;
        //System.out.println(numCardsToDeal + " " + Arrays.toString(suitCount));
        int greatestSuitCount = 0;

        for (int suit: suitCount) {

            if (suit >= greatestSuitCount) {
                greatestSuitCount = suit;
            }
            else greatestSuitCount += 0;

        }

        int cardsLeftInSuit = numRanks - greatestSuitCount;
        long numPossibleDeals = Combinations.combinations(numCardsInDeck - cardsDealt, numCardsToDeal);
        int numWaysToMakeFlush = 0;

        if (greatestSuitCount == 5) {
            numWaysToMakeFlush = (int)numPossibleDeals;
        }
        else if (greatestSuitCount == 4 && numCardsToDeal == 2) {
            //System.out.println("Number of most common suit cards: " + greatestSuitCount + " Cards dealable: " + numCardsToDeal);
            numWaysToMakeFlush += Combinations.combinations(cardsLeftInSuit, numCardsToDeal);
            numWaysToMakeFlush += Combinations.combinations(cardsLeftInSuit, 1)*(numCardsInDeck - (cardsDealt + cardsLeftInSuit));
        }
        else if (greatestSuitCount == 4 && numCardsToDeal == 1) {
            //System.out.println("Number of most common suit cards: " + greatestSuitCount + " Cards dealable: " + numCardsToDeal);
            numWaysToMakeFlush += Combinations.combinations(cardsLeftInSuit, numCardsToDeal);
        }
        else if (greatestSuitCount == 3 && numCardsToDeal == 2) {
            //System.out.println("Number of most common suit cards: " + greatestSuitCount + " Cards dealable: " + numCardsToDeal);
            numWaysToMakeFlush += Combinations.combinations(cardsLeftInSuit, numCardsToDeal);
        }

        probabilityOfFlush = (numWaysToMakeFlush/(numPossibleDeals*1.0));
        probabilityOfFlush = (double)Math.round(probabilityOfFlush*10000)/10000;

        System.out.println(numWaysToMakeFlush);
        return probabilityOfFlush;
    }

    public static double[] allCombos5cards(String[] undealtCards, String[] playerHand) { //implement this in!!!!!!!!!

        long numElements = 2*Combinations.combinations(undealtCards.length, 2);
        String[][] checkedHand = new String[(int)numElements][7];
        int position = 0;
        int[] outs = new int[11];
        double[] probabilities = new double[11];

        for (String element: undealtCards) {
            for (String element2: undealtCards) {
                if (!element.equals(element2)) {
                    checkedHand[position][0] = element;
                    checkedHand[position][1] = element2;
                    checkedHand[position][2] = playerHand[0];
                    checkedHand[position][3] = playerHand[1];
                    checkedHand[position][4] = playerHand[2];
                    checkedHand[position][5] = playerHand[3];
                    checkedHand[position][6] = playerHand[4];
                    if (HasHand.checkForHand(RoyalFlush.isRoyalFlush(checkedHand[position]))) {
                        outs[0]++;
                    }
                    else if (HasHand.checkForHand(StraightFlush.isStraightFlush(checkedHand[position]))) {
                        outs[1]++;
                    }
                    else if (!Arrays.equals(Quads.is4ofAKind(checkedHand[position]), emptyHand)) { //Change to HasHand...
                        outs[2]++;
                    }
                    else if (!Arrays.equals(FullHouse.isFullHouse(checkedHand[position]), emptyHand)) {
                        outs[3]++;
                    }
                    else if (!Arrays.equals(Flush.isFlush(checkedHand[position]), emptyHand)) {
                        outs[4]++;
                    }
                    else if (!Arrays.equals(Straight.isStraight(checkedHand[position]), emptyHand)) {
                        outs[5]++;
                    }
                    else if (!Arrays.equals(Trips.is3ofAKind(checkedHand[position]), emptyHand)) {
                        outs[6]++;
                    }
                    else if (!Arrays.equals(TwoPair.isTwoPair(checkedHand[position]), emptyHand)) {
                        outs[7]++;
                    }
                    else if (!Arrays.equals(Pair.isPair(checkedHand[position]), emptyHand)) {
                        outs[8]++;
                    }
                    else outs[9]++;


                    position++;

                }

                for (int num = 0; num < outs.length; num++) {
                    probabilities[num] = 100*(double)Math.round((outs[num]/(double)position)*1000)/1000;
                }

            }

        }
        int highProbabilityIndex = 0;
        for (int handIndex = 0; handIndex < outs.length - 1; handIndex++) {
            if (probabilities[handIndex + 1] > probabilities[handIndex]) {
                highProbabilityIndex = handIndex + 1;
            }
        }
        probabilities[10] = highProbabilityIndex;

        return probabilities;

    }

    public static double[] allCombos6cards(String[] undealtCards, String[] playerHand) { //implement this in!!!!!!!!!

        long numElements = Combinations.combinations(undealtCards.length, 1);
        String[][] checkedHand = new String[(int)numElements][7];
        int position = 0;
        int[] outs = new int[11];
        double[] probabilities = new double[11];

        for (String element: undealtCards) {

            checkedHand[position][0] = element;
            checkedHand[position][1] = playerHand[0];
            checkedHand[position][2] = playerHand[1];
            checkedHand[position][3] = playerHand[2];
            checkedHand[position][4] = playerHand[3];
            checkedHand[position][5] = playerHand[4];
            checkedHand[position][6] = playerHand[5];

            if (HasHand.checkForHand(RoyalFlush.isRoyalFlush(checkedHand[position]))
                /*!Arrays.equals(RoyalFlush.isRoyalFlush(checkedHand[position]), emptyHand)*/) {
                outs[0]++;
            }
            else if (HasHand.checkForHand(StraightFlush.isStraightFlush(checkedHand[position]))) {
                outs[1]++;
            }
            else if (!Arrays.equals(Quads.is4ofAKind(checkedHand[position]), emptyHand)) { //change to HadHand etc...
                outs[2]++;
            }
            else if (!Arrays.equals(FullHouse.isFullHouse(checkedHand[position]), emptyHand)) {
                outs[3]++;
            }
            else if (!Arrays.equals(Flush.isFlush(checkedHand[position]), emptyHand)) {
                outs[4]++;
            }
            else if (!Arrays.equals(Straight.isStraight(checkedHand[position]), emptyHand)) {
                outs[5]++;
            }
            else if (!Arrays.equals(Trips.is3ofAKind(checkedHand[position]), emptyHand)) {
                outs[6]++;
            }
            else if (!Arrays.equals(TwoPair.isTwoPair(checkedHand[position]), emptyHand)) {
                outs[7]++;
            }
            else if (!Arrays.equals(Pair.isPair(checkedHand[position]), emptyHand)) {
                outs[8]++;
            }
            else if (!Arrays.equals(HighCard.isHighCard(checkedHand[position]), emptyHand)) {
                outs[9]++;
            }

            position++;

        }

        for (int num = 0; num < outs.length; num++) {
            probabilities[num] = 100*(double)Math.round((outs[num]/(double)position)*1000)/1000;
        }

        int highProbabilityIndex = 0;
        for (int handIndex = 0; handIndex < outs.length - 1; handIndex++) {
            if (probabilities[handIndex + 1] > probabilities[handIndex]) {
                highProbabilityIndex = handIndex + 1;
            }
        }
        probabilities[10] = highProbabilityIndex;

        //System.out.println(Arrays.toString(playerHand));
        //System.out.println(totalProbability);
        return probabilities;

    }

    public static double[] allCombos7cards(String[] playerHand) { //implement this in!!!!!!!!!

        //long numElements = Combinations.combinations(undealtCards.length, 1);
        String[][] checkedHand = new String[1][7];
        int position = 0;
        int[] outs = new int[11];
        double[] probabilities = new double[11];


        checkedHand[position][0] = playerHand[0];
        System.out.println(checkedHand[position][0]);
        checkedHand[position][1] = playerHand[1];
        System.out.println(checkedHand[position][1]);
        checkedHand[position][2] = playerHand[2];
        System.out.println(checkedHand[position][2]);
        checkedHand[position][3] = playerHand[3];
        System.out.println(checkedHand[position][3]);
        checkedHand[position][4] = playerHand[4];
        System.out.println(checkedHand[position][4]);
        checkedHand[position][5] = playerHand[5];
        System.out.println(checkedHand[position][5]);
        checkedHand[position][6] = playerHand[6];
        System.out.println(checkedHand[position][6]);

        if (HasHand.checkForHand(RoyalFlush.isRoyalFlush(checkedHand[position]))
            /*!Arrays.equals(RoyalFlush.isRoyalFlush(checkedHand[position]), emptyHand)*/) {
            outs[0]++;
        }
        else if (HasHand.checkForHand(StraightFlush.isStraightFlush(checkedHand[position]))) {
            outs[1]++;
        }
        else if (!Arrays.equals(Quads.is4ofAKind(checkedHand[position]), emptyHand)) { //change to HadHand etc...
            outs[2]++;
        }
        else if (!Arrays.equals(FullHouse.isFullHouse(checkedHand[position]), emptyHand)) {
            outs[3]++;
        }
        else if (!Arrays.equals(Flush.isFlush(checkedHand[position]), emptyHand)) {
            outs[4]++;
        }
        else if (!Arrays.equals(Straight.isStraight(checkedHand[position]), emptyHand)) {
            outs[5]++;
        }
        else if (!Arrays.equals(Trips.is3ofAKind(checkedHand[position]), emptyHand)) {
            outs[6]++;
        }
        else if (!Arrays.equals(TwoPair.isTwoPair(checkedHand[position]), emptyHand)) {
            outs[7]++;
        }
        else if (!Arrays.equals(Pair.isPair(checkedHand[position]), emptyHand)) {
            outs[8]++;
        }
        else if (!Arrays.equals(HighCard.isHighCard(checkedHand[position]), emptyHand)) {
            outs[9]++;
        }

        for (int num = 0; num < outs.length - 1; num++) {
            probabilities[num] = 100*(double)Math.round((outs[num]));
        }

        int highProbabilityIndex = 0;
        for (int handIndex = 0; handIndex < outs.length - 1; handIndex++) {
            if (probabilities[handIndex + 1] > probabilities[handIndex]) {
                highProbabilityIndex = handIndex + 1;
            }
        }
        probabilities[10] = highProbabilityIndex;

        //System.out.println(Arrays.toString(probabilities));
        return probabilities;

    }

    public static String displayProbabilities(double[] probabilities) {


        String probability = "";

        probability += "\n Given the cards dealt, the likelihood of your final hand being one of the following hands is: \n";
        probability += " Royal flush: " + probabilities[0] + "%\n";
        probability += " Straight flush: " + probabilities[1] + "%\n";
        probability += " Four-of-a-kind: " + probabilities[2] + "%\n";
        probability += " Full house: " + probabilities[3] + "%\n";
        probability += " Flush: " + probabilities[4] + "%\n";
        probability += " Straight: " + probabilities[5] + "%\n";
        probability += " Three-of-a-kind: " + probabilities[6] + "%\n";
        probability += " Two-pair: " + probabilities[7] + "%\n";
        probability += " Pair: " + probabilities[8] + "%\n";
        probability += " High Card: " + probabilities[9] + "%\n";


        return probability;
    }

    public static File sendProbabilitiesToFile(double[] probabilities, String fileString) throws IOException {

        int mostLikelyHand = (int)probabilities[10];
        String[] probability = new String[11];

        probability[0] = "Given the cards dealt, the likelihood of your final hand being one of the following hands is:      \n";
        if (mostLikelyHand == 0) {
            probability[1] = "[MOST LIKELY HAND] Royal flush: " + probabilities[0] + "%     \n";
        }
        else {
            probability[1] = " Royal flush: " + probabilities[0] + "%     \n";
        }

        if (mostLikelyHand == 1) {
            probability[2] = "[MOST LIKELY HAND] Straight flush: " + probabilities[1] + "%     \n";
        }
        else {
            probability[2] = " Straight flush: " + probabilities[1] + "%     \n";
        }
        if (mostLikelyHand == 2) {
            probability[3] = "[MOST LIKELY HAND] Four-of-a-kind: " + probabilities[2] + "%     \n";
        }
        else {
            probability[3] = " Four-of-a-kind: " + probabilities[2] + "%     \n";
        }

        if (mostLikelyHand == 3) {
            probability[4] = "[MOST LIKELY HAND] Full house: " + probabilities[3] + "%     \n";
        }
        else {
            probability[4] = " Full house: " + probabilities[3] + "%     \n";
        }

        if (mostLikelyHand == 4) {
            probability[5] = "[MOST LIKELY HAND] Flush: " + probabilities[4] + "%     \n";
        }
        else {
            probability[5] = " Flush: " + probabilities[4] + "%     \n";
        }

        if (mostLikelyHand == 5) {
            probability[6] = "[MOST LIKELY HAND] Straight: " + probabilities[5] + "%     \n";
        }
        else {
            probability[6] = " Straight: " + probabilities[5] + "%     \n";
        }

        if (mostLikelyHand == 6) {
            probability[7] = "[MOST LIKELY HAND] Three-of-a-kind: " + probabilities[6] + "%     \n";
        }
        else {
            probability[7] = " Three-of-a-kind: " + probabilities[6] + "%     \n";
        }

        if (mostLikelyHand == 7) {
            probability[8] = "[MOST LIKELY HAND] Two-Pair: " + probabilities[7] + "%     \n";
        }
        else {
            probability[8] = " Two-pair: " + probabilities[7] + "%    \n";
        }

        if (mostLikelyHand == 8) {
            probability[9] = "[MOST LIKELY HAND] Pair: " + probabilities[8] + "%     \n";
        }
        else {
            probability[9] = " Pair: " + probabilities[8] + "%    \n";
        }

        if (mostLikelyHand == 9) {
            probability[10] = "[MOST LIKELY HAND]  High Card: " + probabilities[9] + "%     \n";
        }
        else {
            probability[10] = " High Card: " + probabilities[9] + "%     \n";
        }

        File probabilityFile = new File(fileString);
        try {
            PrintWriter writer = new PrintWriter(probabilityFile);
            for (int i = 0; i < probability.length; i++) {
                writer.print(probability[i]+"\n");
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return probabilityFile;
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

    static class DisplayProbability implements ActionListener {

        File probablities;

        public DisplayProbability(File playerProbabilityFile) {

            probablities = playerProbabilityFile;
            //make a file with a probabilty function here!!!!!!!!!!!!!!!!!!!!

        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser(probablities);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            JTextArea textArea = new JTextArea(10, 1);
            int returnVal = fc.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    textArea.read(input, "READING FILE :)");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else {
                System.out.println("YOU HAVE FAILED ME!!!!!!!!!!!!");
            }
            frame.getContentPane().add(textArea, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        }

    }

    //public static String[] combineCards(String[] player1, STrin)


    public static void main(String[] args) throws FileNotFoundException {
        /*int[][] playerHand = {
                {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};*/

        //String[] playerHand = {"A-spades", "K-spades", "J-diamonds", "Q-spades", "8-spades"};
        //String[] playerHand2 = {"A-diamonds", "K-diamonds", "Q-diamonds", "6-spades", "4-clubs"};

        /*String[] woohoo = {"9-clubs", "6-hearts", "5-hearts", "3-diamonds", "7-spades", "K-spades", "J-clubs",
                "A-diamonds", "J-hearts", "3-hearts", "5-spades", "9-hearts", "A-hearts", "A-spades",
                "5-clubs", "8-clubs", "8-hearts", "7-hearts", "10-hearts", "3-spades", "10-clubs", "9-diamonds",
                "8-spades", "2-clubs", "8-diamonds", "4-clubs", "7-clubs", "K-hearts", "Q-hearts",
                "5-diamonds", "K-diamonds", "10-diamonds", "3-clubs", "4-diamonds", "J-spades",
                "J-diamonds", "2-diamonds", "Q-spades", "K-clubs", "A-clubs", "6-clubs", "4-hearts",
                "4-spades", "6-spades", "2-spades"};*/

        String[] player1Hand = {"10-clubs", "6-hearts"};
        String[] player2Hand = {"9-clubs", "3-hearts"};
        String[] boardPostFlop = {"10-diamonds", "6-clubs", "8-diamonds"};
        String[] boardPostTurn = {"", "", "", ""};
        String[] player1FullHandPF = {"10-clubs", "6-hearts","10-diamonds", "6-clubs", "8-diamonds"};
        String[] player2FullHandPF = {"9-clubs", "3-hearts","10-diamonds", "6-clubs", "8-diamonds"};
        String[] player1FullHandPT = {"","","","","",""};
        String[] player2FullHandPT = {"","","","","",""};

        String[] allDealtCardsPostFlop = {"9-clubs", "3-hearts", "10-clubs", "6-hearts", "10-diamonds", "6-clubs", "8-diamonds"};
        String[] allDealtCardsPostTurn = {"", "", "", "", "", "", "", ""};


        int[][] dealt1 = CardClassifier.representHand(allDealtCardsPostFlop);
        //int[][] dealt2 = CardClassifier.representHand(allDealtCardsPostTurn);
        String[] remainingCardsPF = remainingCardsInDeck(dealt1, 7);
        System.out.println(Arrays.toString(remainingCardsPF));
        //String[] remainingCardsPT = remainingCardsInDeck(dealt2, 8);

        //System.out.println(Arrays.toString(remainingCardsInDeck(dealt1, 5)));

        double[] player1PostFlop = allCombos5cards(remainingCardsPF, player1FullHandPF);
        //double[] player1PostTurn = allCombos6cards(player1FullHandPT, remainingCardsPT);
        double[] player2PostFlop = allCombos5cards(remainingCardsPF, player2FullHandPF);
        //double[] player2PostTurn = allCombos6cards(player2FullHandPT, remainingCardsPT);

        String[] royalFlush = {"10-diamonds", "10-spades", "Q-clubs", "J-clubs", "K-clubs", "A-clubs"};

        int[][] dealt3 = CardClassifier.representHand(royalFlush);
        //System.out.println(Arrays.deepToString(dealt3));
        double[] player3RoyalPostTurn = allCombos6cards(remainingCardsInDeck(dealt3, 6), royalFlush);
        System.out.println(Arrays.toString(player3RoyalPostTurn));

        System.out.println("Post Flop Probabilities: Player 1");
        System.out.println(displayProbabilities(player1PostFlop));
        //System.out.println("Post Flop Probabilities: Player 2");
        //System.out.println(displayProbabilities(player1PostTurn));
        System.out.println("Post Turn Probabilities: Player 1");
        System.out.println(displayProbabilities(player2PostFlop));
        try {
            sendProbabilitiesToFile(player2PostFlop, "chooseThisFileForPostFlop");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //System.out.println("Post Turn Probabilities: Player 2");
        //System.out.println(displayProbabilities(player2PostTurn));
        //System.out.println(calculateProbabilityOfFlush(5, playerHand));
        //System.out.println(calculateProbabilityOfFlush(5, playerHand2));

    }


}
