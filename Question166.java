/*
Implement a 2D iterator class. It will be initialized with an array of arrays,
and should implement the following methods:

next(): returns the next element in the array of arrays. If there are no more elements, raise an exception.
has_next(): returns whether the iterator still has elements left.
For example, given the input [[1, 2], [3], [], [4, 5, 6]], calling next() repeatedly should output 1, 2, 3, 4, 5, 6.

Do not use flatten or otherwise clone the arrays. Some of the arrays can be empty.
 */

import java.util.List;
import java.util.NoSuchElementException;

public class Question166 {
    public static void main(String[] args) {
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2),
                List.of(3),
                List.of(),
                List.of(4, 5, 6)
        );

        TwoDIterator iterator = new TwoDIterator(listOfLists);

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
    public static class TwoDIterator {
        private final List<List<Integer>> listOfLists;
        private int outerIndex;
        private int innerIndex;

        public TwoDIterator(List<List<Integer>> listOfLists) {
            this.listOfLists = listOfLists;
            this.outerIndex = 0;
            this.innerIndex = 0;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the array");
            }

            int value = listOfLists.get(outerIndex).get(innerIndex);

            // move the iterator to the next element
            if (innerIndex < listOfLists.get(outerIndex).size() - 1) {
                innerIndex++;
            } else {
                // after we finished an inner list
                innerIndex = 0;
                outerIndex++;
                // if the next list is empty, find the next non-empty list
                while (outerIndex < listOfLists.size() && listOfLists.get(outerIndex).isEmpty()) {
                    outerIndex++;
                }
            }

            return value;
        }

        public boolean hasNext() {
            return outerIndex < listOfLists.size();
        }
    }
}
