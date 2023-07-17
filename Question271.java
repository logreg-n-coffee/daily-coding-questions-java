/**
 * Given a sorted list of integers of length N, determine if an element x is in
 * the list without performing any multiplication, division, or bit-shift
 * operations.
 * 
 * Do this in O(log N) time.
 */

public class Question271 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int x = 5;

        boolean isPresent = binarySearch(arr, 0, arr.length - 1, x);
        System.out.println("Element " + x + (isPresent ? " is" : " is not") + " in the list.");
    }

    public static boolean binarySearch(int[] arr, int low, int high, int x) {
        if (high >= low) {
            int mid = low + ((high - low) / 2);

            // If the element is present at the middle itself
            if (x == arr[mid])
                return true;

            // If element is smaller than mid, then it can only be present in left subarray
            if (x < arr[mid])
                return binarySearch(arr, low, mid - 1, x);

            // Else the element can only be present in right subarray
            return binarySearch(arr, mid + 1, high, x);
        }

        // We reach here when element is not present in array
        return false;
    }
}
