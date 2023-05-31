/**
 * Given a sorted array, find the smallest positive integer that is not the sum
 * of a subset of the array.
 * 
 * For example, for the input [1, 2, 3, 10], you should return 7.
 * 
 * Do this in O(N) time.
 */

public class Question224 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 10 };
        int res = smallestNonSumSubset(arr);
        System.out.println(res); // expected output: 7
    }

    public static int smallestNonSumSubset(int[] arr) {
        int result = 1;
        for (int i = 0; i < arr.length && arr[i] <= result; i++) {
            result += arr[i];
        }
        return result;
    }
}
