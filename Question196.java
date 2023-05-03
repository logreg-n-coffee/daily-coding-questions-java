/*
 * Given the root of a binary tree, find the most frequent subtree sum. The
 * subtree sum of a node is the sum of all values under a node, including the
 * node itself.
 * 
 * For example, given the following tree:
 * 
 *  5
 * / \
 * 2 -5
 * Return 2 as it occurs twice: once as the left leaf, and once as the sum of 2
 * + 5 - 5.
 */

import java.util.HashMap;
import java.util.Map;

public class Question196 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // solve the problem with Time Complexity: O(N) and Space Complexity: O(N)

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-5);

        int result = findFrequentTreeSum(root);
        System.out.println("Most frequent subtree sum: " + result);
    }

    private static int findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> sumToCount = new HashMap<>();
        calculateSubtreeSums(root, sumToCount);

        int maxFrequency = 0;
        int mostFrequentSum = 0;
        for (Map.Entry<Integer, Integer> entry : sumToCount.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentSum = entry.getKey();
            }
        }

        return mostFrequentSum;
    }

    private static int calculateSubtreeSums(TreeNode node, HashMap<Integer, Integer> sumToCount) {
        if (node == null) {
            return 0;
        }

        int leftSubtreeSum = calculateSubtreeSums(node.left, sumToCount);
        int rightSubtreeSum = calculateSubtreeSums(node.right, sumToCount);
        int subtreeSum = node.val + leftSubtreeSum + rightSubtreeSum;

        sumToCount.put(subtreeSum, sumToCount.getOrDefault(sumToCount, 0) + 1);

        return subtreeSum;
    }
}
