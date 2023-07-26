
/**
 * Given an undirected graph, determine if it contains a cycle.
 */

import java.util.*;

public class Question280 {
    // solve the problem with dfs
    public static void main(String[] args) {
        Graph g1 = new Graph(5);
        g1.addEdge(1, 0);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        if (g1.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        Graph g2 = new Graph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        if (g2.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");
    }

    static class Graph {
        private final int V; // No. of vertices
        private final List<List<Integer>> adj; // Adjacency List Representation

        Graph(int V) {
            this.V = V;
            this.adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new LinkedList<>());
            }
        }

        // Function to add an edge into the graph
        public void addEdge(int source, int dest) {
            adj.get(source).add(dest);
            adj.get(dest).add(source); // Since it's an undirected graph
        }

        // Recursive function that uses visited[] and parent to detect cycle
        private boolean isCyclicUtil(int v, boolean[] visited, int parent) {
            // Mark the current node as visited
            visited[v] = true;

            // Recur for all the vertices adjacent to this vertex
            for (Integer edge : adj.get(v)) {
                // If an adjacent is not visited, then recur for that adjacent
                if (!visited[edge]) {
                    if (isCyclicUtil(edge, visited, v))
                        return true;
                }

                // If an adjacent is visited and not parent of current vertex, then there is a
                // cycle.
                else if (edge != parent)
                    return true;
            }
            return false;
        }

        // Returns true if the graph contains a cycle, else false.
        public boolean isCyclic() {
            // Mark all the vertices as not visited and not part of recursion stack
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper function to detect cycle in different DFS trees
            for (int u = 0; u < V; u++)
                // Don't recur for u if it is already visited
                if (!visited[u])
                    if (isCyclicUtil(u, visited, -1))
                        return true;

            return false;
        }
    }
}
