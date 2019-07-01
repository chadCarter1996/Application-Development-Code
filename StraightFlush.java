//created by Chad Carter and Joey Gale
//1/31/19

import java.util.Arrays;

public class StraightFlush extends CardClassifier implements Cards{

    public static String[] isStraightFlush(String[] playerHand) { //good to go!

        int numSuits = 4;
        boolean straightFlush = false; //initially, the player is assumed to NOT have a straight flush
        int[][] playerCards = representHand(playerHand);
        String[] returnedStraightFlush = new String[5];

        //System.out.print("Straight flush? ");

        for (int i = 0; i < numSuits; i++) { //checks to see if the player holds one of the 36 different straight flush possibilites
            //if statements used to save time/reduce running time from O(N^2) to O(N)
            //also, the if statements are placed in the order of strongest straight flush, to lowest

            if (playerCards[i][11] == 1 && playerCards[i][10] == 1 && playerCards[i][9] == 1 &&
                    playerCards[i][8] == 1 && playerCards[i][7] ==1) { //K-Q-J-10-9 straight flush
                    straightFlush = true; //a K-Q-J-10-9 straight flush is found
                    returnedStraightFlush[0] = allCards[i][11];
                    returnedStraightFlush[1] = allCards[i][10];
                    returnedStraightFlush[2] = allCards[i][9];
                    returnedStraightFlush[3] = allCards[i][8];
                    returnedStraightFlush[4] = allCards[i][7];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][10] == 1 && playerCards[i][9] == 1 && playerCards[i][8] == 1 &&
                    playerCards[i][7] == 1 && playerCards[i][6] ==1) { //Q-J-10-9-8 straight flush
                    straightFlush = true; //a Q-J-10-9-8 straight flush is found
                    returnedStraightFlush[0] = allCards[i][10];
                    returnedStraightFlush[1] = allCards[i][9];
                    returnedStraightFlush[2] = allCards[i][8];
                    returnedStraightFlush[3] = allCards[i][7];
                    returnedStraightFlush[4] = allCards[i][6];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][9] == 1 && playerCards[i][8] == 1 && playerCards[i][7] == 1 &&
                    playerCards[i][6] == 1 && playerCards[i][5] ==1) { //J-10-9-8-7 straight flush
                    straightFlush = true; //a J-10-9-8-7 straight flush is found
                    returnedStraightFlush[0] = allCards[i][9];
                    returnedStraightFlush[1] = allCards[i][8];
                    returnedStraightFlush[2] = allCards[i][7];
                    returnedStraightFlush[3] = allCards[i][6];
                    returnedStraightFlush[4] = allCards[i][5];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][8] == 1 && playerCards[i][7] == 1 && playerCards[i][6] == 1 &&
                    playerCards[i][5] == 1 && playerCards[i][4] ==1) { //10-9-8-7-6 straight flush
                    straightFlush = true; //a 10-9-8-7-6 straight flush is found
                    returnedStraightFlush[0] = allCards[i][8];
                    returnedStraightFlush[1] = allCards[i][7];
                    returnedStraightFlush[2] = allCards[i][6];
                    returnedStraightFlush[3] = allCards[i][5];
                    returnedStraightFlush[4] = allCards[i][4];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][7] == 1 && playerCards[i][6] == 1 && playerCards[i][5] == 1 &&
                    playerCards[i][4] == 1 && playerCards[i][3] ==1) { //9-8-7-6-5 straight flush
                    straightFlush = true; //a 9-8-7-6-5 straight flush is found
                    returnedStraightFlush[0] = allCards[i][7];
                    returnedStraightFlush[1] = allCards[i][6];
                    returnedStraightFlush[2] = allCards[i][5];
                    returnedStraightFlush[3] = allCards[i][4];
                    returnedStraightFlush[4] = allCards[i][3];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][6] == 1 && playerCards[i][5] == 1 && playerCards[i][4] == 1 &&
                    playerCards[i][3] == 1 && playerCards[i][2] ==1) { //8-7-6-5-4 straight flush
                    straightFlush = true; //an 8-7-6-5-4 straight flush is found
                    returnedStraightFlush[0] = allCards[i][6];
                    returnedStraightFlush[1] = allCards[i][5];
                    returnedStraightFlush[2] = allCards[i][4];
                    returnedStraightFlush[3] = allCards[i][3];
                    returnedStraightFlush[4] = allCards[i][2];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][5] == 1 && playerCards[i][4] == 1 && playerCards[i][3] == 1 &&
                    playerCards[i][2] == 1 && playerCards[i][1] ==1) { //7-6-5-4-3 straight flush
                    straightFlush = true; //a 7-6-5-4-3 straight flush is found
                    returnedStraightFlush[0] = allCards[i][5];
                    returnedStraightFlush[1] = allCards[i][4];
                    returnedStraightFlush[2] = allCards[i][3];
                    returnedStraightFlush[3] = allCards[i][2];
                    returnedStraightFlush[4] = allCards[i][1];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][4] == 1 && playerCards[i][3] == 1 && playerCards[i][2] == 1 &&
                    playerCards[i][1] == 1 && playerCards[i][0] ==1) { //6-5-4-3-2 straight flush
                    straightFlush = true;
                    returnedStraightFlush[0] = allCards[i][4];
                    returnedStraightFlush[1] = allCards[i][3];
                    returnedStraightFlush[2] = allCards[i][2];
                    returnedStraightFlush[3] = allCards[i][1];
                    returnedStraightFlush[4] = allCards[i][0];
                    break; //makes sure to terminate search as a straight flush is found
            }

            else if (playerCards[i][3] == 1 && playerCards[i][2] == 1 && playerCards[i][1] == 1 &&
                    playerCards[i][0] == 1 && playerCards[i][12] ==1) { //5-4-3-2-A straight flush
                    straightFlush = true;
                    returnedStraightFlush[0] = allCards[i][4];
                    returnedStraightFlush[1] = allCards[i][3];
                    returnedStraightFlush[2] = allCards[i][2];
                    returnedStraightFlush[3] = allCards[i][1];
                    returnedStraightFlush[4] = allCards[i][12];
                    break; //makes sure to terminate search as a straight flush is found
            }


        }

        if (straightFlush) {
            //System.out.println("|" + returnedStraightFlush[0] + "| |" + returnedStraightFlush[1] + "| |" + returnedStraightFlush[2]
                    //+ "| |" + returnedStraightFlush[3] + "| |" + returnedStraightFlush[4] + "|"); //displays the straight flush
            return returnedStraightFlush;
        }
        else returnedStraightFlush = emptyHand;

        return returnedStraightFlush; //If a player has a straight flush, it will return true. Otherwise it returns false.
    }

    public static void main(String[] args) {

        String[] hand1 = {"2-hearts", "3-clubs", "4-spades", "5-spades", "6-spades", "7-spades", "8-spades"}; //straight flush 8

        String[] hand2 = {"2-hearts", "6-clubs", "10-spades", "J-spades", "Q-spades", "K-spades", "A-spades"}; //straight flush K
        //also a royal flush

        String[] hand3 = {"A-spades", "2-spades", "3-spades", "4-spades", "5-spades", "8-spades", "2-hearts"}; //straight flush 5

        String[] hand4 = {"2-hearts", "3-hearts", "4-hearts", "5-hearts", "6-clubs", "6-hearts", "A-hearts"}; //straight flush 6

        String[] hand5 = {"9-hearts", "10-hearts", "J-hearts", "Q-hearts", "6-clubs", "K-hearts", "A-spades"}; //straight flush K

        String[] hand6 = {"8-diamonds", "9-diamonds", "10-diamonds", "J-diamonds", "6-diamonds", "K-spades", "Q-diamonds"}; //straight flush Q

        String[] hand7 = {"10-diamonds", "7-clubs", "J-diamonds", "6-clubs", "5-clubs", "4-clubs", "3-clubs"}; //straight flush 10

        String[] hand8 = {"9-spades", "8-spades", "7-spades", "6-spades", "5-spades", "4-spades", "3-spades"}; //straight flush 9

        String[] hand9 = {"J-hearts", "10-hearts", "10-spades", "9-hearts", "7-hearts", "8-clubs", "8-hearts"}; //straight flush J

        String[] hand10 = {"7-diamonds", "7-spades", "3-diamonds", "4-diamonds", "5-diamonds", "6-diamonds", "2-diamonds"}; //straight flush 7

        String[] hand11 = {"2-hearts", "3-hearts", "4-hearts", "5-hearts", "6-clubs", "7-hearts", "8-hearts"}; //not

        String[] hand12 = {"9-clubs", "10-hearts", "J-hearts", "Q-spades", "6-clubs", "K-hearts", "A-spades"}; //not

        String[] hand13 = {"9-diamonds", "10-diamonds", "J-diamonds", "Q-diamonds", "6-diamonds", "K-diamonds", "A-diamonds"}; //yes

        String[] hand14 = {"10-diamonds", "10-clubs", "J-diamonds", "Q-clubs", "J-clubs", "K-clubs", "A-clubs"}; //not

        System.out.println(Arrays.toString(isStraightFlush(hand1)));
        System.out.println(Arrays.toString(isStraightFlush(hand2)));
        System.out.println(Arrays.toString(isStraightFlush(hand3)));
        System.out.println(Arrays.toString(isStraightFlush(hand4)));
        System.out.println(Arrays.toString(isStraightFlush(hand5)));
        System.out.println(Arrays.toString(isStraightFlush(hand6)));
        System.out.println(Arrays.toString(isStraightFlush(hand7)));
        System.out.println(Arrays.toString(isStraightFlush(hand8)));
        System.out.println(Arrays.toString(isStraightFlush(hand9)));
        System.out.println(Arrays.toString(isStraightFlush(hand10)));
        System.out.println(Arrays.toString(isStraightFlush(hand11)));
        System.out.println(Arrays.toString(isStraightFlush(hand12)));
        System.out.println(Arrays.toString(isStraightFlush(hand13)));
        System.out.println(Arrays.toString(isStraightFlush(hand14)));


    }




}
