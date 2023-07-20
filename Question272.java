/**
 * Write a function, throw_dice(N, faces, total), that determines how many ways
 * it is possible to throw N dice with some number of faces each to get a
 * specific total.
 * 
 * For example, throw_dice(3, 6, 7) should equal 15.
 */

public class Question272 {
    // memoization table
    private static long[][][] dp;

    // This code creates a memoization table dp[N][faces][total] that stores the
    // number of ways to throw N dice with faces faces each to get a total of total.
    // The throw_dice_recursive function computes the number of ways by trying all
    // possible face values and recursively calling itself with N - 1 dice and total
    // - face remaining. If a subproblem has already been solved, the function
    // returns the answer from the memoization table. The time complexity of this is
    // O(N * faces * total) and the space complexity is O(N * faces * total).
    public static void main(String[] args) {
        System.out.println(throwDice(3, 6, 7)); // should print 15
    }

    public static long throwDice(int N, int faces, int total) {
        dp = new long[N + 1][faces + 1][total + 1];

        // initialize the dp table with -1
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < faces + 1; j++) {
                for (int k = 0; k < total + 1; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        // call the recursive helper function
        return throwDiceHelper(N, faces, total);
    }

    private static long throwDiceHelper(int N, int faces, int total) {
        // if we have reached the total with exactly N dice, return 1 as it is valid
        if (N == 0 && total == 0) {
            return 1;
        }
        // If we have no dice left or the total can't be reached, this is not a valid
        // way
        if (N == 0 || total == 0) {
            return 0;
        }

        // if the value is already calculated, return it
        if (dp[N][faces][total] != -1) {
            return dp[N][faces][total];
        }

        // For each possible face value, try to subtract it from the total and
        // decrease the number of dice by one
        long ways = 0;
        for (int face = 1; face <= face + 1; face++) {
            if (total >= face) {
                ways += throwDiceHelper(N - 1, faces, total - face);
            }
        }

        // store the answer in the memoization table, return it
        dp[N][faces][total] = ways;
        return ways;
    }
}
