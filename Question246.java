
/**
 * Given a list of words, determine whether the words can be chained to form a
 * circle. A word X can be placed in front of another word Y in a circle if the
 * last character of X is same as the first character of Y.
 * 
 * For example, the words ['chair', 'height', 'racket', touch', 'tunic'] can
 * form the following circle: chair --> racket --> touch --> height --> tunic
 * --> chair.
 */

import java.util.*;

class Question246 {
    public static void main(String[] args) {
        String[] words = { "chair", "height", "racket", "touch", "tunic" };
        boolean canFormCircle = canFormCircle(words);
        System.out.println(canFormCircle); // expected output: true
    }

    public static boolean canFormCircle(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        // By keeping track of the in-degree of each node, we can identify the starting
        // nodes for the DFS.
        int[] inDegree = new int[26];

        // Build the graph of characters
        for (String word : words) {
            char first = word.charAt(0);
            char last = word.charAt(word.length() - 1);
            graph.putIfAbsent(first, new ArrayList<>());
            graph.putIfAbsent(last, new ArrayList<>());
            graph.get(first).add(last);
            inDegree[last - 'a']++;
        }

        // perform dfs to check if there is a path that visits all nodes exactly once
        Set<Character> visited = new HashSet<>();
        char start = words[0].charAt(0);
        return dfs(graph, inDegree, visited, start) == words.length;
    }

    private static int dfs(Map<Character, List<Character>> graph, int[] inDegree, Set<Character> visited, char node) {
        int count = 1;
        if (graph.containsKey(node)) {
            for (char neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    count += dfs(graph, inDegree, visited, neighbor);
                }
            }
        }
        return count;
    }
}