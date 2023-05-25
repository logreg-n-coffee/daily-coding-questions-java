/**
 * We say a number is sparse if there are no adjacent ones in its binary
 * representation. For example, 21 (10101) is sparse, but 22 (10110) is not. For
 * a given input N, find the smallest sparse number greater than or equal to N.
 * 
 * Do this in faster than O(N log N) time.
 */

public class Question217 {
    // solve the problem in O(log n) time and O(1) space (32 bits Array)
    public static void main(String[] args) {
        int x = 22;
        int nextSparse = nextSparse(x);
        System.out.println("Next sparse number after " + x + " is: " + nextSparse);
    }

    // Traverse the array to find a sequence of two consecutive 1s not followed by a
    // 1. If such a sequence is found, set the next bit and reset all the bits
    // on the right.Continue this process until all bits have been processed.
    // By doing this, we guarantee that we never have two consecutive 1s.
    public static int nextSparse(int x) {
        // Convert x to binary and store that in an array.
        int[] bin = new int[32];
        int n = 0;
        while (x != 0) {
            bin[n] = x & 1;
            x >>= 1;
            n++;
        }

        int lastFinal = 0;
        for (int i = 1; i < n; i++) {
            // Find first sequence of consecutive 1s, end of
            // sequence is at i-1.
            if (bin[i] == 1 && bin[i - 1] == 1 && bin[i + 1] != 1) {
                bin[i + 1] = 1;

                // Make all bits before current bit as 0 to ensure we get the smallest number
                for (int j = i; j >= lastFinal; j--) {
                    bin[j] = 0;
                }

                // Store position of last encountered 1 whose
                // right side is set to 0
                lastFinal = i + 1;
            }
        }

        // Construct number from the binary array
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans += (bin[i] << i);
        }
        return ans;
    }
}
