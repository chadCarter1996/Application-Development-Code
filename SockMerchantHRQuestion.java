import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SockMerchantHRQuestion {

    static int sockMerchant(int n, int[] ar) {
        int numPairs = 0;
        int runCount = 1;

        Arrays.sort(ar);

        if (n % 2 == 0) {
            for (int i = 0; i < n - 1; i++) {
                if (ar[i] == ar[i + 1]) {
                    runCount++;
                    if (runCount % 2 == 0) {
                        numPairs++;
                    }
                } else runCount = 1;
            }
        }
        else {
            for (int i = 0; i < n - 1; i++) {
                if (ar[i] == ar[i + 1]) {
                    runCount++;
                    if (runCount % 2 == 0) {
                        numPairs++;
                    }
                } else runCount = 1;
            }
        }
        return numPairs;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = 20;
        int[] test = {4, 5, 5, 5, 6, 6, 4, 1, 4, 4, 3, 6, 6, 3, 6, 1, 4, 5, 5, 5};
        System.out.println(sockMerchant(n, test));
        /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();*/
    }

}
