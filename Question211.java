
/**
 * Given a string and a pattern, find the starting indices of all occurrences of
 * the pattern in the string. For example, given the string "abracadabra" and
 * the pattern "abr", you should return [0, 7].
 */

import java.util.ArrayList;
import java.util.List;

public class Question211 {
    // complexity analysis: Time O(n + m)
    // O(n) comes from the fact that we go through the entire text string of length
    // n during the pattern searching phase. Each character is visited only once in
    // the process.
    // O(m) is the time complexity for building the prefix table (longest proper
    // prefix which is also proper suffix, used to skip unnecessary comparisons)
    // for the pattern string of length m.
    // Space: O(m) for the prefix table
    public static void main(String[] args) {
        String str = "abracadabra";
        String pattern = "abr";
        List<Integer> indices = findPatternIndices(str, pattern);
        System.out.println(indices); // Outputs: [0, 7]
    }

    // pattern matching using KMP algorithm
    public static int[] computePrefixFunction(String pattern) {
        int m = pattern.length(); // length of pattern
        int[] longestPrefixSuffix = new int[m]; // array to store longest prefix suffix values
        int len = 0; // length of the previous longest prefix suffix
        int i = 1; // index for traversing the pattern

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                longestPrefixSuffix[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = longestPrefixSuffix[len - 1];
                } else {
                    longestPrefixSuffix[i] = len;
                    i++;
                }
            }
        }

        return longestPrefixSuffix;
    }

    public static List<Integer> findPatternIndices(String str, String pattern) {
        List<Integer> indices = new ArrayList<>();

        // compute the longest prefix suffix array
        int[] longestPrexifSuffix = computePrefixFunction(pattern);

        int i = 0; // index for str
        int j = 0; // index for pattern

        // loop through str
        while (i < str.length()) {
            // if the current char matches the pattern char
            if (str.charAt(i) == pattern.charAt(j)) {
                // increment both i and j
                i++;
                j++;
            }

            // if j equals pattern length, pattern is found
            if (j == pattern.length()) {
                // add to the list of indices
                indices.add(i - j);
                // update j to the value of the longest prefix suffix array at the previous
                // index
                j = longestPrexifSuffix[j - 1];
            } else if (i < str.length() && str.charAt(i) != pattern.charAt(j)) {
                // if i is less than str length
                // and the current char does not match the pattern char
                if (j != 0) {
                    // update j to the value of the longest prefix suffix array at the previous
                    // index
                    j = longestPrexifSuffix[j - 1];
                } else {
                    // if j is 0, increment i
                    i++;
                }
            }
        }

        return indices;
    }
}
