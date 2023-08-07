/**
 * On a mysterious island there are creatures known as Quxes which come in three
 * colors: red, green, and blue. One power of the Qux is that if two of them are
 * standing next to each other, they can transform into a single creature of the
 * third color.
 * 
 * Given N Quxes standing in a line, determine the smallest number of them
 * remaining after any possible sequence of such transformations.
 * 
 * For example, given the input ['R', 'G', 'B', 'G', 'B'], it is possible to end
 * up with a single Qux through the following steps:
 * 
 * Arrangement | Change
 * ----------------------------------------
 * ['R', 'G', 'B', 'G', 'B'] | (R, G) -> B
 * ['B', 'B', 'G', 'B'] | (B, G) -> R
 * ['B', 'R', 'B'] | (R, B) -> G
 * ['B', 'G'] | (B, G) -> R
 * ['R'] |
 */

public class Question290 {
    // solve the problem with greedy algorithm - Time complexity: O(n), Space
    // complexity: O(1)

    // A function to count the number of Quxes of each color.
    // It returns an array where count[0] is the number of 'R', count[1] is the
    // number of 'G' and count[2] is the number of 'B'.
    private static int[] countQuxesColors(char[] quxes) {
        int[] count = new int[3];
        for (char qux : quxes) {
            if (qux == 'R')
                count[0]++;
            else if (qux == 'G')
                count[1]++;
            else
                count[2]++;
        }
        return count;
    }

    // A function to check if the counts of all colors are either even or odd.
    // If either all counts are even or all counts are odd, then it returns true.
    // Otherwise, it returns false.
    private static boolean allEvenOrOdd(int[] count) {
        boolean allEven = (count[0] % 2 == 0) && (count[1] % 2 == 0) && (count[2] % 2 == 0);
        boolean allOdd = (count[0] % 2 != 0) && (count[1] % 2 != 0) && (count[2] % 2 != 0);
        return allEven || allOdd;
    }

    // A function to check if there are only two types of Quxes present.
    // If there are only two types of Quxes (i.e., any of the count is zero), then
    // it returns true. Otherwise, it returns false.
    private static boolean onlyTwoColors(int[] count) {
        return (count[0] == 0 && count[1] == 0) ||
                (count[1] == 0 && count[2] == 0) ||
                (count[0] == 0 && count[2] == 0);
    }

    // A function to find out the minimum number of Quxes remaining after any
    // possible sequence of transformations.
    // The strategy is to count the number of Quxes of each color and do the
    // transformations as long as we can.
    public static int minQuxes(char[] quxes) {
        int[] count = countQuxesColors(quxes);

        // If there are only two types of Quxes present, then we cannot perform any
        // transformation.
        if (onlyTwoColors(count))
            return quxes.length;

        // If either all counts are even or all counts are odd, then we can perform
        // transformations and finally,
        // two Quxes of different colors will remain.
        if (allEvenOrOdd(count))
            return 2;

        // If the counts are mixed (some counts are even and some counts are odd), then
        // we can perform transformations and finally,
        // a single Qux will remain.
        return 1;
    }

    public static void main(String[] args) {
        char[] quxes = { 'R', 'G', 'B', 'G', 'B' };
        System.out.println(minQuxes(quxes)); // Output: 1
    }
}
