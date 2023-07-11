
/**
 * MegaCorp wants to give bonuses to its employees based on how many lines of
 * codes they have written. They would like to give the smallest positive amount
 * to each worker consistent with the constraint that if a developer has written
 * more lines of code than their neighbor, they should receive more money.
 * 
 * Given an array representing a line of codes of employees at MegaCorp,
 * determine how much each one should get paid.
 * 
 * For example, given [10, 40, 200, 1000, 60, 30], you should return [1, 2, 3,
 * 4, 2, 1].
 */

import java.util.Arrays;

public class Question265 {
    public static void main(String[] args) {
        int[] linesOfCode = { 10, 40, 200, 1000, 60, 30 };
        System.out.println(Arrays.toString(calculateBonuses(linesOfCode)));
    }

    // iterates over the array from left to right and then from right to left. On
    // the first pass, it ensures that each element is at least 1 greater than its
    // left neighbor, if the number of lines of code is higher. On the second pass,
    // it does the same for the right neighbor.
    public static int[] calculateBonuses(int[] linesOfCode) {
        int n = linesOfCode.length;

        int[] bonuses = new int[n];

        // Initial bonuses for everyone
        for (int i = 0; i < n; i++) {
            bonuses[i] = 1;
        }

        // From left to right
        for (int i = 1; i < n; i++) {
            if (linesOfCode[i] > linesOfCode[i - 1]) {
                bonuses[i] = bonuses[i - 1] + 1;
            }
        }

        // From right to left
        for (int i = n - 2; i >= 0; i--) {
            if (linesOfCode[i] > linesOfCode[i + 1]) {
                bonuses[i] = Math.max(bonuses[i], bonuses[i + 1] + 1);
            }
        }

        return bonuses;
    }
}
