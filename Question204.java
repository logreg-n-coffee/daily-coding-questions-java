/*
 * Given a complete binary tree, count the number of nodes in faster than O(n) time. 
 * Recall that a complete binary tree has every level filled except the last, 
 * and the nodes in the last level are filled starting from the left.
 * 
 */

public class Question204 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // solve the problem using O(log(n) * log(n)) = O(log(n)^2) time
    /*
     * 1
     * / \
     * 2 3
     * / \ / \
     * 4 5 6 7
     * 
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(countNodes(root));
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        // if the tree is a perfect binary tree
        if (leftDepth == rightDepth) {
            // If the tree is perfect (all levels completely filled), the number of nodes is
            // 2^h - 1, where h is the height of the tree.
            return (1 << leftDepth) - 1; // Math.pow(2, leftDepth) - 1 - this returns a double val
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    // find the height of the tree by traversing down to the leftmost node -
    // time complexity: O(log(n))
    private static int leftDepth(TreeNode node) {
        int d = 0;
        while (node != null) {
            d++;
            node = node.left;
        }
        return d;
    }

    private static int rightDepth(TreeNode node) {
        int d = 0;
        while (node != null) {
            d++;
            node = node.right;
        }
        return d;
    }
}
