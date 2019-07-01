import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class DrawCards extends JComponent {

    private String[] cards;
    private String stage;
    public DrawCards(String[] cards, String stage) {
        this.cards = cards;
        this.stage = stage;
    }

    public void paint(Graphics g){

        if (stage.equalsIgnoreCase("preflop")) {
            Image offScreen = createImage(340, 250);
            draw(offScreen.getGraphics());

            g.drawImage(offScreen, 0, 0, this);
        }
        else if (stage.equalsIgnoreCase("flop")) {
            Image offScreen = createImage(800, 500);
            draw(offScreen.getGraphics());

            g.drawImage(offScreen, 0, 0, this);
        }
        else if (stage.equalsIgnoreCase("turn")) {
            Image offScreen = createImage(800, 500);
            draw(offScreen.getGraphics());

            g.drawImage(offScreen, 0, 0, this);
        }
        else if (stage.equalsIgnoreCase("river")) {
            Image offScreen = createImage(800, 500);
            draw(offScreen.getGraphics());

            g.drawImage(offScreen, 0, 0, this);
        }
        else if (stage.equalsIgnoreCase("winner")) {
            Image offScreen = createImage(800, 500);
            draw(offScreen.getGraphics());

            g.drawImage(offScreen, 0, 0, this);
        }

    }

    public void draw(Graphics g) {

        int fontSize = 50;
        g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));

        if (stage.equalsIgnoreCase("preflop")) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0,0,400,350);
            g.setColor(Color.BLACK.darker());
            g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize/2));
            g.drawString("C&J Casino Texas Hold 'Em", 10, 30);
            try {
                File cardFile1 = new File(cards[0]);
                File cardFile2 = new File(cards[1]);
                Image card1 = ImageIO.read(cardFile1);
                Image card2 = ImageIO.read(cardFile2);
                g.drawImage(card1.getScaledInstance(135, 180, 1), 180, 40, this);
                g.drawImage(card2.getScaledInstance(135, 180, 1), 20, 40, this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (stage.equalsIgnoreCase("Flop")) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0,0,700,500);
            //g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize/2));
            g.setColor(Color.BLACK.darker());
            g.drawString("C&J Casino Texas Hold 'Em", 10, 75);
            try {
                File cardFile1 = new File(cards[0]);
                File cardFile2 = new File(cards[1]);
                File cardFile3 = new File(cards[2]);
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
        else if (stage.equalsIgnoreCase("turn")) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0,0,700,500);
            g.setColor(Color.BLACK.darker());
            g.drawString("C&J Casino Texas Hold 'Em", 10, 75);

            try {
                File cardFile1 = new File(cards[0]);
                File cardFile2 = new File(cards[1]);
                File cardFile3 = new File(cards[2]);
                File cardFile4 = new File(cards[3]);
                Image card1 = ImageIO.read(cardFile1);
                Image card2 = ImageIO.read(cardFile2);
                Image card3 = ImageIO.read(cardFile3);
                Image card4 = ImageIO.read(cardFile4);
                g.drawImage(card4.getScaledInstance(100, 140, 1), 430, 200, this);
                g.drawImage(card1.getScaledInstance(100, 140, 1), 300, 200, this);
                g.drawImage(card2.getScaledInstance(100, 140, 1), 170, 200, this);
                g.drawImage(card3.getScaledInstance(100, 140, 1), 40, 200, this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (stage.equalsIgnoreCase("river")) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0,0,700,500);
            g.setColor(Color.BLACK.darker());
            g.drawString("C&J Casino Texas Hold 'Em", 10, 75);
            try {
                File cardFile1 = new File(cards[0]);
                File cardFile2 = new File(cards[1]);
                File cardFile3 = new File(cards[2]);
                File cardFile4 = new File(cards[3]);
                File cardFile5 = new File(cards[4]);
                Image card1 = ImageIO.read(cardFile1);
                Image card2 = ImageIO.read(cardFile2);
                Image card3 = ImageIO.read(cardFile3);
                Image card4 = ImageIO.read(cardFile4);
                Image card5 = ImageIO.read(cardFile5);
                g.drawImage(card5.getScaledInstance(100,140, 1), 560, 200, this);
                g.drawImage(card4.getScaledInstance(100, 140, 1), 430, 200, this);
                g.drawImage(card1.getScaledInstance(100, 140, 1), 300, 200, this);
                g.drawImage(card2.getScaledInstance(100, 140, 1), 170, 200, this);
                g.drawImage(card3.getScaledInstance(100, 140, 1), 40, 200, this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (stage.equalsIgnoreCase("winner")) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0,0,700,500);
            g.setColor(Color.BLACK.darker());
            g.drawString("C&J Casino Texas Hold 'Em", 10, 75);
            try {
                File cardFile1 = new File(cards[0]);
                File cardFile2 = new File(cards[1]);
                File cardFile3 = new File(cards[2]);
                File cardFile4 = new File(cards[3]);
                File cardFile5 = new File(cards[4]);
                Image card1 = ImageIO.read(cardFile1);
                Image card2 = ImageIO.read(cardFile2);
                Image card3 = ImageIO.read(cardFile3);
                Image card4 = ImageIO.read(cardFile4);
                Image card5 = ImageIO.read(cardFile5);
                g.drawImage(card5.getScaledInstance(100,140, 1), 560, 200, this);
                g.drawImage(card4.getScaledInstance(100, 140, 1), 430, 200, this);
                g.drawImage(card3.getScaledInstance(100, 140, 1), 300, 200, this);
                g.drawImage(card2.getScaledInstance(100, 140, 1), 170, 200, this);
                g.drawImage(card1.getScaledInstance(100, 140, 1), 40, 200, this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }



    public static void main(String[] args) {
        int spade = 0;
        String[] cardLinks = new String[3];
        int i = 0;

        cardLinks[i] = Cards.cardImageLocations[spade][i];
        cardLinks[i+1] = Cards.cardImageLocations[spade][i+1];
        cardLinks[i+2] = Cards.cardImageLocations[spade][i+2];
        new DrawCards(cardLinks, "Flop");
    }

}
