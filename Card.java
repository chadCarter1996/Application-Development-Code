import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
//import javafx.animation.Animation;

public class Card extends JFrame {
    //code borrowed from alex2410 from Stack Overflow.
    // https://stackoverflow.com/questions/21045119/making-an-image-show-up-in-java
    //private Animation a;
    private int cardX = 0;
    private int cardY = 0;
    int leftSideY, leftSideX, rightSideY, rightSideX;
    private DrawCards cardsInHand;
    private String stage;

    public Card(String[] cardFiles, String playerName, String stage) {
        this.stage = stage;
        cardsInHand = new DrawCards(cardFiles, stage);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(cardsInHand);
        frame.setTitle(playerName);
        frame.pack();
        frame.setVisible(true);
    }

    static class DisplayCards implements ActionListener {

        //private DrawCards cardsInHand;
        private String[] playerCards;
        private String stage;
        public DisplayCards(String[] playerCards, String stage) {

            this.playerCards = playerCards;
            //this.cardsInHand = cardsInHand;
            this.stage = stage;

        }

        public void actionPerformed(ActionEvent e){
            if (stage.equalsIgnoreCase("preflop")) {
                JFrame CardWindow = new JFrame("Player Cards");
                CardWindow.setVisible(true);
                CardWindow.setSize(400, 350);
                CardWindow.add(new DrawCards(playerCards, stage));
            }
            else {
                JFrame CardWindow = new JFrame("Player Cards");
                CardWindow.setVisible(true);
                CardWindow.setSize(700, 500);
                CardWindow.add(new DrawCards(playerCards, stage));
            }
            //JLabel label = new JLabel("Your cards are: ");

            //CardWindow.add(new CardImage(playerCards[0]));
            //CardWindow.update();

        }

    }


    //figure this out!
    public void draw(Graphics g, String[] cards) {

        g.setColor(Color.GREEN.darker());
        g.fillRect(0, 0, getWidth(), getHeight());

        leftSideY = getHeight() / 4;
        rightSideY = getHeight() / 4;
        leftSideX = getWidth() / 8;
        rightSideX = getWidth() - getWidth() / 8;


        try {
            File cardFile1 = new File(cards[0]);
            File cardFile2 = new File(cards[1]);
            //CardImage card1 = new CardImage(cards[0]);
            Image card1 = ImageIO.read(cardFile1);
            Image card2 = ImageIO.read(cardFile2);
            g.drawImage(card1.getScaledInstance(50, 70, 1), getWidth() / 2 + 40, getHeight() - getHeight() / 6, this);
            g.drawImage(card2.getScaledInstance(50, 70, 1), getWidth() / 2 - 40, getHeight() - getHeight() / 6, this);
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }
    //patrickf, https://stackoverflow.com/questions/15558202/how-to-resize-image-in-java
    public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }

    class CardImage extends JPanel{

        private BufferedImage playingCard;
        private BufferedImage updated;

        CardImage(String pathname){
            try {
                File cardFile = new File(pathname);
                playingCard = ImageIO.read(cardFile);
            } catch (Exception e) {
                System.out.println("There was an error finding or reading the file" +  pathname );
            }
        }

        //updated = scale(playingCard, 30, 50);

        @Override
        @Transient
        public Dimension getPreferredSize() {
            return new Dimension(200, 280);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(playingCard, cardX, cardY, this);
        }

    }

    public static void main(String args[]) {
        int spade = 0;
        int club = 1;
        int diamond = 2;
        int heart = 3;

        String[] cardLinks = new String[2];
        int i = 0;

            //String[] cardLinks = new String[2];
        cardLinks[i] = Cards.cardImageLocations[spade][i];
        cardLinks[i+1] = Cards.cardImageLocations[spade][i+1];

        new Card(cardLinks, "spade", "preFlop");


        /*for (int i = 0; i < 13; i++) {
            new Card(Cards.cardImageLocations[club][i], "club");
        }

        for (int i = 0; i < 13; i++) {
            new Card(Cards.cardImageLocations[diamond][i], "diamond");
        }

        for (int i = 0; i < 13; i++) {
            new Card(Cards.cardImageLocations[heart][i], "heart");
        }
*/
        System.out.println(Cards.cardImageLocations[3][12]);
    }

}
