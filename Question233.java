/**
 * Implement the function fib(n), which returns the nth number in the Fibonacci
 * sequence, using only O(1) space.
 */

public class Question233 {
    public static void main(String[] args) {
        System.out.println(fib(10));
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }

        return curr;
    }
}
