/*
 * You are given an array of arrays of integers, where each array corresponds to a row in a triangle of numbers. 
 * For example, [[1], [2, 3], [1, 5, 1]] represents the triangle:
 * 
 *   1
 *  2 3
 * 1 5 1
 * We define a path in the triangle to start at the top and go down one row at a time to an adjacent value, 
 * eventually ending with an entry on the bottom row. 
 * For example, 1 -> 3 -> 5. The weight of the path is the sum of the entries.
 * 
 * Write a program that returns the weight of the maximum weight path.
 */

public class Question201 {
    public static void main(String[] args) {
        int[][] triangle = { { 1 }, { 2, 3 }, { 1, 5, 1 } };
        int maxWeight = maximumWeight(triangle);
        System.out.println("Maximum weight of path: " + maxWeight);
    }

    public static int maximumWeight(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];

        // initialize the bottom row of the dp array
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle[n - 1][i];
        }

        // fill in the rest of the dp array
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
            }
        }

        // the maximum weight path starts at the top
        return dp[0][0];
    }
}
