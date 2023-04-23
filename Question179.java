/*
Given the sequence of keys visited by a postorder traversal of a binary search tree, reconstruct the tree.

For example, given the sequence 2, 4, 3, 8, 7, 5, you should construct the following tree:

    5
   / \
  3   7
 / \   \
2   4   8
 */

public class Question179 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] postorder = {2, 4, 3, 8, 7, 5};
        TreeNode root = buildTree(postorder, 0, postorder.length - 1);
        printInorder(root);
    }

    private static TreeNode buildTree(int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }

        // Take the last element in the sequence as the root of the tree.
        TreeNode node = new TreeNode(postorder[end]);

        // Find the index of the first element in the sequence that is greater than the root.
        // All elements before this index belong to the left subtree,
        // and all elements after this index belong to the right subtree.
        int i = start;
        for (; i < end; i++) {
            if (postorder[i] > node.val) {
                break;
            }
        }

        node.left = buildTree(postorder, start, i - 1);
        node.right = buildTree(postorder, i, end - 1);

        return node;
    }

    private static void printInorder(TreeNode node) {
        if (node != null) {
            printInorder(node.left);
            System.out.print(node.val + " ");
            printInorder(node.right);
        }
    }
}
