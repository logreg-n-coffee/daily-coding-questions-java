/**
 * You are given N identical eggs and access to a building with k floors. Your
 * task is to find the lowest floor that will cause an egg to break, if dropped
 * from that floor. Once an egg breaks, it cannot be dropped again. If an egg
 * breaks when dropped from the xth floor, you can assume it will also break
 * when dropped from any floor greater than x.
 * 
 * Write an algorithm that finds the minimum number of trial drops it will take,
 * in the worst case, to identify this floor.
 * 
 * For example, if N = 1 and k = 5, we will need to try dropping the egg at
 * every floor, beginning with the first, until we reach the fifth floor, so our
 * solution will be 5.
 */

/*
 * Egg dropping puzzle:
 * eggFloor[n][k] = 1 + min{max(eggFloor[n-1][x-1], eggFloor[n][k-x]): x in {1,
 * 2, ..., k}}
 */

public class Question230 {
    public static void main(String[] args) {
        int n = 2, k = 10;
        System.out.println(
                "Minimum number of trials in worst case with " + n + " eggs and " + k + " floors is " + eggDrop(n, k));
    }

    public static int eggDrop(int n, int k) {
        int[][] eggFloor = new int[n + 1][k + 1];
        int res, x;

        // we need one trial for one floor and 0 trials for 0 floors
        for (int i = 1; i < n + 1; i++) {
            eggFloor[i][1] = 1;
            eggFloor[i][0] = 0;
        }

        // we always need j trials for one egg and j floors
        for (int j = 1; j < k + 1; j++) {
            eggFloor[1][j] = j;
        }

        // fill rest of the entries in table using optimal substructure property
        for (int i = 2; i < n + 1; i++) {
            for (int j = 2; j < k + 1; j++) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                for (x = 1; x < j + 1; x++) {
                    res = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
                    if (res < eggFloor[i][j]) {
                        eggFloor[i][j] = res;
                    }
                }
            }
        }

        // eggFloor[n][k] holds the result
        return eggFloor[n][k];
    }
}
