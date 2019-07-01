import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.beans.Transient;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//this could be useful!
//https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html#card

//code from https://stackoverflow.com/questions/14661571/java-how-can-i-import-and-display-sprites
//code sharer: sanoj00
//THANK YOU SUPER AWESOME DUDE!

public class UserInterface extends JFrame implements Cards{
//FIGURE OUT a way to pass in the card string array for each player, create a temporary view of each player's cards AND allow
//The hands to reset after the round (easy right...)
    private static final long serialVersionUID = 1L;
    int WIDTH = 800;
    int HEIGHT = 500;
    int SCALE = 1;
    int cardX1,cardY1;
    int x1,y1;
    private int leftSideY, rightSideY, leftSideX, rightSideX;

    public static String[] cardsInHand(String[] playerCards, HashMap<String, String> cardPaths) {

        String[] handCards = new String[playerCards.length];
        for (int i = 0; i < handCards.length; i++) {
            handCards[i] = cardPaths.get(playerCards[i]);
        }

        return handCards;
    }


    public UserInterface(){

        setSize(WIDTH * SCALE, HEIGHT * SCALE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("C&J Casino: Texas Hold 'Em");
        setVisible(true);

        PokerDisplayPanel display = new PokerDisplayPanel();

        //JPanel playerChoices = new JPanel();
        //UserInterface.PlayGame();


        //paint(getGraphics(), playerHand);

        addKeyListener(new Input());

        cardX1=getWidth()/2-16;
        cardY1=getHeight()/2-16;

        try {
            while(true){
                //update();
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void paint(Graphics g){


        //String[] cards = cardsInHand(playerCards, cardPaths);
        String[][] cardCompilation= cardImageLocations;

        String card1 = cardCompilation[3][12];
        String card2 = cardCompilation[3][11];

        String[] playerHand = new String[2];
        playerHand[0] = card1;
        playerHand[1] = card2;

        Image offScreen = createImage(getWidth(),getHeight());
        //for (int i = 0; i < playerHand.length; i++) {
        draw(offScreen.getGraphics(), playerHand);
        //}

        g.drawImage(offScreen,0,0,this);

    }


    public void draw(Graphics g, String[] cardImageLocations){
        //Nicolas Modrzyk Stack Overflow contributor.
        g.setColor(Color.GREEN.darker());
        g.fillRect(0,0,getWidth(),getHeight());

        leftSideY = getHeight()/4;
        rightSideY = getHeight()/4;
        leftSideX = getWidth()/8;
        rightSideX = getWidth() - getWidth()/8;


        try {
            File cardFile1 = new File(cardImageLocations[0]);
            File cardFile2 = new File(cardImageLocations[1]);
            Image card1 = ImageIO.read(cardFile1);
            Image card2 = ImageIO.read(cardFile2);
            g.drawImage(card1.getScaledInstance(50, 70,1), getWidth()/2 + 40, getHeight() - getHeight()/6, this);
            g.drawImage(card2.getScaledInstance(50, 70,1), getWidth()/2 - 40, getHeight() - getHeight()/6, this);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        int fontSize = 67;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(Color.orange.darker());
        g.drawString("C&J Casino", getWidth()/2 - 150, getHeight()/2 + 50);

        g.setColor(Color.BLACK);

        //draws the top players cards
        g.drawRect(getWidth()/2 + 40, getHeight()/6, 50, 70);
        g.setColor(Color.red);
        g.fillRect(getWidth()/2 + 42, getHeight()/6 + 2, 50, 70);
        g.setColor(Color.BLACK);

        g.drawRect(getWidth()/2 - 40, getHeight()/6, 50, 70);
        g.setColor(Color.red);
        g.fillRect(getWidth()/2 - 38, getHeight()/6 + 2, 48, 68);
        g.setColor(Color.BLACK);


        for (int i = 0; i < 3; i++) {

            //draw the rightmost side cards
            g.drawRect(rightSideX - 40, rightSideY + rightSideY*i, 50, 70);
            g.setColor(Color.red);
            g.fillRect(rightSideX - 38, rightSideY + rightSideY*i + 2, 48, 68);

            //draw the cards next to the rightmost cards
            g.setColor(Color.BLACK);
            g.drawRect(rightSideX + 40, rightSideY + rightSideY*i, 50, 70);
            g.setColor(Color.red);
            g.fillRect(rightSideX + 42, rightSideY + rightSideY*i + 2, 48, 68);

            //draw the leftmost side cards
            g.setColor(Color.BLACK);
            g.drawRect(leftSideX + 40, leftSideY + leftSideY*i, 50, 70);
            g.setColor(Color.red);
            g.fillRect(leftSideX + 42, rightSideY + rightSideY*i + 2, 48, 68);

            //draw the cards next to the leftmost cards
            g.setColor(Color.BLACK);
            g.drawRect(leftSideX - 40, leftSideY + leftSideY*i, 50, 70);
            g.setColor(Color.red);
            g.fillRect(leftSideX -38, rightSideY + rightSideY*i + 2, 48, 68);

        }

        repaint();

    }

    public void setX(int x){
        this.cardX1=x;
    }
    public void setY(int y){
        this.cardY1=y;
    }
    /*public void update(){
        heroX+=x;
        heroY+=y;
    }*/

    public class Input implements KeyListener{

        public void keyPressed(KeyEvent e) {
            int keyCode=e.getKeyCode();

            if(keyCode==KeyEvent.VK_W){
                setY(-5);
            }
            if(keyCode==KeyEvent.VK_A){
                setX(-5);
            }
            if(keyCode==KeyEvent.VK_S){
                setY(5);
            }
            if(keyCode==KeyEvent.VK_D){
                setX(5);
            }
            if(keyCode==KeyEvent.VK_ESCAPE){
                stop();
            }
        }

        public void keyReleased(KeyEvent e) {
            int keyCode=e.getKeyCode();

            if(keyCode==KeyEvent.VK_W){
                setY(0);
            }
            if(keyCode==KeyEvent.VK_A){
                setX(0);
            }
            if(keyCode==KeyEvent.VK_S){
                setY(0);
            }
            if(keyCode==KeyEvent.VK_D){
                setX(0);
            }
        }

        public void keyTyped(KeyEvent e) {

        }

    }

    public void run() {

        new Thread().start();
    }

    public void stop(){

        System.exit(0);
    }

    //String[] cardImageLocations;
    //MrJavaHelp https://m.youtube.com/watch?v=jEXxaPQ_fQo
    static class PlayGame extends JComponent implements ActionListener {

        private String[] cards;
        private DrawCards cardsInHand;
        private String[] boardAndHoleCards;
        private String stage;
        private String[] boardCardLocations;
        PokerTable table;

        public PlayGame(String[] cards, DrawCards cardsInHand, String[] boardAndHoleCards, String stage,
                        String[] boardCardLocations) {
            this.cards = cards;
            this.cardsInHand = cardsInHand;
            this.boardAndHoleCards = boardAndHoleCards;
            this.stage = stage;
            this.boardCardLocations = boardCardLocations;
            this.table = table;
        }

        public void actionPerformed(ActionEvent e) {

            JFrame pokerTable = new JFrame("C&J Casino Texas Hold 'Em");
            //pokerTable.add(new PokerTable());
            pokerTable.setVisible(true);
            pokerTable.setSize(700,500);

            //JPanel display ideas https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html#card
            //ImageIcon tableImage = new ImageIcon("C:\\Users\\Chad Carter\\Pictures\\PokerTable.png");
            //JLabel background = new JLabel(tableImage);
            //figure out a way to paint the background image created in the stages, onto the JPanel created during each stage.

            JPanel playerChoices = new JPanel();
            //playerChoices.add(background);
            playerChoices.setSize(700, 50);
            pokerTable.add(playerChoices);
            //for testing purposes
            //ImageIcon buttonImage = new
            //        ImageIcon("C:/Users/Chad Carter/IdeaProjects/Poker Capstone/Card Images/viewCardsButton.png");
            JButton callButton = new JButton("Call");
            JButton viewCardsButton = new JButton("View Cards");
            JButton raiseButton = new JButton("Raise");
            JButton foldButton = new JButton("Fold");
            JButton probabilityButton = new JButton("View Hand Probabilities");
            JButton dealCardsButton = new JButton("Show board post " + stage);

            viewCardsButton.setSize(80, 30);
            callButton.setSize(50,30);
            //add call command listener
            raiseButton.setSize(50,30);
            //add raise command listener
            foldButton.setSize(50,30);
            //add fold command listener
            probabilityButton.setSize(120,30);
            dealCardsButton.setSize(60, 30);
            //JButton winningHand = new JButton("View Winner");

            //UPLOAD hand probabilities list to display hand probabilities!!! Also add a listener!

            //////ADD THE BOARD CARDS TO THE INTERFACE NEXT!!!!!!!!!!!

            //File probPreFlop = new File("chooseThisFileForPreFlop");
            //add precalculated probabilities ;
            //MATH IS GREAT!!!!

            //implement a round by round command for each stage

            if (stage.equalsIgnoreCase("flop")) {

                //playerChoices.add(new Flop(boardCardLocations, stage));
                playerChoices.add(callButton);
                playerChoices.add(raiseButton);
                playerChoices.add(foldButton);
                playerChoices.add(viewCardsButton);
                playerChoices.add(probabilityButton);
                //playerChoices.add(dealCardsButton);
                pokerTable.getContentPane().add(new Flop(boardCardLocations, "flop"));
                playerChoices.setVisible(true);
                viewCardsButton.addActionListener(new Card.DisplayCards(cards, /*cardsInHand,*/ "preFlop"));
                //dealCardsButton.addActionListener(new Flop.DisplayBoardCards(cards, cardsInHand, boardAndHoleCards, boardCardLocations, stage));

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
                //dealCardsButton.addActionListener(new Flop.DisplayBoardCards(cards, cardsInHand, boardAndHoleCards,
                //        boardCardLocations, stage));
                probabilityButton.addActionListener(new PokerProbability.DisplayProbability(probFlop));
            }
            //enables the flop cards visual
            else if (stage.equalsIgnoreCase("turn")) {

                playerChoices.add(callButton);
                playerChoices.add(raiseButton);
                playerChoices.add(foldButton);
                playerChoices.add(viewCardsButton);
                playerChoices.add(probabilityButton);
                //playerChoices.add(dealCardsButton);
                //pokerTable.add(playerChoices);
                pokerTable.getContentPane().add(new Turn(boardCardLocations, "turn"));
                viewCardsButton.addActionListener(new Card.DisplayCards(cards, /*cardsInHand,*/ "preFlop"));

                String postTurnFileName = "stage_2_Turn";
                int[][] dealt6 = CardClassifier.representHand(boardAndHoleCards);
                String[] remainingCardsPT = PokerProbability.remainingCardsInDeck(dealt6, boardAndHoleCards.length);
                double[] postTurnProbabilities = PokerProbability.allCombos6cards(remainingCardsPT, boardAndHoleCards);
                File probTurn = new File(postTurnFileName);
                try {
                    probTurn = PokerProbability.sendProbabilitiesToFile(postTurnProbabilities, postTurnFileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //dealCardsButton.addActionListener(new Turn.DisplayTurnCards(cards, cardsInHand, boardAndHoleCards, boardCardLocations, stage));
                probabilityButton.addActionListener(new PokerProbability.DisplayProbability(probTurn));
            }

            else if (stage.equalsIgnoreCase("river")) {

                playerChoices.add(callButton);
                playerChoices.add(raiseButton);
                playerChoices.add(foldButton);
                playerChoices.add(viewCardsButton);
                playerChoices.add(probabilityButton);
                playerChoices.add(dealCardsButton);
                //pokerTable.add(playerChoices);
                viewCardsButton.addActionListener(new Card.DisplayCards(cards, /*cardsInHand,*/ "preFlop"));

                String postRiverFileName = "stage_3_River";
                double[] postRiverProbabilities = PokerProbability.allCombos7cards(boardAndHoleCards);

                File probRiver = new File(postRiverFileName);
                try {
                    probRiver = PokerProbability.sendProbabilitiesToFile(postRiverProbabilities, postRiverFileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dealCardsButton.addActionListener(new River.DisplayRiverCards(boardCardLocations, stage));
                probabilityButton.addActionListener(new PokerProbability.DisplayProbability(probRiver));
            }
           /* else if (stage.equalsIgnoreCase("winner")) {
                playerChoices.add(winningHand);
                //winningHand.addActionListener();
            }*/
        }

    }


    public static void main(String[] args) {


        /*JFrame frame = new JFrame("Main Menu");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);
        JButton playButton = new JButton("Play A Round of Hold 'Em?");
        mainPanel.add(playButton);
        String[] cards = {"C:/Users/Chad Carter/IdeaProjects/Poker Capstone/Card Images/3ofSpades.png",
                "C:/Users/Chad Carter/IdeaProjects/Poker Capstone/Card Images/DeuceOfSpades.png"};
        String stage1 = "preFlop";
        DrawCards cardsInHand = new DrawCards(cards, stage1);
        String[] player1FullHandPF = {"10-clubs", "6-hearts","10-diamonds", "6-clubs", "8-diamonds"};
        String stage = "Flop";
        String[] playerBoardCards = {cardImageLocations[2][8], cardImageLocations[1][4], cardImageLocations[2][6]};
        playButton.addActionListener(new PlayGame(cards, cardsInHand, player1FullHandPF, stage, playerBoardCards));*/

    }


}
