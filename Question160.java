/*
Given a tree where each edge has a weight, compute the length of the longest path in the tree.

For example, given the following tree:

   a
  /|\
 b c d
    / \
   e   f
  / \
 g   h
and the weights: a-b: 3, a-c: 5, a-d: 8, d-e: 2, d-f: 4, e-g: 1, e-h: 1,
the longest path would be c -> a -> d -> f, with a length of 17.

The path does not have to pass through the root,
and each node can have any amount of children.
 */

import java.util.HashMap;
import java.util.Map;

public class Question160 {
    public static void main(String[] args) {
        TreeNode a = new TreeNode('a');
        TreeNode b = new TreeNode('b');
        TreeNode c = new TreeNode('c');
        TreeNode d = new TreeNode('d');
        TreeNode e = new TreeNode('e');
        TreeNode f = new TreeNode('f');
        TreeNode g = new TreeNode('g');
        TreeNode h = new TreeNode('h');

        a.children.put(b, 3);
        a.children.put(c, 5);
        a.children.put(d, 8);
        d.children.put(e, 2);
        d.children.put(f, 4);
        e.children.put(g, 1);
        e.children.put(h, 1);

        findLongestPath(a);
        System.out.println("The longest path length is: " + maxPathLength);
    }

    static class TreeNode {
        char val;
        Map<TreeNode, Integer> children;  // <tree, weight> pair

        TreeNode(char val) {
            this.val = val;
            this.children = new HashMap<>();
        }
    }

    private static int maxPathLength = 0;

    // recursive Depth-First Search (DFS) algorithm - Time: O(N), Space: O(log(N)) ~ O(N), where N is the num of nodes
    public static int findLongestPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int longestPathFromNode = 0;
        // secondLongestPathFromNode: we consider all possible paths that pass through an internal node,
        // and not just the paths that go from leaf to leaf.
        int secondLongestPathFromNode = 0;

        for (Map.Entry<TreeNode, Integer> childEntry : node.children.entrySet()) {
            TreeNode child = childEntry.getKey();
            int weight = childEntry.getValue();
            int pathLength = findLongestPath(child) + weight;

            if (pathLength > longestPathFromNode) {
                secondLongestPathFromNode = longestPathFromNode;
                longestPathFromNode = pathLength;
            } else if (pathLength > secondLongestPathFromNode) {
                secondLongestPathFromNode = pathLength;
            }
        }

        maxPathLength = Math.max(maxPathLength, longestPathFromNode + secondLongestPathFromNode);
        return longestPathFromNode;
    }
}
