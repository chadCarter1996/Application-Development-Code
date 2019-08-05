

public class RepeatedStringChallenge {

    static long repeatedString(String s, long n) {

        //Everything needs to be a long

        long length = s.length();
        long totNumAsInString = 0L; //required
        long totalStrings = n/length;

        if (totalStrings > 0) {
            long totalLengthOfRemainingString = n % totalStrings;
            for (int i = 0; i < length; i++) {
                if (s.substring(i, i + 1).equalsIgnoreCase("a")) {
                    totNumAsInString++;
                }
            }
            totNumAsInString *= totalStrings;
            System.out.println(totNumAsInString);
            System.out.println(totalStrings);
            for (int j = 0; j < totalLengthOfRemainingString; j++) {
                if (s.substring(j, j + 1).equalsIgnoreCase("a")) {
                    totNumAsInString++;
                }
            }
        }

        else {
            for (int j = 0; j < n; j ++) {
                if (s.substring(j, j+1).equalsIgnoreCase("a")) {
                    totNumAsInString++;
                }
            }
        }

        return totNumAsInString;

    }

    public static void main(String[] args) {

        System.out.println(repeatedString("ababa", 3L));
    }

}
