/**
 * A fixed point in an array is an element whose value is equal to its index.
 * Given a **sorted** array of distinct elements, return a fixed point, if one
 * exists. Otherwise, return False.
 * 
 * For example, given [-6, 0, 2, 40], you should return 2. Given [1, 5, 7, 8],
 * you should return False.
 */

public class Question273 {
    public static void main(String[] args) {
        int[] array1 = { -6, 0, 2, 40 };
        int[] array2 = { 1, 5, 7, 8 };

        System.out.println(findFixedPoint(array1));
        System.out.println(findFixedPoint(array2));
    }

    // solve the problem with O(log N) time complexity and O(1) space complexity
    public static Integer findFixedPoint(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) / 2);

            if (arr[mid] == mid) {
                return mid;
            } else if (arr[mid] < mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
