/**
 * You are given an array of integers, where each element represents the maximum
 * number of steps that can be jumped going forward from that element. Write a
 * function to return the minimum number of jumps you must take in order to get
 * from the start to the end of the array.
 * 
 * For example, given [6, 2, 4, 0, 5, 1, 1, 4, 2, 9], you should return 2, as
 * the optimal solution involves jumping from 6 to 5, and then from 5 to 9.
 */

public class Question245 {
    public static void main(String[] args) {
        int[] arr = { 6, 2, 4, 0, 5, 1, 1, 4, 2, 9 };
        System.out.println("Min Jump(s) to reach to the end: " + minJumps(arr));
    }

    public static int minJumps(int[] arr) {
        // If array length is less than or equal to 1, we don't need to jump
        if (arr.length <= 1) {
            return 0;
        }

        // If first element is 0, it's impossible to make any move, hence return -1
        if (arr[0] == 0) {
            return -1;
        }

        // maxReach stores the index until which the current jump can reach
        int maxReach = arr[0];

        // step stores the number of steps we can still take
        int step = arr[0];

        // jump stores the amount of jumps necessary to reach that maximal reachable
        // index
        int jump = 1;

        // Start from the second element, as we have already processed the first element
        for (int i = 1; i < arr.length; i++) {
            // Check if we have reached the end of the array
            if (i == arr.length - 1) {
                System.out.println("Jumping to index: " + i);
                return jump;
            }

            // update maxReach
            maxReach = Math.max(maxReach, i + arr[i]);

            // we used a step to get to the current index, decrement step
            step--;

            // If no further steps left
            if (step == 0) {
                // we must have used a jump
                jump++;

                // Check if the current index/position is reachable or not. If not, return -1
                if (i >= maxReach) {
                    return -1;
                }

                // re-initialize the steps to the amount
                // of steps to reach maxReach from position i.
                step = maxReach - i;

                // Print the index where a jump is made
                System.out.println("Jumping from index: " + i);
            }

        }

        // after everything has been done. Then, we can't reach the end
        return -1;
    }
}
