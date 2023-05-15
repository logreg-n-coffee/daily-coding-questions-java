/*
 * A permutation can be specified by an array P, where P[i] represents the
 * location of the element at i in the permutation. For example, [2, 1, 0]
 * represents the permutation where elements at the index 0 and 2 are swapped.
 * 
 * Given an array and a permutation, apply the permutation to the array. For
 * example, given the array ["a", "b", "c"] and the permutation [2, 1, 0],
 * return ["c", "b", "a"].
 */

import java.util.Arrays;

class Question206 {
    public static void main(String[] args) {
        String[] array = { "a", "b", "c" };
        int[] permutation = { 2, 1, 0 };
        String[] permutedArray = applyPermutation(array, permutation);
        System.out.println(Arrays.toString(permutedArray));
    }

    public static String[] applyPermutation(String[] array, int[] permutation) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[permutation[i]];
        }
        return result;
    }
}
