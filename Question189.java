/*
Given an array of elements, return the length of the longest subarray where all its elements are distinct.

For example, given the array [5, 1, 3, 5, 2, 3, 4, 1],
return 5 as the longest subarray of distinct elements is [5, 2, 3, 4, 1].
 */

import java.util.Set;
import java.util.HashSet;

public class Question189 {
    public static void main(String[] args) {
        int[] arr = {5, 1, 3, 5, 2, 3, 4, 1};
        System.out.println(longestDistinctSubarray(arr));
    }

    // solve the problem with a sliding window and a hashset
    public static int longestDistinctSubarray(int[] arr) {
        int maxLength = 0;
        int left = 0;
        int right = 0;

        Set<Integer> distinctElements = new HashSet<>();

        while (right < arr.length) {
            if (!distinctElements.contains(arr[right])) {
                distinctElements.add(arr[right]);
                maxLength = Math.max(maxLength, distinctElements.size());
                right++;
            } else {
                distinctElements.remove(arr[left]);
                left++;
            }
        }

//        System.out.println(distinctElements);
        return maxLength;
    }
}
