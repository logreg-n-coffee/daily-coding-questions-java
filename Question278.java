import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer N, construct all possible binary search trees with N nodes.
 */

public class Question278 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int N = 3;
        List<Node> totalTreesFrom1toN = tree.constructTrees(1, N);
        /* List of all different BSTs */
        System.out.println("Preorder traversal of different binary search trees are:");
        for (int i = 0; i < totalTreesFrom1toN.size(); i++) {
            tree.printPreorder(totalTreesFrom1toN.get(i));
            System.out.println();
        }
    }

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    static class BinaryTree {
        List<Node> constructTrees(int start, int end) {
            List<Node> list = new ArrayList<>();
            /*
             * if start > end then subtree will be empty so returning NULL
             * in the list
             */
            if (start > end) {
                list.add(null);
                return list;
            }

            /*
             * iterating through all values from start to end for constructing\
             * left and right subtree recursively
             */
            for (int i = start; i <= end; i++) {
                /* constructing left subtree */
                List<Node> leftSubtree = constructTrees(start, i - 1);

                /* constructing right subtree */
                List<Node> rightSubtree = constructTrees(i + 1, end);

                /*
                 * now looping through all left and right subtrees and connecting
                 * them to ith root below
                 */
                for (int j = 0; j < leftSubtree.size(); j++) {
                    Node left = leftSubtree.get(j);
                    for (int k = 0; k < rightSubtree.size(); k++) {
                        Node right = rightSubtree.get(k);
                        Node node = new Node(i); // making value i as root
                        node.left = left; // connect left subtree
                        node.right = right; // connect right subtree
                        list.add(node); // add this tree to list
                    }
                }
            }
            return list;
        }

        void printPreorder(Node node) {
            if (node == null)
                return;

            System.out.print(node.data + " ");
            printPreorder(node.left);
            printPreorder(node.right);
        }
    }
}
