//import javax.*;
import java.*;
//import javafx.*;
import javax.imageio.IIOImage;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class PokerDisplayPanel extends JPanel {

    private Image pokerTable;
    //Timer time = new Timer(5, this);
    int x = 0;
    int velocityOfX = 2;
    private JTextField callText, foldText, raiseText, potText, winnerText;
    private int totalBet, totalPot;

    //constructor
    public PokerDisplayPanel() {

        //pokerTable = Toolkit.getDefaultToolkit().createImage("PokerTable.png");
        super(new GridLayout(2, 3, 10, 0));
        setBorder(new EmptyBorder(0, 0, 50, 0));

        Font displayFont = new Font("Dialog", Font.BOLD, 50);

        JLabel wonLabel = new JLabel("Call:");
        wonLabel.setFont(displayFont);
        add(wonLabel);

        JLabel lostLabel = new JLabel("Raise:");
        lostLabel.setFont(displayFont);
        add(lostLabel);

        JLabel buttonLabel = new JLabel("Fold:");
        buttonLabel.setFont(displayFont);
        add(buttonLabel);

        callText = new JTextField("  Call", 50);
        callText.setFont(displayFont);
        callText.setEditable(false);
        callText.setBackground(Color.WHITE);
        add(callText);

        foldText = new JTextField("  Fold", 50);
        foldText.setFont(displayFont);
        foldText.setEditable(false);
        foldText.setBackground(Color.WHITE);
        add(foldText);

        raiseText = new JTextField(5);
        raiseText.setFont(displayFont);
        raiseText.setEditable(false);
        raiseText.setBackground(Color.DARK_GRAY);
        add(raiseText);

    }

    public void update(String choice, int amountOfBet) {
        if (choice.equalsIgnoreCase("call")) {
            totalBet+= amountOfBet;
            potText.setText("  " + totalBet);
            winnerText.setText("");
            winnerText.setBackground(Color.DARK_GRAY);
        }

        else if (choice.equalsIgnoreCase("fold")) {
            totalBet+=0;
            potText.setText("  " + totalBet);
            winnerText.setText("");
            winnerText.setBackground(Color.DARK_GRAY);
        }

        else {
            potText.setText("  " + totalBet);
            potText.setBackground(Color.YELLOW);
        }
    }

    public void draw(Graphics background) {

        super.paint(background);
        background.drawImage(pokerTable, 320, 175, this);

    }

    /*public void cards(Graphics card, String Image) {

        super.paintComponent(card);
        setLayout(new BorderLayout());
        //setContentPane(new JLabel(new ImageIcon(Image)));
        setLayout(new FlowLayout());
        // Just for refresh :) Not optional!
        setSize(699,384);
        setSize(700,385);

    }
*/

    /*public void actionPerformed(ActionEvent e) {
        x = x + velocityOfX;
        repaint();
    }*/

    //public static void main(String[] args) {
        //new PokerDisplayPanel();
    //}



}
