/**
 * You are given an array of length 24, where each element represents the number
 * of new subscribers during the corresponding hour. Implement a data structure
 * that efficiently supports the following:
 * 
 * update(hour: int, value: int): Increment the element at index hour by value.
 * query(start: int, end: int): Retrieve the number of subscribers that have
 * signed up between start and end (inclusive).
 * 
 * You can assume that all values get cleared at the end of the day, and that
 * you will not be asked for start and end values that wrap around midnight.
 */

public class Question242 {
    public static void main(String[] args) {
        // Create a new Subscribers object
        Subscribers subscribers = new Subscribers();

        // Update the number of subscribers in different hours
        subscribers.update(0, 10); // 10 subscribers signed up at hour 0
        subscribers.update(1, 20); // 20 subscribers signed up at hour 1
        subscribers.update(2, 30); // 30 subscribers signed up at hour 2
        subscribers.update(3, 40); // 40 subscribers signed up at hour 3

        // Query the total number of subscribers from hour 0 to hour 2
        System.out.println("Total subscribers from hour 0 to 2: " + subscribers.query(0, 2)); // Output: 60

        // Query the total number of subscribers from hour 1 to 3
        System.out.println("Total subscribers from hour 1 to 3: " + subscribers.query(1, 3)); // Output: 90

        // Update the number of subscribers at hour 2
        subscribers.update(2, 10); // 10 more subscribers signed up at hour 2

        // Query the total number of subscribers from hour 0 to 2 again
        System.out.println("Total subscribers from hour 0 to 2 after update: " + subscribers.query(0, 2)); // Output: 70
    }

    // fenwick tree (binary indexed tree)
    static class FenwickTree {
        private int[] tree;

        // initialize the tree
        public FenwickTree(int n) {
            this.tree = new int[n + 1];
        }

        // increment value at specific index in the tree - O(log(n))
        public void update(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index; // add least significant bit
            }
        }

        // calculate prefix sum up to given index - O(log(n))
        public int prefixSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index; // remove least significant bit
            }
            return sum;
        }

        // calculate sum between two indices
        public int sum(int start, int end) {
            return prefixSum(end) - prefixSum(start - 1);
        }
    }

    // solution
    static class Subscribers {
        private FenwickTree tree;

        public Subscribers() {
            this.tree = new FenwickTree(24);
        }

        public void update(int hour, int value) {
            // Convert to 1-based index for Fenwick Tree
            tree.update(hour + 1, value);
        }

        public int query(int start, int end) {
            // Convert to 1-based index for Fenwick Tree
            return tree.sum(start + 1, end + 1);
        }
    }
}
