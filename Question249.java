/**
 * Given an array of integers, find the maximum XOR of any two elements.
 */

public class Question249 {

    // Trie class definition
    public static class Trie {
        private Trie[] children; // each node of Trie has two children for '0' and '1'

        // Constructor initializes an empty Trie node
        public Trie() {
            children = new Trie[2];
        }

        // Insert function
        // The function inserts the binary representation of a number in the Trie
        public void insert(int num) {
            Trie curr = this; // start from the root
            for (int i = 31; i >= 0; i--) { // iterate over each bit of the number
                int bit = (num >> i) & 1; // extract the bit at ith position
                if (curr.children[bit] == null) {
                    curr.children[bit] = new Trie(); // if child for the bit does not exist, create a new Trie node
                }
                curr = curr.children[bit]; // go to the child node
            }
        }

        // The function finds the maximum XOR of a number with existing numbers in the
        // Trie
        public int findMaxXor(int num) {
            Trie curr = this; // start from the root
            int xor = 0; // to store the maximum xor
            for (int i = 31; i >= 0; i--) { // iterate over each bit of the number
                int bit = (num >> i) & 1; // extract the bit at ith position
                // if the opposite bit exists in the Trie, go that way to maximize the xor
                if (curr.children[bit ^ 1] != null) {
                    xor |= 1 << i; // include the bit in the xor
                    bit ^= 1; // flip the bit to go the opposite way
                }
                curr = curr.children[bit]; // go to the child node
            }
            return xor;
        }
    }

    // The function finds the maximum XOR from all pairs in the array
    public static int findMaxXor(int[] nums) {
        Trie trie = new Trie(); // initialize a Trie
        int maxXor = Integer.MIN_VALUE; // to store the maximum xor
        for (int num : nums) {
            trie.insert(num); // insert the number in the Trie
            int currXor = trie.findMaxXor(num); // find the maximum xor of the number with the numbers in the Trie
            maxXor = Math.max(maxXor, currXor); // update the maximum xor if necessary
        }
        return maxXor; // return the maximum xor
    }

    // Main function
    public static void main(String[] args) {
        int[] nums = { 3, 10, 5, 25, 2, 8 }; // the input array
        int max = findMaxXor(nums); // find the maximum xor
        System.out.println("Max XOR is " + max); // print the maximum xor
    }
}
