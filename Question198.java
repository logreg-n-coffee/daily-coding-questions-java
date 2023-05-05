/*
 * Given a set of distinct positive integers, find the largest subset such that every pair of elements in the subset (i, j) satisfies either i % j = 0 or j % i = 0.
 * For example, given the set [3, 5, 10, 20, 21], you should return [5, 10, 20]. Given [1, 3, 6, 24], return [1, 3, 6, 24].
 */

import java.util.*;

public class Question198 {
    public static void main(String[] args) {
        int[] nums1 = { 3, 5, 10, 20, 21 };
        int[] nums2 = { 1, 3, 6, 24 };

        System.out.println(largestDivisibleSubset(nums1));
        System.out.println(largestDivisibleSubset(nums2));
    }

    // hint: The max subset for that num is either itself or max subset of
    // num[j] + nums[i], where 0 <= j < i.
    public static Deque<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;

        // Count array to store the size of the largest divisible subset ending with
        // nums[i]
        int[] count = new int[n];
        // Pre array to store the index of the previous element in the largest divisible
        // subset ending with nums[i]
        int[] pre = new int[n];

        // Sort the array
        Arrays.sort(nums);

        // find max subset
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;

            // Find the element in the sorted array that can be divided by the current
            // element - the loop iterates from i - 1 to 0 in reverse order
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    // If the size of the largest divisible subset ending with nums[j] + 1 is
                    // greater than count[i] then update count[i] and pre[i]
                    if (1 + count[j] > count[i]) {
                        count[i] = 1 + count[j];
                        pre[i] = j;
                    }
                }
            }

            // Keep track of the index of the element which has the largest divisible subset
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }

        // Add elements to the deque
        Deque<Integer> result = new LinkedList<>();
        while (index != -1) {
            result.addFirst(nums[index]);
            index = pre[index];
        }

        return result;
    }
}
