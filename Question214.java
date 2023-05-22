/**
 * Given an integer n, return the length of the longest consecutive run of 1s in
 * its binary representation.
 * 
 * For example, given 156, you should return 3.
 */

public class Question214 {
    public static void main(String[] args) {
        int n = 156;
        System.out.println(longestRunOfOnes(n));
    }

    public static int longestRunOfOnes(int n) {
        // convert to binary and add a trailing 0
        String binary = Integer.toBinaryString(n) + "0";
        int longestRun = 0;
        int currentRun = 0;

        for (char bit : binary.toCharArray()) {
            if (bit == '1') {
                currentRun++;
            } else {
                longestRun = Math.max(longestRun, currentRun);
                currentRun = 0;
            }
        }

        return longestRun;
    }
}
