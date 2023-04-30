/*
Given a circular array, compute its maximum subarray sum in O(n) time.
A subarray can be empty, and in this case the sum is 0.

For example, given [8, -1, 3, 4], return 15 as we choose the numbers 3, 4, and 8
where the 8 is obtained from wrapping around.

Given [-4, 5, 1, 0], return 6 as we choose the numbers 5 and 1.
 */

public class Question190 {
    public static void main(String[] args) {
        int[] arr1 = {8, -1, 3, 4};
        int[] arr2 = {-4, 5, 1, 0};

        System.out.println(maxCircularSum(arr1)); // Output: 15
        System.out.println(maxCircularSum(arr2)); // Output: 6
    }

    public static int maxCircularSum(int[] arr) {
        int totalSum = 0;

        int maxSum = Integer.MIN_VALUE;
        int currentMax = 0;

        int minSum = Integer.MAX_VALUE;
        int currentMin = 0;

        for (int num : arr) {
            // Calculate the sum of the entire array.
            totalSum += num;

            // Find the maximum subarray sum in the original array using Kadane's algorithm.
            currentMax = Math.max(currentMax + num, num);
            maxSum = Math.max(maxSum, currentMax);

            // Find the minimum subarray sum in the original array using a modified version of Kadane's algorithm.
            currentMin = Math.min(currentMin + num, num);
            minSum = Math.min(minSum, currentMin);
        }

        // edge case: maxSum is negative; considering a subarray with no elements - maxSum will be 0
        if (maxSum < 0) {
            return 0;
        } else {
            // Calculate the maximum circular subarray sum by subtracting the minimum subarray sum from the total sum.
            return Math.max(maxSum, totalSum - minSum);
        }
    }
}
