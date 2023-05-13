/*
Let a matrix be an N by M matrix in which every row and every column is sorted.

Given i1, j1, i2, and j2, compute the number of elements of M smaller than M[i1, j1] and larger than M[i2, j2].

For example, given the following matrix:

[[1, 3, 7, 10, 15, 20],
 [2, 6, 9, 14, 22, 25],
 [3, 8, 10, 15, 25, 30],
 [10, 11, 12, 23, 30, 35],
 [20, 25, 30, 35, 40, 45]]
And i1 = 1, j1 = 1, i2 = 3, j2 = 3, return 14 as there are 14 numbers in the matrix smaller than 6 or greater than 23.

 */

public class Question195 {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 3, 7, 10, 15, 20 },
                { 2, 6, 9, 14, 22, 25 },
                { 3, 8, 10, 15, 25, 30 },
                { 10, 11, 12, 23, 30, 35 },
                { 20, 25, 30, 35, 40, 45 }
        };

        int i1 = 1, j1 = 1, i2 = 3, j2 = 3;
        int count = countElements(matrix, i1, j1, i2, j2);
        System.out.println(
                "Number of elements smaller than " +
                        matrix[i1][j1] + " or larger than " +
                        matrix[i2][j2] + " is: " + count);
    }

    public static int countElements(int[][] matrix, int i1, int j1, int i2, int j2) {
        int count = 0;
        int smallerThan = matrix[i1][j1];
        int largerThan = matrix[i2][j2];

        for (int[] row : matrix) {
            count += binarySearchSmaller(row, smallerThan);
            count += binarySearchLarger(row, largerThan);
        }

        return count;
    }

    public static int binarySearchSmaller(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static int binarySearchLarger(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return arr.length - low;
    }
}
