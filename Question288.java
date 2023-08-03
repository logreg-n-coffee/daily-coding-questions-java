
/**
 * The number 6174 is known as Kaprekar's contant, after the mathematician who
 * discovered an associated property: for all four-digit numbers with at least
 * two distinct digits, repeatedly applying a simple procedure eventually
 * results in this value. The procedure is as follows:
 * 
 * For a given input x, create two new numbers that consist of the digits in x
 * in ascending and descending order.
 * Subtract the smaller number from the larger number.
 * For example, this algorithm terminates in three steps when starting from
 * 1234:
 * 
 * 4321 - 1234 = 3087
 * 8730 - 0378 = 8352
 * 8532 - 2358 = 6174
 * Write a function that returns how many steps this will take for a given input
 * N.
 */

import java.util.Arrays;
import java.util.Collections;

public class Question288 {
    // Time Complexity: The reason the time complexity is O(1) is
    // because the maximum number of iterations the algorithm can go through is
    // limited. Regardless of the input, it never takes more than 7 steps for a
    // four-digit number to reach Kaprekar's constant (6174).
    // Space Complexity: The space complexity is also O(1) because the amount of
    // space the program uses does not grow with the size of the input. We're using
    // only a fixed amount of space to store the number at each step, the count of
    // steps, and temporary variables for computation. Even though we're converting
    // the number to a string to manipulate it, the size of this string is also a
    // constant (4 characters), so this doesn't affect the overall space complexity.
    public static void main(String[] args) {
        System.out.println(findStepsToKaprekarConstant(1234)); // should print 3
    }

    public static int findStepsToKaprekarConstant(int N) {
        int steps = 0;

        while (N != 6174) {
            // The String.format("%04d", N) statement in the code is used to convert the
            // integer N into a 4-digit string, preserving leading zeroes if present.
            String nStr = String.format("%04d", N);
            String asc = sortDigits(nStr, true);
            String desc = sortDigits(nStr, false);

            N = Math.abs(Integer.parseInt(desc) - Integer.parseInt(asc));
            steps++;
        }

        return steps;
    }

    private static String sortDigits(String str, boolean ascending) {
        String[] arr = str.split("");

        if (ascending) {
            Arrays.sort(arr);
        } else {
            Arrays.sort(arr, Collections.reverseOrder());
        }

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        return sb.toString();
    }
}
