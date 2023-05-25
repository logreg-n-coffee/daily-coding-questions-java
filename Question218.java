
/**
 * Write an algorithm that computes the reversal of a directed graph. For
 * example, if a graph consists of A -> B -> C, it should become A <- B <- C.
 */

import java.util.*;

public class Question218 {
    public static void main(String[] args) {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        System.out.println("Original graph:");
        graph.printGraph();

        Graph reversedGraph = graph.reverse();

        System.out.println("Reversed graph:");
        reversedGraph.printGraph();
    }

    static class Graph {
        private int vertices;
        private ArrayList<ArrayList<Integer>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<ArrayList<Integer>>(vertices);

            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<Integer>());
            }
        }

        public void addEdge(int source, int destination) {
            adjacencyList.get(source).add(destination);
        }

        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                System.out.println("Edges from node " + i + " are: " + adjacencyList.get(i));
            }
        }

        // the basic idea to reverse a directed graph is to traverse the graph and then
        // for each node, swap its adjacency list with a reverse adjacency list
        public Graph reverse() {
            Graph reversedGraph = new Graph(vertices);

            for (int i = 0; i < vertices; i++) {
                ArrayList<Integer> neighbors = adjacencyList.get(i);
                for (int j = 0; j < neighbors.size(); j++) {
                    reversedGraph.addEdge(neighbors.get(j), i);
                }
            }
            return reversedGraph;
        }
    }
}
