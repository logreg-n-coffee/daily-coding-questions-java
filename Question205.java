/*
 * Given an integer, find the next permutation of it in absolute order. 
 * For example, given 48975, the next permutation would be 49578.
 * 
 * Solution:
 * Traverse from right and find the first item that is not following the ascending order.
 * Swap this item with the next smallest item that is on the right of it.
 * After swapping, sort the rest of the array in ascending order.
 * 
 */

public class Question205 {
    public static void main(String[] args) {
        int num = 48975;
        System.out.println("Next permutation is " + nextPermutation(num));
    }

    public static int nextPermutation(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        // find the first item that is not in ascending order from right
        int i = digits.length - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        // If we never find such a digit, it means that the number is in descending
        // order and there's no larger permutation possible. In this case, i would
        // become -1, and we don't want to run the subsequent swapping and reversing
        // code.
        if (i >= 0) {
            int j = digits.length - 1;
            // find the next smallest item to swap
            while (digits[j] <= digits[i]) {
                j--;
            }
            // swap
            swap(digits, i, j);
        }

        // sort the rest in descending order
        reverse(digits, i + 1);
        return Integer.parseInt(new String(digits));
    }

    private static void swap(char[] digits, int i, int j) {
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

    private static void reverse(char[] digits, int start) {
        int i = start, j = digits.length - 1;
        while (i < j) {
            swap(digits, i, j);
            i++;
            j--;
        }
    }
}