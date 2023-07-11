import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A bridge in a connected (undirected) graph is an edge that, if removed,
 * causes the graph to become disconnected. Find all the bridges in a graph.
 */

public class Question262 {
    public static void main(String[] args) {
        System.out.println("Bridges in first graph: ");
        Graph g1 = new Graph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.bridge();
    }

    static public class Graph {
        private int time = 0;
        private static final int NIL = -1;
        private int V; // No. of vertices
        private List<LinkedList<Integer>> adj; // Adjacency List

        // Constructor
        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; ++i)
                adj.add(new LinkedList<>());
        }

        // Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj.get(v).add(w); // Add w to v's list.
            adj.get(w).add(v); // Add v to w's list
        }

        void bridgeUtil(int u, boolean visited[], int disc[], int low[], int parent[]) {
            // Mark the current node as visited
            visited[u] = true;

            // Initialize discovery time and low value
            disc[u] = low[u] = ++time;

            // Go through all vertices adjacent to this
            for (Integer v : adj.get(u)) {
                // If v is not visited yet, then make it a child of u in DFS tree and recurse
                // for it
                if (!visited[v]) {
                    parent[v] = u;
                    bridgeUtil(v, visited, disc, low, parent);

                    // Check if the subtree rooted with v has a connection to one of the ancestors
                    // of u
                    low[u] = Math.min(low[u], low[v]);

                    // If the lowest vertex reachable from subtree under v is below u in DFS tree,
                    // then u-v is a bridge
                    if (low[v] > disc[u])
                        System.out.println(u + " " + v);
                }
                // Update low value of u for parent function calls.
                else if (v != parent[u])
                    low[u] = Math.min(low[u], disc[v]);
            }
        }

        void bridge() {
            boolean visited[] = new boolean[V];
            int disc[] = new int[V];
            int low[] = new int[V];
            int parent[] = new int[V];

            // Initialize parent and visited arrays
            for (int i = 0; i < V; i++) {
                parent[i] = NIL;
                visited[i] = false;
            }

            // Call the recursive helper function to find Bridges in DFS tree rooted with
            // vertex 'i'
            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    bridgeUtil(i, visited, disc, low, parent);
        }
    }

}
