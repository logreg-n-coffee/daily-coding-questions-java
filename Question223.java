
/**
 * Typically, an implementation of in-order traversal of a binary tree has O(h)
 * space complexity, where h is the height of the tree. Write a program to
 * compute the in-order traversal of a binary tree using O(1) space.
 */

import java.util.ArrayList;
import java.util.List;

public class Question223 {
    public static void main(String[] args) {

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // implement in-order traversal of a binary tree using O(1) space - using Morris
    // Traversal
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root; // This uses O(1) space

        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    prev.right = null;
                    result.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return result;
    }
}
