/*
Given a 2-D matrix representing an image, a location of a pixel in the screen and a color C,
replace the color of the given pixel and all adjacent same colored pixels with C.

For example, given the following matrix, and location pixel of (2, 2), and 'G' for green:

B B W
W W W
W W W
B B B

Becomes

B B G
G G G
G G G
B B B
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Question151 {
    public static void main(String[] args) {
        String[][] mat = {
                {"B", "B", "W"},
                {"W", "W", "W"},
                {"W", "W", "W"},
                {"B", "B", "B"},
        };

        int[] px = {2, 2};
        String clr = "G";
        fill(mat, px, clr, null);
        System.out.println(Arrays.deepToString(mat));
    }

    // use flood fill strategy (DFS) to solve filling problem - O(V + E) time and O(V) space
    public static void fill(String[][] matrix, int[] coordinate, String color, Set<int[]> visited) {
        // reassign a visited hashset if there is not a one
        if (visited == null) {
            visited = new HashSet<>();
        }

        // record the visited coordinate
        visited.add(coordinate);

        int r = coordinate[0];
        int c = coordinate[1];
        // same the prev color
        String priorColor = matrix[r][c];
        // replace prev color with current desired color
        matrix[r][c] = color;

        // prepare new coords to go through
        int[][] newCoordinates = {{r + 1, c}, {r, c + 1}, {r - 1, c}, {r, c - 1}};

        for (int[] newCoordinate : newCoordinates) {
            int newR = newCoordinate[0];
            int newC = newCoordinate[1];
            if (!visited.contains(newCoordinate) &&
                    isInMatrix(matrix, newCoordinate) &&
                    Objects.equals(matrix[newR][newC], priorColor)
            ) {
                visited.add(newCoordinate);
                fill(matrix, newCoordinate, color, visited);
            }
        }
    }

    public static boolean isInMatrix(String[][] matrix, int[] coordinate) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int r = coordinate[0];
        int c = coordinate[1];

        return 0 <= r & r < rows && 0 <= c && c < cols;
    }
}
