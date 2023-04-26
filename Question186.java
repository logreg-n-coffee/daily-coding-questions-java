/*
Given an array of positive integers, divide the array into two subsets such that the difference
between the sum of the subsets is as small as possible.

For example, given [5, 10, 15, 20, 25], return the sets {10, 25} and {5, 15, 20}, which has a difference of 5,
which is the smallest possible difference.
 */

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Question186 {
    // strategy: find the sum of the entire array and then find the subset with a sum closest to half of the total sum.
    // This way, we minimize the difference between the two subsets.
    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20, 25};
        System.out.println(minSubsetDifference(arr));
    }

    // ignoring sorting, time complexity is O(n * halfSum); space complexity is O(n * halfSum)
    public static List<List<Integer>> minSubsetDifference(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        int halfSum = totalSum / 2;

        // dp[i][j] is true if it's possible to achieve a sum of j using the first i elements
        // in the input array, otherwise it's false.
        boolean[][] dp = new boolean[arr.length + 1][halfSum + 1];

        // a sum of 0 can always be achieved with an empty subset
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < arr.length + 1; i++) {
            for (int j = 1; j < halfSum + 1; j++) {
                if (arr[i - 1] <= j) {
                    // Check if the current element can be included in the subset
                    // For each element in the input array, we check if it can be included in the subset to
                    // achieve the current sum j. If it can, we set the value of dp[i][j] to true
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    // Carry over the value from the previous row
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Find the closest sum to halfSum that can be achieved
        int closestSum = -1;
        for (int j = halfSum; j >= 0; j--) {
            if (dp[arr.length][j]) {
                closestSum = j;
                break;
            }
        }

        // Construct the first subset using the DP array
        Set<Integer> subset1 = new HashSet<>();
        int remainingSum = closestSum;
        for (int i = arr.length; i > 0 && remainingSum >= 0; i--) {
            if (remainingSum >= arr[i - 1] && dp[i - 1][remainingSum - arr[i - 1]]) {
                subset1.add(arr[i - 1]);
                remainingSum -= arr[i - 1];
            }
        }

        // Construct the second subset by including elements not in the first subset
        Set<Integer> subset2 = new HashSet<>();
        for (int value : arr) {
            if (!subset1.contains(value)) {
                subset2.add(value);
            }
        }

        // Sort the result set
        List<Integer> sortedSubset1 = new ArrayList<>(subset1);
        List<Integer> sortedSubset2 = new ArrayList<>(subset2);

        sortedSubset1.sort(Integer::compareTo);
        sortedSubset2.sort(Integer::compareTo);

        // Return both subsets as a set of sets
        List<List<Integer>> result = new ArrayList<>();
        result.add(sortedSubset1);
        result.add(sortedSubset2);
        return result;
    }
}
