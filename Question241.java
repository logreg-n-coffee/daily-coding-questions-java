/**
 * In academia, the h-index is a metric used to calculate the impact of a
 * researcher's papers. It is calculated as follows:
 * 
 * A researcher has index h if at least h of her N papers have h citations each.
 * If there are multiple h satisfying this formula, the maximum is chosen.
 * 
 * For example, suppose N = 5, and the respective citations of each paper are
 * [4, 3, 0, 1, 5]. Then the h-index would be 3, since the researcher has 3
 * papers with at least 3 citations.
 * 
 * Given a list of paper citations of a researcher, calculate their h-index.
 */

public class Question241 {
    public static void main(String[] args) {
        int[] citations = { 4, 3, 0, 1, 5 };
        System.out.println("The h-index is: " + hIndex(citations));
    }

    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] counts = new int[n + 1]; // Count of papers for each citation number

        // Count the number of papers for each citation number
        for (int c : citations) {
            // If the citation count is greater than or equal to n, increment the count at
            // index n
            if (c >= n) {
                counts[n]++;
            } else {
                // Otherwise, increment the count at the index corresponding to the citation
                // count
                counts[c]++;
            }
        }

        int total = 0; // Total number of papers with i or more citations

        // Traverse the counts array in reverse order
        for (int i = n; i >= 0; i--) {
            total += counts[i]; // Increment the total by the count at the current index

            // If the total is greater than or equal to the current index, return the index
            // as the h-index
            if (total >= i) {
                return i;
            }
        }

        // If no h-index is found (which should not happen in valid inputs), return 0
        return 0;
    }

}