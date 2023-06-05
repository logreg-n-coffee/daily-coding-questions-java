
/**
 * Given a list of numbers, create an algorithm that arranges them in order to
 * form the largest possible integer. For example, given [10, 7, 76, 415], you
 * should return 77641510.
 */

import java.util.Arrays;
// import java.util.Comparator;

public class Question228 {
    public static void main(String[] args) {
        Integer[] nums = { 10, 7, 76, 415 };
        arrangeToFormLargest(nums);

        StringBuilder largestNum = new StringBuilder();
        for (int num : nums) {
            largestNum.append(num);
        }

        System.out.println(largestNum.toString());
    }

    public static void arrangeToFormLargest(Integer[] nums) {
        Arrays.sort(nums, (a, b) -> {
            String order1 = a.toString() + b.toString();
            String order2 = b.toString() + a.toString();
            return order2.compareTo(order1);
        });
        /*
         * Alternatively:
         * Arrays.sort(nums, new Comparator<Integer>() {
         * 
         * @Override
         * public int compare(Integer a, Integer b) {
         * String ab = a.toString() + b.toString();
         * String ba = b.toString() + a.toString();
         * return ba.compareTo(ab);
         * }
         * });
         */
    }
}
