/*
Given n numbers, find the greatest common denominator between them.

For example, given the numbers [42, 56, 14], return 14.
 */

public class Question184 {
    // solve the question with time: O(log(min(a, b))), where a and b are the input numbers.
    public static void main(String[] args) {
        int[] nums = {42, 56, 14};
        System.out.println(findGCD(nums));
    }

    // find the gcd using Euclidean algorithm
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = b % a;
            a = temp;
        }
        return a;
    }

    public static int findGCD(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = gcd(result, nums[i]);
        }
        return result;
    }
}
