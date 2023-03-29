/*
Given a positive integer n, find the smallest number of squared integers which sum to n.

For example, given n = 13, return 2 since 13 = 3**2 + 2**2 = 9 + 4.

Given n = 27, return 3 since 27 = 3**2 + 3**2 + 3**2 = 9 + 9 + 9.
 */

import java.util.Arrays;

public class Question156 {
    public static void main(String[] args) {
        System.out.println(numSquares(13));
        System.out.println(numSquares(27));
    }

    // solve using dynamic programming - O(n^2) time and O(n) space
    public static int numSquares(int n) {
        if (n == 0) {
            return 0;
        }

        int[] cache = new int[n + 1];
        Arrays.fill(cache, Integer.MAX_VALUE);

        cache[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            int j = 1;
            while (j * j <= i) {
                cache[i] = Math.min(cache[i], cache[i - j * j] + 1);
                j++;
            }
        }

        return cache[n];
    }

    // solve using native method - O(2^n) time and O(1) extra space
    public static int numSquareBruteForce(int n) {
        if (n == 0) {
            return 0;
        }

        int minNumSquares = Integer.MAX_VALUE;

        // Iterate i from 1 to sqrt(n)
        int i = 1;
        while (i * i <= n) {
            // Recursively compute the minimum number of squares needed to sum to n - i*i
            // Pick the min of those, plus 1
            minNumSquares = Math.min(minNumSquares, numSquareBruteForce(n - i * i) + 1);
            i++;
        }

        return minNumSquares;
    }
}
