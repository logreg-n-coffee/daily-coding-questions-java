/**
 * Recall that a full binary tree is one in which each node is either a leaf
 * node, or has two children. Given a binary tree, convert it to a full one by
 * removing nodes with only one child.
 * 
 * For example, given the following tree:
 * 
 * ---------0---------
 * ------/-----\------
 * ----1---------2----
 * --/------------\---
 * 3-----------------4
 * --\--------------/ \
 * ----5-----------6---7
 * You should convert it to:
 * 
 * -----0-------
 * --/-----\----
 * 5---------4--
 * --------/---\
 * -------6-----7
 */

public class Question254 {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // intuition: to perform a post-order traversal, working our way from the bottom
    // to the top of the tree, and replacing a node with its child if only one child
    // exists.
    public static void main(String[] args) {
        // Build the example tree.
        TreeNode node3 = new TreeNode(3, null, new TreeNode(5));
        TreeNode node4 = new TreeNode(4, new TreeNode(6), new TreeNode(7));
        TreeNode node1 = new TreeNode(1, node3, null);
        TreeNode node2 = new TreeNode(2, null, node4);
        TreeNode root = new TreeNode(0, node1, node2);

        // Print the original tree (optional).
        System.out.println("Original tree:");
        printTree(root);

        // Convert the tree.
        root = convertToFullBinaryTree(root);

        // Print the converted tree (optional).
        System.out.println("Converted tree:");
        printTree(root);
    }

    private static void printTree(TreeNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node.val);
            printTree(node.right);
        }
    }

    public static TreeNode convertToFullBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = convertToFullBinaryTree(root.left);
        root.right = convertToFullBinaryTree(root.right);

        if (root.left == null && root.right == null) {
            return root;
        }

        if (root.left != null && root.right != null) {
            return root;
        }

        if (root.left == null) {
            root = root.right;
        } else {
            root = root.left;
        }

        return root;
    }
}
