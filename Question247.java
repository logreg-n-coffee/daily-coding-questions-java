/**
 * Given a binary tree, determine whether or not it is height-balanced. A
 * height-balanced binary tree can be defined as one in which the heights of the
 * two subtrees of any node never differ by more than one.
 */

public class Question247 {
    public static void main(String[] args) {
        // Create balanced binary tree
        TreeNode balancedTree = new TreeNode(1);
        balancedTree.left = new TreeNode(2);
        balancedTree.right = new TreeNode(3);
        balancedTree.left.left = new TreeNode(4);
        balancedTree.left.right = new TreeNode(5);
        balancedTree.right.left = new TreeNode(6);
        balancedTree.right.right = new TreeNode(7);

        // Create unbalanced binary tree
        TreeNode unbalancedTree = new TreeNode(1);
        unbalancedTree.left = new TreeNode(2);
        unbalancedTree.left.left = new TreeNode(3);
        unbalancedTree.left.left.left = new TreeNode(4);

        System.out.println("Is the first tree balanced? " + isBalanced(balancedTree));
        printTree(balancedTree, 0);
        System.out.println("Is the second tree balanced? " + isBalanced(unbalancedTree));
        printTree(unbalancedTree, 0);
    }

    public static void printTree(TreeNode node, int space) {
        // Base case
        if (node == null)
            return;

        // Increase distance between levels
        space += 10;

        // Process right child first
        printTree(node.right, space);

        // Print current node after space count
        System.out.print("\n");
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.print(node.val + "\n");

        // Process left child
        printTree(node.left, space);
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // solve the question with O(n) time complexity and O(n) space complexity
    // -1 is returned if the tree is not balanced. This value is propagated up the
    // recursive call stack as soon as it is determined that a subtree is not
    // balanced, avoiding any unnecessary computation for the rest of the nodes in
    // that subtree. This improves the time complexity to O(n).
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

}
