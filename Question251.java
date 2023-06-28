
/**
 * Given an array of a million integers between zero and a billion, out of
 * order, how can you efficiently sort it? Assume that you cannot store an array
 * of a billion elements in memory.
 */

import java.util.*;

public class Question251 {
    // Intuition: radix sort, which sorts integer numbers digit by digit from least
    // significant digit to the most significant. Radix sort uses counting sort as a
    // subroutine to sort.
    public static void main(String[] args) {
        int arr[] = new int[1000000];
        Random rand = new Random();
        // generate or populate your array here
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000000000); // Generating random numbers between 0 and a billion
        }
        radixSort(arr);
        print(arr);
    }

    // A utility function to get maximum value in arr[]
    static int getMax(int[] arr) {
        int n = arr.length;
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // store count of occurences in count[]
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // change count[i] so that count[i] now contains actual position of this digit
        // in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // copy the output array to arr[], so that arr[] now contains sorted numbers
        // according to current digit
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // The main function to that sorts arr[] of size n using Radix Sort
    static void radixSort(int[] arr) {
        // find the maximum number to know number of digits
        int m = getMax(arr);

        // do counting sort for every digit. Note that instead of passing digit number,
        // exp is passed. exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    // A utility function to print an array
    static void print(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}
