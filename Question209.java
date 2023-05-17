/*
 * Write a program that computes the length of the longest common subsequence of three given strings. 
 * For example, given "epidemiologist", "refrigeration", and "supercalifragilisticexpialodocious", 
 * it should return 5, since the longest common subsequence is "eieio".
 * 
 */

public class Question209 {
    // solve the question with (O^3) time and space complexity
    public static void main(String[] args) {
        String x = "epidemiologist";
        String y = "refrigeration";
        String z = "supercalifragilisticexpialodocious";

        System.out.println(lcsOf3(x, y, z));
    }

    public static int lcsOf3(String x, String y, String z) {
        int m = x.length();
        int n = y.length();
        int o = z.length();

        // initialize a 3D array to store the LCS values - bottom up approach
        int[][][] l = new int[m + 1][n + 1][o + 1];

        // For each character in the input strings, it checks if the characters match.
        // If they do, it increments the LCS value. If they don't, it carries forward
        // the maximum LCS value from the previous characters.
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                for (int k = 0; k < o + 1; k++) {
                    // if one of the string is empty, the LCS is 0
                    if (i == 0 || j == 0 || k == 0) {
                        l[i][j][k] = 0;
                    } else if (x.charAt(i - 1) == y.charAt(j - 1) && x.charAt(i - 1) == z.charAt(k - 1)) {
                        // if the last characters match, then the LCS is 1 + the LCS of the remaining
                        // strings
                        l[i][j][k] = l[i - 1][j - 1][k - 1] + 1;
                    } else {
                        // if the last characters don't match, then LCS is the maximum of the LCS of the
                        // remaining strings
                        l[i][j][k] = Math.max(Math.max(l[i - 1][j][k], l[i][j - 1][k]), l[i][j][k - 1]);
                    }
                }
            }
        }

        return l[m][n][o];
    }
}
