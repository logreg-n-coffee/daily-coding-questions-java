/**
 * Given an array of numbers N and an integer k, your task is to split N into k
 * partitions such that the maximum sum of any partition is minimized. Return
 * this sum.
 * 
 * For example, given N = [5, 1, 2, 7, 3, 4] and k = 3, you should return 8,
 * since the optimal partition is [5, 1, 2], [7], [3, 4].
 */

public class Question243 {
    // solve the problem in O(n log m) time and O(1) space, where m is the sum of
    // the input array
    public static void main(String[] args) {

    }

    public static int minimizeMaxPartitionSum(int[] N, int k) {
        int left = 0, right = 0;
        for (int num : N) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canSplit(N, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // The canSplit() function goes through the array and tries to split it into as
    // few partitions as possible such that the sum of each partition is less than
    // or equal to the given maximum sum. If the number of partitions exceeds k, it
    // returns false. Otherwise, it returns true.
    public static boolean canSplit(int[] N, int k, int max) {
        int partitions = 1;
        int sum = 0;
        for (int num : N) {
            sum += num;
            if (sum > max) {
                sum = num;
                partitions++;
                if (partitions > k) {
                    return false;
                }
            }
        }
        return true;
    }
}
