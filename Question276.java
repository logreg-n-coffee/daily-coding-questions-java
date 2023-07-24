/**
 * Implement an efficient string matching algorithm.
 * 
 * That is, given a string of length N and a pattern of length k, write a
 * program that searches for the pattern in the string with less than O(N * k)
 * worst-case time complexity.
 * 
 * If the pattern is found, return the start index of its location. If not,
 * return False.
 */

public class Question276 {
    // KMP (Knuth-Morris-Pratt) algorithm. This algorithm has a worst-case time
    // complexity of O(N+K), which is achieved by avoiding unnecessary comparisons.
    public int KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int[] lps = new int[M];
        int j = 0; // Index for pat[]

        // LPS array holds the length of the longest proper prefix which is also suffix
        computeLPSArray(pat, M, lps);

        int i = 0; // Index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                return i - j; // Pattern found, return the start index
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }

        return -1; // Pattern not found
    }

    void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        Question276 kmp = new Question276();
        int result = kmp.KMPSearch(pat, txt);
        if (result == -1)
            System.out.println("Pattern not found");
        else
            System.out.println("Pattern found at index " + result);
    }
}
