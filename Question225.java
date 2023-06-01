/**
 * There are N prisoners standing in a circle, waiting to be executed. The
 * executions are carried out starting with the kth person, and removing every
 * successive kth person going clockwise until there is no one left.
 * 
 * Given N and k, write an algorithm to determine where a prisoner should stand
 * in order to be the last survivor.
 * 
 * For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1,
 * 5, 3], so you should return 3.
 * 
 * Bonus: Find an O(log N) solution if k = 2.
 */

public class Question225 {
    public static void main(String[] args) {
        int n = 5;
        int k = 2;
        // orignal location starts with 1
        System.out.println("The safe position is " + (josephus(n, k)));
        System.out.println("The safe position is " + josephusLogN(n));
    }

    public static int josephus(int n, int k) {
        if (n == 1) {
            return 1;
        } else {
            return (josephus(n - 1, k) + k - 1) % n + 1;
        }
    }

    public static int josephusLogN(int n) {
        return 2 * (n - Integer.highestOneBit(n)) + 1;
    }
}
