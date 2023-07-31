
/**
 * Two nodes in a binary tree can be called cousins if they are on the same
 * level of the tree but have different parents. For example, in the following
 * diagram 4 and 6 are cousins.
 * 
 * 1
 * / \
 * 2 3
 * / \ \
 * 4 5 6
 * Given a binary tree and a particular node, find all cousins of that node.
 */

import java.util.*;

public class Question284 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        int target = 5;
        List<Integer> cousins = getCousins(root, target);
        System.out.println("The cousins of node " + target + " are: " + cousins);
    }

    public static List<Integer> getCousins(TreeNode root, int target) {
        if (root == null || root.val == target)
            return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isNextLevel = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    level.add(node.left.val);
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    level.add(node.right.val);
                    queue.offer(node.right);
                }

                if (node.left != null && node.left.val == target ||
                        node.right != null && node.right.val == target) {
                    isNextLevel = true;
                    level.remove((Integer) target);
                }
            }

            if (isNextLevel)
                return level;
        }

        return new ArrayList<>();
    }
}
