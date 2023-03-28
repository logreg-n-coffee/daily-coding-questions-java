/*
Given a list of elements, find the majority element,
which appears more than half the time (> floor(len(lst) / 2.0)).

You can assume that such element exists.

For example, given [1, 2, 1, 1, 3, 4, 0], return 1.
 */

import java.util.HashMap;

public class Question155 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 3, 4, 0};
        System.out.println(majority(nums));  // prints 1
    }

    // solve with O(n) time and space
    public static int majority(int[] elements) {
        HashMap<Integer, Integer> elementToCount = new HashMap<>();

        for (int element : elements) {
            if (!elementToCount.containsKey(element)) {
                elementToCount.put(element, 0);
            } else {
                elementToCount.put(element, elementToCount.get(element) + 1);
            }
        }

        // find the element with most of the counts
        int maxCount = 0;
        int majorityElement = -1;  // default return is -1

        for (int element : elementToCount.keySet()) {
            if (elementToCount.get(element) > maxCount) {
                maxCount = elementToCount.get(element);
                majorityElement = element;
            }
        }

        return majorityElement;
    }
}
