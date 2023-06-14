import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A tree is symmetric if its data and shape remain unchanged when it is
 * reflected about the root node. The following tree is an example:
 * 
 * 4
 * / | \
 * 3 5 3
 * / \
 * 9 9
 * Given a k-ary tree, determine whether it is symmetric.
 */

public class Question237 {
    static class Node {
        int val;
        List<Node> children;

        Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        Node node3_left = new Node(3);
        Node node5 = new Node(5);
        Node node3_right = new Node(3);

        Node node9_left = new Node(9);
        Node node9_right = new Node(9);

        root.children.add(node3_left);
        root.children.add(node5);
        root.children.add(node3_right);

        node3_left.children.add(node9_left);
        node3_right.children.add(node9_right);

        System.out.println(isSymmetric(root)); // should print: true
    }

    public static boolean isSymmetric(Node root) {
        return isMirror(root.children);
    }

    private static boolean isMirror(List<Node> children) {
        int size = children.size();

        for (int i = 0; i < size / 2; i++) {
            Node child1 = children.get(i);
            Node child2 = children.get(size - i - 1);

            if (!isMirror(child1, child2)) {
                return false;
            }
        }

        return true;
    }

    // method overload for isMirror
    private static boolean isMirror(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            // empty nodes are considered as mirror
            return true;
        }

        if (node1 == null || node2 == null) {
            // one of the nodes is null
            return false;
        }

        if (node1.val != node2.val) {
            // values are not equal
            return false;
        }

        if (node1.children.size() != node2.children.size()) {
            // number of children are not equal
            return false;
        }

        List<Node> node1Children = new ArrayList<>(node1.children);
        List<Node> node2Children = new ArrayList<>(node2.children);

        Collections.reverse(node2Children);

        return isMirror(node1Children) && isMirror(node2Children);
    }
}
