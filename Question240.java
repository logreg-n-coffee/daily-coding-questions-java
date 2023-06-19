/**
 * There are N couples sitting in a row of length 2 * N. They are currently
 * ordered randomly, but would like to rearrange themselves so that each
 * couple's partners can sit side by side.
 * 
 * Each couple is represented by two numbers that are consecutive and even-odd.
 * 
 * What is the minimum number of swaps necessary for this to happen?
 */
public class Question240 {
    public static void main(String[] args) {
        int[] row = { 0, 3, 1, 2, 4, 5, 6, 7 };
        System.out.println(minSwaps(row));
    }

    public static int minSwaps(int[] row) {
        int n = row.length;
        int[] positions = new int[n];

        // fill the positions array
        for (int i = 0; i < n; i++) {
            positions[row[i]] = i;
        }

        // initialize a counter for the number of swaps
        int swaps = 0;

        // iterate over the row, two elements at a time
        for (int i = 0; i < n; i += 2) {
            int partner;

            // if the two elements are part of the same couple, skip to the next
            if (row[i] / 2 == row[i + 1] / 2) {
                continue;
            }

            // find the partner of the first person in the pair
            if (row[i] % 2 == 0) {
                partner = positions[row[i] / 2 * 2 + 1]; // Given an integer x, x / 2 * 2 will always give the largest
                                                         // even number less than or equal to x.
            } else {
                partner = positions[row[i] / 2 * 2];
            }

            // swap the second person in the pair with the partner of the first person
            int temp = row[i + 1];
            row[i + 1] = row[partner];
            row[partner] = temp;

            // increment the swap counter
            swaps++;
        }

        return swaps;
    }
}
