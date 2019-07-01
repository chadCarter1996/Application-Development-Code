//Created by Chad Carter 1/7/19 for Poker Capstone
//Method is owned solely by Chad Carter

public class Combinations {

    //returns number of possible card hands
    public static long combinations(int numCards, int numCardsToDraw) {
        long result = 1;
        long furtherDivisor = 1;

        for (int i = numCards; i > numCards - numCardsToDraw; i--) {

            result *= i;

        }

        for (int j = 1; j <= numCardsToDraw; j++) {

            furtherDivisor *= j;

        }
        //System.out.println("Cards left: " + numCards);
        //System.out.println("Cards to be dealt: " + numCardsToDraw);


        return (result)/furtherDivisor;

    }

    public static void main(String[] args) {
        //System.out.println(combinations(52, 7));
        System.out.println("Number of possible card dealings = " + combinations(48, 5));
        System.out.println();
        System.out.println("Number of possible card dealings = " + combinations(45, 2));
        System.out.println();
        System.out.println("Number of possible card dealings = " + combinations(44, 1));
    }


}
