/**
 * The "look and say" sequence is defined as follows: beginning with the term 1,
 * each subsequent term visually describes the digits appearing in the previous
 * term. The first few terms are as follows:
 * 
 * 1 (one 1 which produces 11)
 * 11 (two 1s which produce 21)
 * 21 (one 2 followed by one 1 which produce 1211)
 * 1211 (one 1, one 2, and two 1s which produce 111221)
 * 111221
 * As an example, the fourth term is 1211, since the third term consists of one
 * 2 and one 1.
 * 
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, one 1" or 1211.
 * 1211 is read off as "one 1, one 2, two 1s" or 111221.
 * 111221 is read off as "three 1s, two 2s, one 1" or 312211.
 * 
 * Given an integer N, print the Nth term of this sequence.
 */

public class Question275 {
    public static void main(String[] args) {
        int N = 5; // Set N here
        System.out.println(lookAndSay(N));
    }

    // Method to find the Nth term of the "look and say" sequence
    public static String lookAndSay(int n) {
        // If n is less than or equal to 0, return an empty string. This also serves to
        // prevent invalid arguments from crashing the program.
        if (n <= 0)
            return "";

        // Initialize the sequence with the first term
        String result = "1";

        // Generate the next n-1 terms
        while (n > 1) {

            // StringBuilder used to construct the next term of the sequence
            StringBuilder current = new StringBuilder();

            // Iterate over each digit in the current term
            for (int i = 0; i < result.length(); i++) {

                // count keeps track of the number of consecutive identical digits
                int count = 1;

                // Increment count while the next digit is the same as the current one
                while (i + 1 < result.length() && result.charAt(i) == result.charAt(i + 1)) {
                    i++;
                    count++;
                }

                // Append the count and the digit to the current term
                current.append(count).append(result.charAt(i));
            }

            // The newly generated term becomes the result for the next iteration
            result = current.toString();

            // Decrement n since we have generated one more term
            n--;
        }

        // Return the final term
        return result;
    }
}
