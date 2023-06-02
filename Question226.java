
/**
 * You come across a dictionary of sorted words in a language you've never seen
 * before. Write a program that returns the correct order of letters in this
 * language.
 * 
 * For example, given ['xww', 'wxyz', 'wxyw', 'ywx', 'ywz'], you should return
 * ['x', 'z', 'w', 'y'].
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Question226 {
    private static final int ALPHABET_SIZE = 26;

    public static void main(String[] args) {
        String[] words = { "xww", "wxyz", "wxyw", "ywx", "ywz" };
        char[] order = findOrder(words);

        for (char c : order) {
            System.out.print(c + " ");
        }
    }

    // find the order of characters from a sorted dictionary of words
    public static char[] findOrder(String[] words) {
        // Create adjacency list for graph representation
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            adjList.add(new ArrayList<>());
        }

        // Array to track visited characters
        boolean[] seen = new boolean[ALPHABET_SIZE];

        // Stack to store the topological order
        Stack<Character> stack = new Stack<>();

        // Set to hold the characters that actually appear in the words
        Set<Integer> lettersInWords = new HashSet<>();

        // Build the graph
        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];

            // Find the first differing characters and add an edge in the graph
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j) - 'a').add(word2.charAt(j) - 'a');
                    break;
                }

                // Add all characters to the set of characters that appear in the words
                for (char c : (word1 + word2).toCharArray()) {
                    lettersInWords.add(c - 'a');
                }
            }
        }

        // Perform a DFS for each character that appears in the words
        for (int i : lettersInWords) {
            if (!seen[i]) {
                dfs(i, seen, stack, adjList);
            }
        }

        // Build the resulting character order from the stack
        char[] order = new char[stack.size()];
        for (int i = 0; i < order.length; i++) {
            order[i] = stack.pop();
        }

        return order;
    }

    // use dfs to find the topological order - topological sort
    private static void dfs(int i, boolean[] seen, Stack<Character> stack, List<List<Integer>> adjList) {
        // Mark this node as visited
        seen[i] = true;

        // Visit all adjacent nodes
        for (int j : adjList.get(i)) {
            if (!seen[j]) {
                dfs(j, seen, stack, adjList);
            }
        }

        // Add this node to the stack
        stack.push((char) (i + 'a'));
    }
}
