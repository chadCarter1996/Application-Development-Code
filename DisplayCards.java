// cards from (potentially) https://commons.wikimedia.org/wiki/Category:SVG_playing_cards
// these cards are not copyrighted and are free to use by the public

import javafx.animation.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DisplayCards implements Cards{ //displays card Table interface! Adjusts for playing table!

    private Animation a;
    private int cardX;
    private int cardY;
    //private PlayerInterface table = new PlayerInterface();
    private HashMap<String, String> filePaths;
    private String[] woo;

    public DisplayCards(Animation a) {

        this.a = a;

    }

    public static String[] getCardsInHand(String[] playerCards, HashMap<String, String> cardPaths) {

        String[] handCards = new String[playerCards.length];
        for (int i = 0; i < handCards.length; i++) {
            handCards[i] = cardPaths.get(playerCards[i]);
        }

        return handCards;
    }

    public void draw(Graphics g, String[] cards) {

        //table.draw(g, cards);

    }

    public static void main(String[] args) {

    }

}
