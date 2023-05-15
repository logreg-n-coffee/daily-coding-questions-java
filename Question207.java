/*
 * Given an undirected graph G, check whether it is bipartite. 
 * Recall that a graph is bipartite if its vertices can be divided into 
 * two independent sets, U and V, such that no edge connects vertices of the same set.
 */

/*
 * Intuition: 
 * The idea is to start from any node, then assign it a color (let's say color 1). 
 * All of its neighbors will be assigned the other color (let's say color 2). 
 * If at any point we encounter a node that has already been colored and the color 
 * is the same as the one we are about to assign to it, then the graph is not bipartite. 
 * Otherwise, if we can color the entire graph this way, then the graph is bipartite.
 */

import java.util.*;

public class Question207 {
    // constants that represent the color of each node
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;

    // Solve the problem with BFS: Time Complexity: O(V + E) - Space Complexity:
    // O(V)
    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(3).add(2);
        System.out.println(isBipartite(graph)); // true

        // override the graph
        graph = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(4).add(3);
        graph.get(4).add(0);
        System.out.println(isBipartite(graph)); // false
    }

    public static boolean isBipartite(List<List<Integer>> graph) {
        int n = graph.size();
        int[] color = new int[n]; // Array to store the color assigned to each vertex
        Arrays.fill(color, UNCOLORED);

        // Iterate through all vertices
        for (int i = 0; i < n; i++) {
            // If the vertex is uncolored, try to color it and all its neighbors
            if (color[i] == UNCOLORED) {
                Queue<Integer> q = new LinkedList<>(); // Queue for BFS
                q.offer(i); // Start BFS from the current vertex
                color[i] = RED; // Color the current vertex red

                // Continue BFS until there are no more vertices to color
                while (!q.isEmpty()) {
                    int node = q.poll(); // Get the next vertex from the queue
                    int cNei = (color[node] == RED ? BLUE : RED);

                    // Iterate through all neighbors of the current vertex
                    for (int neighbor : graph.get(node)) {
                        // If the neighbor is uncolored, color it and add it to the queue
                        if (color[neighbor] == UNCOLORED) {
                            q.offer(neighbor);
                            color[neighbor] = cNei;
                        } else if (color[neighbor] != cNei) {
                            // If the neighbor is already colored and its color is the same as the current
                            // vertex, return false
                            return false;
                        }
                    }
                }
            }
        }
        // If we are able to color all the vertices without a contradiction, return true
        return true;
    }
}
