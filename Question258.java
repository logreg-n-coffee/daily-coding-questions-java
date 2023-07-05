
/**
 * In Ancient Greece, it was common to write text with the first line going left
 * to right, the second line going right to left, and continuing to go back and
 * forth. This style was called "boustrophedon".
 * 
 * Given a binary tree, write an algorithm to print the nodes in boustrophedon
 * order.
 * 
 * For example, given the following tree:
 * 
 * 1
 * / \
 * 2 3
 * / \ / \
 * 4 5 6 7
 * You should return [1, 3, 2, 4, 5, 6, 7].
 */

import java.util.*;

public class Question258 {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        printBoustrophedon(root);
    }

    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static void printBoustrophedon(Node root) {
        if (root == null) {
            return;
        }

        Deque<Integer> result = new ArrayDeque<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;

        // bfs with a twist
        while (!q.isEmpty()) {
            int size = q.size();
            Deque<Integer> temp = new ArrayDeque<>();

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                if (leftToRight) {
                    temp.addLast(curr.val);
                } else {
                    temp.addFirst(curr.val);
                }

                // left sub-tree
                if (curr.left != null) {
                    q.add(curr.left);
                }

                // right sub-tree
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }

            result.addAll(temp);
            leftToRight = !leftToRight;
        }

        System.out.println(result);
    }
}
