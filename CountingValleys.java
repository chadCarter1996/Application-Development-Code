import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CountingValleys {

    static int countingValleys(int n, String s) {

        String[] movement = new String[s.length()];
        int[] positionMatrix = new int[s.length() + 1];
        int initialPos = 0;
        int numValleys = 0;

        for (int i = 0; i < s.length(); i++) {
            movement[i] = s.substring(i, i+1);
            if (movement[i].equalsIgnoreCase("U")) {
                initialPos++;
                positionMatrix[i + 1] = initialPos;
            }
            else {
                initialPos--;
                positionMatrix[i + 1] = initialPos;
            }
        }
        for (int i = 0; i < positionMatrix.length - 1; i++) {
            if (positionMatrix[i+1] < 0 && positionMatrix[i] >= 0) {
                numValleys++;
            }
        }

        return numValleys;
    }

    public static void main(String[] args) {

        System.out.println(countingValleys(8, "UDDDUDUU"));

    }

}
