/**
 * The transitive closure of a graph is a measure of which vertices are
 * reachable from other vertices. It can be represented as a matrix M, where
 * M[i][j] == 1 if there is a path between vertices i and j, and otherwise 0.
 * 
 * For example, suppose we are given the following graph in adjacency list form:
 * 
 * graph = [
 * [0, 1, 3],
 * [1, 2],
 * [2],
 * [3]
 * ]
 * 
 * graph[0] = [0, 1, 3]: Vertex 0 has edges connecting it to vertices 0
 * (itself), 1, and 3.
 * graph[1] = [1, 2]: Vertex 1 has edges connecting it to vertices 1 (itself)
 * and 2.
 * graph[2] = [2]: Vertex 2 has an edge connecting it to vertex 2 (itself).
 * graph[3] = [3]: Vertex 3 has an edge connecting it to vertex 3 (itself).
 * 
 * graph = [
 * [1, 3], # Node 0 is connected to nodes 1 and 3
 * [2], # Node 1 is connected to node 2
 * [], # Node 2 is not connected to any node
 * [] # Node 3 is not connected to any node
 * ]
 * 
 * adjacencyMatrix = [
 * [0, 1, 0, 1], // Node 0 is connected to nodes 1 and 3
 * [0, 0, 1, 0], // Node 1 is connected to node 2
 * [0, 0, 0, 0], // Node 2 is not connected to any node
 * [0, 0, 0, 0] // Node 3 is not connected to any node
 * ]
 * 
 * The transitive closure of this graph would be:
 * 
 * [1, 1, 1, 1]
 * [0, 1, 1, 0]
 * [0, 0, 1, 0]
 * [0, 0, 0, 1]
 * 
 * 
 * Given a graph, find its transitive closure.
 */

public class Question255 {
    // The transitive closure of a graph can be computed using the Floyd Warshall
    // algorithm. This algorithm is a dynamic programming algorithm that calculates
    // the shortest paths between all pairs of vertices in a weighted graph. It can
    // also be used to compute the transitive closure of a graph by considering the
    // existence of a path between two vertices as a "weight", and finding whether a
    // path exists between every pair of vertices.
    public static void main(String[] args) {
        int[][] graph = {
                { 0, 1, 0, 1 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        int[][] transitiveClosure = getTransitiveClosure(graph);
        for (int i = 0; i < transitiveClosure.length; i++) {
            for (int j = 0; j < transitiveClosure[i].length; j++) {
                System.out.print(transitiveClosure[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] getTransitiveClosure(int[][] graph) {
        // Number of vertices in the graph
        int n = graph.length;

        // Initialize closure matrix with the adjacency matrix of the graph
        int[][] closure = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                closure[i][j] = graph[i][j];
            }
        }

        // Include the self loops
        for (int i = 0; i < n; i++) {
            closure[i][i] = 1;
        }

        // Compute transitive closure: If there is a path from i to k and
        // from k to j, then there is also a path from i to j.
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    closure[i][j] = closure[i][j] | (closure[i][k] & closure[k][j]);
                }
            }
        }

        // Return the computed transitive closure
        return closure;
    }
}
