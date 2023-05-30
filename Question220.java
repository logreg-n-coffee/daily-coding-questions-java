
/**
 * In front of you is a row of N coins, with values v1, v1, ..., vn.
 * 
 * You are asked to play the following game. You and an opponent take turns
 * choosing either the first or last coin from the row, removing it from the
 * row, and receiving the value of the coin.
 * 
 * Write a program that returns the maximum amount of money you can win with
 * certainty, if you move first, assuming your opponent plays optimally.
 * 
 * [10, 24*,  15,   ?]
 * [0,   24, 24*,  29]
 * [0,    0,   5,  9*]
 * [0,    0,   0,   9]
 */

import java.util.Arrays;

class Question220 {
    public static void main(String[] args) {
        int[] coins = { 10, 24, 5, 9 };
        System.out.println(maxProfitRecursive(coins, 0));
        System.out.println(maxProfitDP(coins));
    }

    public static int maxProfitRecursive(int[] coins, int value) {
        if (coins.length == 1) {
            return value + coins[0];
        } else if (coins.length == 2) {
            return value + Math.max(coins[0], coins[1]);
        } else {
            return value + Math.max(
                    coins[0] + Math.min(
                            maxProfitRecursive(Arrays.copyOfRange(coins, 2, coins.length), value),
                            maxProfitRecursive(Arrays.copyOfRange(coins, 1, coins.length - 1), value)),
                    coins[coins.length - 1] + Math.min(
                            maxProfitRecursive(Arrays.copyOfRange(coins, 1, coins.length - 1), value),
                            maxProfitRecursive(Arrays.copyOfRange(coins, 0, coins.length - 2), value)));
        }
    }

    public static int maxProfitDP(int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][n];

        // fill in the diagonal (single coins)
        for (int i = 0; i < n; i++) {
            dp[i][i] = coins[i];
        }

        // fill in the diagonal (two coins)
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = Math.max(dp[i][i], dp[i + 1][i + 1]);
        }

        // fill in the rest of the table
        for (int gap = 2; gap < n; gap++) { // the gap between i and j
            for (int i = 0; i < n - gap; i++) {
                int j = i + gap;

                // if we take the ith coin, then we can take either the (i+2)th or the (j-1)th
                // coin
                int left = dp[i][j - 2];
                int diagonal = dp[i + 1][j - 1];
                int bottom = dp[i + 2][j];
                dp[i][i + gap] = Math.max(
                        coins[i] + Math.min(diagonal, bottom),
                        coins[j] + Math.min(left, diagonal));
            }
        }

        return dp[0][n - 1];
    }
}