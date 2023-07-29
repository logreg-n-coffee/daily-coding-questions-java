
/**
 * Given an array of integers, determine whether it contains a Pythagorean
 * triplet. Recall that a Pythogorean triplet (a, b, c) is defined by the
 * equation a2+ b2= c2.
 */

import java.util.HashSet;
import java.util.Set;

public class Question282 {
    // Time complexity: O(n^2) Space complexity: O(n)

    public static boolean hasPythagoreanTriplet(int arr[]) {
        int n = arr.length;

        // Square all the elements and store them in a HashSet
        Set<Integer> squares = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] * arr[i];
            squares.add(arr[i]);
        }

        // Check for Pythagorean triplet
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (squares.contains(arr[i] + arr[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        int arr[] = { 3, 1, 4, 6, 5 };
        if (hasPythagoreanTriplet(arr)) {
            System.out.println("Array contains a Pythagorean triplet");
        } else {
            System.out.println("Array does not contain a Pythagorean triplet");
        }
    }
}
