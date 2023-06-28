/**
 * The ancient Egyptians used to express fractions as a sum of several terms
 * where each numerator is one. For example, 4 / 13 can be represented as 1 / 4
 * + 1 / 18 + 1 / 468.
 * 
 * Create an algorithm to turn an ordinary fraction a / b, where a < b, into an
 * Egyptian fraction.
 */

public class Question252 {
    // Intution: use the Greedy Algorithm here. For a given number of the form
    // 'nr/dr' where dr > nr, first find the greatest possible unit fraction, then
    // recur for the remaining part. For example, consider 6/14, we first introduce
    // 1/3 as a result. We then recur for (6/14 – 1/3) = 4/42.
    public static void main(String[] args) {
        int nr = 6, dr = 14;
        System.out.print("Egyptian Fraction Representation of " +
                nr + "/" + dr + " is: \n ");
        printEgyptian(nr, dr);
    }

    // function to print the Egyptian Fraction of a / b
    public static void printEgyptian(int nr, int dr) {
        // if numerator or denominator is 0
        if (dr == 0 || nr == 0) {
            return;
        }

        // if denominator is divided by numerator, then simple division makes the
        // fraction in
        // 1 / n form
        if (dr % nr == 0) {
            System.out.println("1 / " + dr / nr);
            return;
        }

        // if numerator is divided by denominator, then the given number is not fraction
        if (nr % dr == 0) {
            System.out.println(nr / dr);
            return;
        }

        // If numerator is more than denominator
        if (nr > dr) {
            System.out.print(nr / dr + " + ");
            printEgyptian(nr % dr, dr);
            return;
        }

        // We reach here when dr > nr and dr%nr is non-zero.
        // Find ceiling of dr/nr and print it as first fraction
        int n = dr / nr + 1;
        System.out.print("1/" + n + " + ");

        // Recur for remaining part
        printEgyptian(nr * n - dr, dr * n);
    }
}
