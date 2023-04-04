/*
Given an array of integers, return a new array where each element in the new array is
the number of smaller elements to the right of that element in the original input array.

For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:

There is 1 smaller element to the right of 3
There is 1 smaller element to the right of 4
There are 2 smaller elements to the right of 9
There is 1 smaller element to the right of 6
There are no smaller elements to the right of 1
 */

import java.util.Arrays;
import java.util.TreeMap;

public class Question165 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 9, 6, 1};
        System.out.println(Arrays.toString(smallerElements(arr)));
        System.out.println(Arrays.toString(smallerElementsBST(arr)));
    }

    // solve with O(n ^ 2) time and O(n) space
    public static int[] smallerElements(int[] input) {
        int[] result = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            int count = 0;

            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[i]) {
                    count++;
                }
            }

            // save count to current result[i]
            result[i] = count;
        }

        return result;
    }

    // O(n * log n) time - O(n) space
    public static int[] smallerElementsBST(int[] input) {
        int[] result = new int[input.length];
        // implementation of a Balanced Binary Search Tree
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();  // num, count pair

        for (int i = input.length - 1; i >= 0; i--) {
            // the greatest key strictly less than the given key
            Integer smaller = treeMap.lowerKey(input[i]);  // O(log n) time

            // If a smaller key is found, use its value (count) as the result for the current element
            // Otherwise, set the result for the current element to 0
            if (smaller == null) {
                result[i] = 0;
            } else {
                result[i] = treeMap.get(smaller);  // O(log n) time
            }

            // Update the TreeMap with the current element and its count
            // If the current element is already in the TreeMap, increment its count by 1
            // Otherwise, add the current element to the TreeMap with a count of result[i] + 1
            treeMap.put(input[i], result[i] + 1);  // O(log n) time
        }

        return result;
    }
}
