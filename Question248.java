/**
 * Find the maximum of two numbers without using any if-else statements,
 * branching, or direct comparisons.
 */

public class Question248 {
    public static void main(String[] args) {
        int a = 10;
        int b = 25;
        System.out.println(maxBitwise(a, b));
        System.out.println(maxAbs(a, b));
    }

    public static int maxBitwise(int a, int b) {
        // Consider the following expression: a - k * (a - b). If k = 0, this returns a,
        // whereas if k = 1, this returns b. So if we had some way to assign k to one of
        // these values based on which term was greater, we would have a solution.
        // Fortunately, we can do this by shifting (a - b) all the way to the right and
        // taking the most significant bit.

        // Any positive difference will be shifted down to zero. Meanwhile, a negative
        // difference will result in 1, because negative numbers are typically stored
        // using two's complement representation.
        int c = a - b;
        int k = (c >> 31) & 1;
        int max = a - k * c;
        return max;
    }

    public static int maxAbs(int a, int b) {
        return (a + b + Math.abs(a - b)) / 2;
    }
}
