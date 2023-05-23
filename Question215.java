
/**
 * The horizontal distance of a binary tree node describes how far left or right
 * the node will be when the tree is printed out.
 * 
 * More rigorously, we can define it as follows:
 * 
 * The horizontal distance of the root is 0.
 * The horizontal distance of a left child is hd(parent) - 1.
 * The horizontal distance of a right child is hd(parent) + 1.
 * For example, for the following tree, hd(1) = -2, and hd(6) = 0.
 * 
 * 5
 * / \
 * 3 7
 * / \ / \
 * 1 4 6 9
 * / /
 * 0 8
 * The bottom view of a tree, then, consists of the lowest node at each
 * horizontal distance. If there are two nodes at the same depth and horizontal
 * distance, either is acceptable.
 * 
 * For this tree, for example, the bottom view could be [0, 1, 3, 6, 8, 9].
 * 
 * Given the root to a binary tree, return its bottom view.
 */

import java.util.*;

public class Question215 {
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // pair is used to hold node's value and its level
    static class Pair<T, U> {
        public final T node;
        public final U level;

        public Pair(T node, U level) {
            this.node = node;
            this.level = level;
        }
    }

    // solve the problem with dfs
    private static void traverse(Node root, Map<Integer, Pair<Integer, Integer>> hdTable, int distance, int level) {
        if (root == null) {
            return;
        }

        if (!hdTable.containsKey(distance) || hdTable.get(distance).level <= level) {
            hdTable.put(distance, new Pair<>(root.data, level));
        }

        traverse(root.left, hdTable, distance - 1, level + 1);
        traverse(root.right, hdTable, distance + 1, level + 1);
    }

    public static List<Integer> getBottomView(Node root) {
        Map<Integer, Pair<Integer, Integer>> hdTable = new TreeMap<>();
        traverse(root, hdTable, 0, 0);

        List<Integer> result = new ArrayList<>();
        for (Pair<Integer, Integer> value : hdTable.values()) {
            result.add(value.node);
        }

        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(7);
        root.left.left = new Node(1);
        root.left.right = new Node(4);
        root.left.left.left = new Node(0);
        root.right.left = new Node(6);
        root.right.right = new Node(9);
        root.right.right.left = new Node(8);

        List<Integer> bottomView = getBottomView(root);
        System.out.println(bottomView);
    }
}
