//created by Chad Carter and Joey Gale
//1/31/19

import java.util.HashMap;

public interface Cards {

    int numCardsInDeck = 52;
    int totalBoardAndHoleCards = 7;
    int numSuits = 4;
    int numRanks = 13;
    int numFinalCards = 5;
    //array that eventually helps display best hand
    String[] emptyHand = {"", "", "", "", ""};
    HashMap<String, String> cardLocationsMap = new HashMap<>();

    //the card deck: sorted
    //Array columns represent card ranks (2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A)
    //Array rows represent card suits (spades, clubs, diamonds, hearts)
    String[][] allCards =
            {
                    {"2-spades", "3-spades", "4-spades", "5-spades", "6-spades", "7-spades", "8-spades",
                            "9-spades", "10-spades", "J-spades", "Q-spades", "K-spades", "A-spades"},

                    {"2-clubs", "3-clubs", "4-clubs", "5-clubs", "6-clubs", "7-clubs", "8-clubs",
                            "9-clubs", "10-clubs", "J-clubs", "Q-clubs", "K-clubs", "A-clubs"},

                    {"2-diamonds", "3-diamonds", "4-diamonds", "5-diamonds", "6-diamonds", "7-diamonds", "8-diamonds",
                            "9-diamonds", "10-diamonds", "J-diamonds", "Q-diamonds", "K-diamonds", "A-diamonds"},

                    {"2-hearts", "3-hearts", "4-hearts", "5-hearts", "6-hearts", "7-hearts", "8-hearts",
                            "9-hearts", "10-hearts", "J-hearts", "Q-hearts", "K-hearts", "A-hearts"}
            };

    //each card has it's own image, here's the image locations of all cards

    String filepath = "C:/Users/Chad Carter/IdeaProjects/Poker Capstone/Card Images/";
    String[][] cardImageLocations = {
            //Spades
            {filepath+"DeuceOfSpades.png", filepath+"3ofSpades.png", filepath+"4ofSpades.png", filepath+"5ofSpades.png",
                    filepath+"6ofSpades.png", filepath+"7ofSpades.png", filepath+"8ofSpades.png", filepath+"9ofSpades.png",
                    filepath+"10ofSpades.png", filepath+"JackofSpades.png", filepath+"QueenOfSpades.png", filepath+"KingOfSpades.png",
                    filepath+"AceOfSpades.png"
            },
            //Clubs
            {filepath+"DeuceOfClubs.png", filepath+"3ofClubs.png", filepath+"4ofClubs.png", filepath+"5ofClubs.png",
                    filepath+"6ofClubs.png", filepath+"7ofClubs.png", filepath+"8ofClubs.png", filepath+"9ofClubs.png",
                    filepath+"10ofClubs.png", filepath+"JackofClubs.png", filepath+"QueenOfClubs.png", filepath+"KingOfClubs.png",
                    filepath+"AceOfClubs.png"
            },
            //Diamonds
            {filepath+"DeuceOfDiamonds.png", filepath+"3ofDiamonds.png", filepath+"4ofDiamonds.png", filepath+"5ofDiamonds.png",
                    filepath+"6ofDiamonds.png", filepath+"7ofDiamonds.png", filepath+"8ofDiamonds.png", filepath+"9ofDiamonds.png",
                    filepath+"10ofDiamonds.png", filepath+"JackofDiamonds.png", filepath+"QueenOfDiamonds.png", filepath+"KingOfDiamonds.png",
                    filepath+"AceOfDiamonds.png"
            },
            //Hearts
            {filepath+"DeuceOfHearts.png", filepath+"3ofHearts.png", filepath+"4ofHearts.png", filepath+"5ofHearts.png",
                    filepath+"6ofHearts.png", filepath+"7ofHearts.png", filepath+"8ofHearts.png", filepath+"9ofHearts.png",
                    filepath+"10ofHearts.png", filepath+"JackofHearts.png", filepath+"QueenOfHearts.png", filepath+"KingOfHearts.png",
                    filepath+"AceOfHearts.png"
            }

    };
    //for (int i= 0; )

}