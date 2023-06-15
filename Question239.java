/**
 * One way to unlock an Android phone is through a pattern of swipes across a
 * 1-9 keypad.
 * 
 * For a pattern to be valid, it must satisfy the following:
 * 
 * All of its keys must be distinct.
 * It must not connect two keys by jumping over a third key, unless that key has
 * already been used.
 * For example, 4 - 2 - 1 - 7 is a valid pattern, whereas 2 - 1 - 7 is not.
 * 
 * Find the total number of valid unlock patterns of length N, where 1 <= N <=
 * 9.
 */

public class Question239 {
    // We define a 10x10 matrix 'jumps' to hold the intermediate numbers between two
    // numbers and an array 'visited' to hold the visit status of each number.
    private static int[][] jumps;
    private static boolean[] visited;

    public static void main(String[] args) {
        // Test 1
        int m = 2;
        int n = 3;
        System.out.println("Test 1 --- Expected: 376, Got: " + numberOfPatterns(m, n));

        // Test 2
        m = 1;
        n = 1;
        System.out.println("Test 2 --- Expected: 9, Got: " + numberOfPatterns(m, n));

        // Test 3
        m = 1;
        n = 9;
        System.out.println("Test 3 --- Expected: 389497, Got: " + numberOfPatterns(m, n));
    }

    public static int numberOfPatterns(int m, int n) {
        // Initialize the jumps matrix and array visited.
        jumps = new int[10][10];
        visited = new boolean[10];

        // Fill the jumps matrix.
        jumps[1][3] = jumps[3][1] = 2;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5;

        // Initialize the result.
        int count = 0;

        // for all lengths from m to n inclusive
        for (int length = m; length <= n; length++) {
            // For each symmetric group, we do a DFS for one number in the group
            // and then multiply the result by the number of elements in the group.
            // This makes use of the symmetry property and reduces computational complexity.
            count += dfs(1, length - 1) * 4; // 1, 3, 7, 9 are symmetric
            count += dfs(2, length - 1) * 4; // 2, 4, 6, 8 are symmetric
            count += dfs(5, length - 1); // 5 is a special case (symmetric with itself)
        }

        return count;
    }

    // dfs with backtracking
    private static int dfs(int curr, int remain) {
        // If there is no more numbers to visit, return 1.
        if (remain < 0) {
            return 0;
        }

        // If there is no more length to visit (reached a valid pattern), return 1.
        if (remain == 0) {
            return 1;
        }

        // Mark the current number as visited.
        visited[curr] = true;

        // Initialize the result.
        int result = 0;

        // For all possible next numbers from 1 to 9
        for (int next = 1; next <= 9; next++) {
            // We can only move the 'next' number if the number is not visited and
            // (two numbers are adjacent or jumps[curr][next] is visited)
            if (!visited[next] && (jumps[curr][next] == 0 || visited[jumps[curr][next]])) {
                // DFS for the next number.
                result += dfs(next, remain - 1);
            }
        }

        // Mark the current number as not visited so that it can be used in other paths
        visited[curr] = false;

        // Return the result.
        return result;
    }
}
