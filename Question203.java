/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand. Find the minimum element in O(log N) time. You may assume
 * the array does not contain duplicates.
 * 
 * For example, given [5, 7, 10, 3, 4], return 3.
 */

public class Question203 {
    public static void main(String[] args) {
        int[] nums = { 5, 7, 10, 3, 4 };
        System.out.println("The minimum element is: " + findMin(nums));
    }

    // solve the problem with a modified version of the binary search algorithm
    public static int findMin(int[] nums) {
        // Define two pointers, left and right, where left initially points to the first
        // element and right points to the last element of the array.
        int left = 0;
        int right = nums.length - 1;

        // Enter a loop that continues as long as left < right:
        while (left < right) {
            // Calculate the middle index mid as (left + right) / 2.
            int mid = left + (right - left) / 2;

            // If the element at mid is greater than the element at right, it means the
            // minimum element lies in the right half of the array - update left to mid + 1
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // Otherwise, the minimum element is in the left half or at mid,
                // so update right to mid
                right = mid;
            }
        }

        // Once the loop ends, the left pointer will point to the minimum element in the
        // array.
        return nums[left];
    }
}
