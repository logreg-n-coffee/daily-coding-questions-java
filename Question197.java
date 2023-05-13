/*
 * Given an array and a number k that's smaller than the length of the array, rotate the array to the right k elements in-place.
 */

public class Question197 {
    /*
     * Reverse the entire array.
     * Reverse the first k elements.
     * Reverse the remaining n-k elements, where n is the length of the array.
     */
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int k = 3;

        rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            throw new IllegalArgumentException("invalid input");
        }

        int n = nums.length;
        k %= n;

        // reverse the entire array
        reverse(nums, 0, n - 1);
        // reverse the first k elements
        reverse(nums, 0, k - 1);
        // reverse the remaining n-k elements
        reverse(nums, k, n - 1);
    }

}
