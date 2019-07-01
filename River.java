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

public class River extends JComponent {

    private String[] flopCardLocations;
    private String stage;
    public River(String[] flopCardLocations, String stage) {
        this.flopCardLocations = flopCardLocations;
        this.stage = stage;
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

        if (stage.equalsIgnoreCase("turn")) {
            try {
                File cardFile1 = new File(flopCardLocations[0]);
                File cardFile2 = new File(flopCardLocations[1]);
                File cardFile3 = new File(flopCardLocations[2]);
                File cardFile4 = new File(flopCardLocations[3]);
                File cardFile5 = new File(flopCardLocations[4]);
                Image card1 = ImageIO.read(cardFile1);
                Image card2 = ImageIO.read(cardFile2);
                Image card3 = ImageIO.read(cardFile3);
                Image card4 = ImageIO.read(cardFile4);
                Image card5 = ImageIO.read(cardFile5);
                g.drawImage(card1.getScaledInstance(100, 140, 1), 300, 200, this);
                g.drawImage(card2.getScaledInstance(100, 140, 1), 170, 200, this);
                g.drawImage(card3.getScaledInstance(100, 140, 1), 40, 200, this);
                g.drawImage(card4.getScaledInstance(100,140, 1), 430,200,this);
                g.drawImage(card5.getScaledInstance(100, 140, 1), 560, 200, this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    static class DisplayRiverCards implements ActionListener {

        //private DrawCards cardsInHand;
        private String[] riverCardLocations;
        private String stage;

        public DisplayRiverCards(String[] riverCardLocations, String stage) {

            this.riverCardLocations = riverCardLocations;
            this.stage = stage;

        }

        public void actionPerformed(ActionEvent e) {
            JFrame CardWindow = new JFrame("Board Cards Post Turn");
            CardWindow.setVisible(true);
            CardWindow.setSize(700, 500);
            CardWindow.add(new DrawCards(riverCardLocations, stage));
            //add the background display!!! IT WILL SHOW THE CARDS WITHOUT A BUTTON PUSH!!!


        }
    }


}