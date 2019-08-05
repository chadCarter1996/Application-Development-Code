import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class JumpingOverClouds {

    static int jumpingOnClouds(int[] c) {

        int totalJumps = 0;

        for (int i = 0; i < c.length - 1; i++) {

            if (c[i] == 0 && c[i+1] == 0) {
                totalJumps++;
                i++;
            }
            else if (c[i] == 1) {
                totalJumps++;
            }
            else if (c[i] == 0 && c[i+1] == 1) {
                totalJumps++;
                i++;
            }
            else totalJumps++;

        }
        return totalJumps;
    }

    public static void main(String[] args) {

        int[] cloudArray = {0, 0, 0, 0, 1, 0};
        int[] cloudArray2 = {0, 0, 1, 0, 0, 1, 0};
        System.out.println(jumpingOnClouds(cloudArray));
        System.out.println(jumpingOnClouds(cloudArray2));

    }

}
