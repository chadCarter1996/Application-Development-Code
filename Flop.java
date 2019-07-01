import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class Flop extends JComponent {

    private String[] flopCardLocations;
    private String stage;

    public Flop(String[] flopCardLocations, String stage) {
        this.flopCardLocations = flopCardLocations;
        this.stage = stage;
        //JButton forFunButton = new JButton("ClickMe");

    }

    public void paint(Graphics g){

        Image offScreen = createImage(700,500);
        draw(offScreen.getGraphics());

        g.drawImage(offScreen,0,0, null);

    }

    public void draw(Graphics g) {

        String stage = this.stage;

        g.setColor(Color.GREEN.darker());
        g.fillRect(0,0,700,500);
        g.setColor(Color.BLACK);
        g.drawString("C&J Casino", 100, 50);

        if (stage.equalsIgnoreCase("flop")) {
            try {
                File cardFile1 = new File(flopCardLocations[0]);
                File cardFile2 = new File(flopCardLocations[1]);
                File cardFile3 = new File(flopCardLocations[2]);
                Image card1 = ImageIO.read(cardFile1);
                Image card2 = ImageIO.read(cardFile2);
                Image card3 = ImageIO.read(cardFile3);
                g.drawImage(card1.getScaledInstance(100, 140, 1), 300, 200, this);
                g.drawImage(card2.getScaledInstance(100, 140, 1), 170, 200, this);
                g.drawImage(card3.getScaledInstance(100, 140, 1), 40, 200, this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    static class DisplayBoardCards implements ActionListener {

        //private DrawCards cardsInHand;
        private String[] flopCardLocations;
        private String stage;
        private String[] preflopCards;
        private DrawCards cardsInHand;
        String[] boardAndHoleCards;

        public DisplayBoardCards(String[] preflopCards, DrawCards cardsInHand, String[] boardAndHoleCards,
                                 String[] flopCardLocations, String stage) {

            this.flopCardLocations = flopCardLocations;
            this.stage = stage;
            this.preflopCards = preflopCards;
            this.cardsInHand = cardsInHand;
            this.boardAndHoleCards = boardAndHoleCards;

        }

        public void actionPerformed(ActionEvent e) {

            JFrame pokerTable = new JFrame("Player Cards");
            pokerTable.setVisible(true);
            pokerTable.setSize(700, 500);

            //adjust this to paint the flop cards onto the JPanel...
            ImageIcon tableImage = new ImageIcon("C:\\Users\\Chad Carter\\Pictures\\PokerTable.png");
            JLabel background = new JLabel(tableImage);
            //figure out a way to paint the background image created in the stages, onto the JPanel created during each stage.

            JPanel playerChoices = new JPanel();
            pokerTable.add(playerChoices);

            //initializes player choice buttons!
            JButton callButton = new JButton("Call");
            JButton viewCardsButton = new JButton("View Cards");
            JButton raiseButton = new JButton("Raise");
            JButton foldButton = new JButton("Fold");
            JButton probabilityButton = new JButton("View Hand Probabilities");

            //sets button sizes!
            viewCardsButton.setSize(80, 30);
            callButton.setSize(50,30);
            raiseButton.setSize(50,30);
            foldButton.setSize(50,30);
            probabilityButton.setSize(120,30);

            playerChoices.add(callButton);
            playerChoices.add(raiseButton);
            playerChoices.add(foldButton);
            playerChoices.add(viewCardsButton);
            playerChoices.add(probabilityButton);
            viewCardsButton.addActionListener(new Card.DisplayCards(preflopCards, /*cardsInHand,*/ "preFlop"));
            String postFlopFileName = "stage_1_Flop";
            int[][] dealt5 = CardClassifier.representHand(boardAndHoleCards);
            String[] remainingCardsPF = PokerProbability.remainingCardsInDeck(dealt5, boardAndHoleCards.length);
            File probFlop = new File(postFlopFileName);

            double[] postFlopProbabilities = PokerProbability.allCombos5cards(remainingCardsPF, boardAndHoleCards);
            try {
                probFlop = PokerProbability.sendProbabilitiesToFile(postFlopProbabilities, postFlopFileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            pokerTable.add(new DrawCards(flopCardLocations, stage));
            probabilityButton.addActionListener(new PokerProbability.DisplayProbability(probFlop));



        }
    }


}
