import java.util.HashSet;
import java.util.Set;

/*
You are given an array of length n + 1 whose elements belong to
the set {1, 2, ..., n}. By the pigeonhole principle, there must be a duplicate.

Find it in linear time and space.
 */
public class Question164 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicateLinearSpace(nums));
        System.out.println(findDuplicateConstantSpace(nums));
    }

    // O(n) time and space
    public static int findDuplicateLinearSpace(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            } else {
                seen.add(num);
            }
        }

        return -1;  // default return
    }

    // O(n) time and O(1) space
    public static int findDuplicateConstantSpace(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];

        // find the intersection point of the two runners
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // find the entry point of the cycle
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }
}
