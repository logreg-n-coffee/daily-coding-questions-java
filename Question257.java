/**
 * Given an array of integers out of order, determine the bounds of the smallest
 * window (indices) that must be sorted in order for the entire array to be
 * sorted. For example, given [3, 7, 5, 6, 9], you should return (1, 3).
 */

public class Question257 {
    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 3, 7, 5, 6, 9 };
        Pair window = findUnsortedSubarray(nums);
        System.out.println("The smallest window to be sorted is: (" + window.first + ", " + window.second + ")");
    }

    public static Pair findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int start = 0, end = n - 1;

        // First, we'll traverse the array from the beginning and find the first element
        // which is not in sorted order.
        // find the first number out of sorting order from the beginning
        while (start < n - 1 && nums[start] <= nums[start + 1]) {
            start++;
        }

        // if the whole array is already sorted, return
        if (start == n - 1) {
            return new Pair(-1, -1);
        }

        // Then, we'll traverse the array from the end and find the last element which
        // is not in sorted order.
        // find the first number out of sorting order from the end
        while (end > 0 && nums[end] >= nums[end - 1]) {
            end--;
        }

        // The window from the first unsorted element to the last unsorted element is
        // the minimum window that we need to sort.
        // find the maximum and minimum of the subarray
        int max = nums[start], min = nums[start];
        for (int k = start + 1; k <= end; k++) {
            max = Math.max(max, nums[k]);
            min = Math.min(min, nums[k]);
        }

        // expand the window to its correct
        while (start > 0 && nums[start - 1] > min) {
            start--;
        }

        while (end < n - 1 && nums[end + 1] < max) {
            end++;
        }

        return new Pair(start, end);
    }

}