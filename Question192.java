/*
You are given an array of non-negative integers. Let's say you start at the beginning of the array and
are trying to advance to the end. You can advance at most, the number of steps that you're currently on.
Determine whether you can get to the end of the array.

For example, given the array [1, 3, 1, 2, 0, 1], we can go from indices 0 -> 1 -> 3 -> 5, so return true.

Given the array [1, 2, 1, 0, 0], we can't reach the end, so return false.
 */

public class Question192 {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 1, 2, 0, 1};
        int[] nums2 = {1, 2, 1, 0, 0};

        System.out.println(canReachEnd(nums1));  // Output: true
        System.out.println(canReachEnd(nums2));  // Output: false
    }

    public static boolean canReachEnd (int[] nums) {
        // Initialize a variable to keep track of the maximum reachable index
        int maxReachableIndex = 0;

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // If the current index is greater than the maximum reachable index,
            // it means that we cannot reach this index, so return false
            if (i > maxReachableIndex) {
                return false;
            }

            // Update the maximum reachable index with the max value between
            // the current maximum reachable index and the sum of the current index and the value at that index
            maxReachableIndex = Math.max(maxReachableIndex, i + nums[i]);

            // If the maximum reachable index is greater than or equal to the last index,
            // it means that we can reach the end of the array, so return true
            if (maxReachableIndex >= nums.length - 1) {
                return true;
            }
        }

        // it means that we can reach the end of the array, so return true
        return false;
    }
}
