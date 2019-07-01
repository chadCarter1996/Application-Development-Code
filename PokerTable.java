//Code adjusted based on Nambi's original code
//code from https://stackoverflow.com/questions/18777893/jframe-background-image


import java.*;
import javax.imageio.IIOImage;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class PokerTable extends JFrame {

    //use the CrapsTable java class for guidance with this!!!
    private Card bCard1, bCard2, bCard3, bCard4, bCard5; //cards displayed on the board (similar to die class!)
    private final int delay = 20;
    private Timer clock;
    private PokerDisplayPanel display; //adjust PokerDisplayPanel to function similar to DisplayPanel
    private DisplayCards cardTable; //similar set up to DrawDealersArea

    private static int windowWidth = 1200;
    private static int windowHeight = 1000;
    private static int windowX = 120;
    private static int windowY = 120;
    //private static int borderWidth = 5;

    //constructor
    public PokerTable()
    {

        setTitle("Poker Table FINALLY Uploaded!");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Chad Carter\\Pictures\\PokerTable.png")));
        setLayout(new FlowLayout());
        // Just for refresh :) Not optional!
        setSize(699,499);
        setSize(700,500);

        //add card images here!
    }

    public static int getWindowX() {
        return windowX;
    }

    public static int getWindowY() {
        return windowY;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static void main(String[] args) {
        new PokerTable();
    }

}
