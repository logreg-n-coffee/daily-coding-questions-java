/*
You are given an N by M matrix of 0s and 1s. Starting from the top left corner,
how many ways are there to reach the bottom right corner?

You can only move right and down. 0 represents an empty space while 1 represents a wall you cannot walk through.

For example, given the following matrix:

[[0, 0, 1],
 [0, 0, 1],
 [1, 0, 0]]
Return two, as there are only two ways to get to the bottom right:

Right, down, down, right
Down, right, down, right
The top left corner and bottom right corner will always be 0.
 */

public class Question158 {
    static final int EMPTY = 0;
    static final int WALL = 1;

    public static void main(String[] args) {
        int[][] matrix = {
                {EMPTY, EMPTY, WALL},
                {EMPTY, EMPTY, WALL},
                {WALL, EMPTY, EMPTY}
        };
        int result = numWays(matrix);
        System.out.println(result);
    }

    // solve the question with dynamic programming - O(m * n) time and space
    public static int numWays(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] numWaysMatrix = new int[m][n];

        // fill the first row
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == WALL) {
                break;
            }
            numWaysMatrix[0][j] = 1;
        }

        // fill the first column
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == WALL) {
                break;
            }
            numWaysMatrix[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != WALL) {
                    int fromTop = numWaysMatrix[i - 1][j];
                    int fromLeft = numWaysMatrix[i][j - 1];
                    numWaysMatrix[i][j] = fromTop + fromLeft;
                }
            }
        }

        return (matrix[m - 1][n - 1] == WALL)
                ? 0  // if the bottom right is a wall then there is no solution
                : (numWaysMatrix[m - 1][n - 1]);
    }
}
