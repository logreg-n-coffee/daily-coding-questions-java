/*
 * A Collatz sequence in mathematics can be defined as follows. Starting with any positive integer:
 * if n is even, the next number in the sequence is n / 2
 * if n is odd, the next number in the sequence is 3n + 1
 * It is conjectured that every such sequence eventually reaches the number 1. Test this conjecture.
 * 
 * Bonus: What input n <= 1000000 gives the longest sequence?
 */

public class Question210 {
    public static void main(String[] args) {
        final int limit = 1000000;
        int longestChain = 0;
        int number = 0;
        int[] cache = new int[limit + 1];

        for (int i = 1; i <= limit; i++) {
            int chainLength = generateCollatzSequence(i, cache);
            if (chainLength > longestChain) {
                longestChain = chainLength;
                number = i;
            }
        }

        System.out.printf(
                "The number %d produces the longest Collatz sequence with a length of %d.%n",
                number,
                longestChain);
    }

    /**
     * Generate the Collatz sequence for a given number.
     *
     * @param n
     *              The number to generate the sequence for.
     * @param cache
     *              A cache of previously generated sequence lengths.
     * @return The length of the Collatz sequence for n.
     */
    public static int generateCollatzSequence(long n, int[] cache) {
        if (n == 1) { // base case: n = 1
            return 1;
        }

        if (n < cache.length && cache[(int) n] != 0) { // if the value of n is already in the array, return it
            return cache[(int) n];
        }

        int length = 0; // initialize the length to 0

        if (n % 2 == 0) { // if n is even, divide it by 2 and add 1 to the length
            length = generateCollatzSequence(n / 2, cache) + 1;
        } else { // if n is odd, multiply it by 3 and add 1 to the length
            length = generateCollatzSequence(3 * n + 1, cache) + 1;
        }

        if (n < cache.length) { // if the value of n is less than the size of the array, add it to the array
            cache[(int) n] = length;
        }

        return length; // return the length
    }
}
