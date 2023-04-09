/*
Given an N by N matrix, rotate it by 90 degrees clockwise.

For example, given the following matrix:

[[1, 2, 3],
 [4, 5, 6],
 [7, 8, 9]]
you should return:

[[7, 4, 1],
 [8, 5, 2],
 [9, 6, 3]]
 */

import java.util.Arrays;

public class Question168 {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        int[][] rotatedMat = rotate90DegreesClockwise(mat);

        System.out.println(Arrays.deepToString(rotatedMat));

        for (int[] row : rotatedMat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

    }

    public static int[][] rotate90DegreesClockwise (int[][] matrix) {
        int n = matrix.length;

        // transpose the matrix (swap rows and columns)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reverse each row by column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }

        return matrix;
    }
}
