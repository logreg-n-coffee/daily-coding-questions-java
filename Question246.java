
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

    private static Map<Character, LinkedList<Character>> adjVertices = new HashMap<>();
    private static Map<Character, Boolean> visitedNodes = new HashMap<>();
    private static Map<Character, Integer> inDegree = new HashMap<>();
    private static Map<Character, Integer> outDegree = new HashMap<>();

    private static boolean canFormCircle(String[] words) {

        // Step 1: Create adjacency list and calculate in/out degrees
        for (String word : words) {
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length() - 1);

            // populate the adjacency list
            adjVertices.putIfAbsent(firstChar, new LinkedList<>());
            adjVertices.get(firstChar).add(lastChar);

            // calculate in/out degrees
            outDegree.put(firstChar, outDegree.getOrDefault(firstChar, 0) + 1);
            inDegree.put(lastChar, inDegree.getOrDefault(lastChar, 0) + 1);

            // mark nodes as not visited
            visitedNodes.put(firstChar, false);
            visitedNodes.put(lastChar, false);
        }

        // Step 2: Check if in degree and out degree of every vertex is same
        for (Character vertex : adjVertices.keySet()) {
            if (!inDegree.get(vertex).equals(outDegree.get(vertex))) {
                return false;
            }
        }

        // Step 3: Make sure all non-zero degree vertices form a single connected
        // component
        int nonZeroDegreeVertices = 0;
        for (Character vertex : adjVertices.keySet()) {
            if (outDegree.get(vertex) > 0) {
                nonZeroDegreeVertices++;
            }
        }

        // Do a DFS traversal of graph starting from the first character of the first
        // word
        dfsTraversal(words[0].charAt(0));

        // If DFS traversal visits all vertices, then return true
        int visitedVertices = 0;
        for (Character vertex : visitedNodes.keySet()) {
            if (visitedNodes.get(vertex) && outDegree.get(vertex) > 0) {
                visitedVertices++;
            }
        }

        return visitedVertices == nonZeroDegreeVertices;
    }

    private static void dfsTraversal(char currentVertex) {
        visitedNodes.put(currentVertex, true);
        for (char vertex : adjVertices.get(currentVertex)) {
            if (!visitedNodes.get(vertex)) {
                dfsTraversal(vertex);
            }
        }
    }
}
