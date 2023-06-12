/**
 * Given an array of numbers of length N, find both the minimum and maximum
 * using less than 2 * (N - 2) comparisons.
 */

public class Question235 {
    public static void main(String[] args) {
        int[] numbers = { 3, 5, 1, 2, 6, 9, 8, 7, 4 };
        int[] minMax = getMinMax(numbers);
        System.out.println("Minimum: " + minMax[0]);
        System.out.println("Maximum: " + minMax[1]);
    }

    // process elements in pairs, so overall we reduce the number of comparisons.
    public static int[] getMinMax(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            throw new IllegalArgumentException("Array should have at least one element");
        }

        int min, max;

        // Initialize current index, min and max
        int i = 0;
        if (numbers.length % 2 == 0) {
            if (numbers[0] > numbers[1]) {
                max = numbers[0];
                min = numbers[1];
            } else {
                max = numbers[1];
                min = numbers[0];
            }
            i = 2; // set the start for loop
        } else {
            min = numbers[0];
            max = numbers[0];
            i = 1; // set the start for loop
        }

        while (i < numbers.length - 1) {
            if (numbers[i] > numbers[i + 1]) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }
                if (numbers[i + 1] < min) {
                    min = numbers[i + 1];
                }
            } else {
                if (numbers[i + 1] > max) {
                    max = numbers[i + 1];
                }
                if (numbers[i] < min) {
                    min = numbers[i];
                }
            }
            i += 2; // increment the index for the next pair
        }

        return new int[] { min, max };
    }
}
