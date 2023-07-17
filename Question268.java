/**
 * Given a 32-bit positive integer N, determine whether it is a power of four in
 * faster than O(log N) time.
 */

public class Question268 {
    /**
     * First, check if the number is positive and not zero.
     * Next, verify that there is only a single bit set in the binary representation
     * (which means the number is a power of two).
     * Then, subtract one from the number and divide by three. If the number is a
     * power of four, this will always produce an integer answer. This is because
     * the binary representation of (4^n - 1) is always evenly divisible by 11 in
     * binary form (which is 3 in decimal form).
     */
    public static void main(String[] args) {
        System.out.println(isPowerOfFour(16));
    }

    public static boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n - 1) % 3 == 0;
    }
}
