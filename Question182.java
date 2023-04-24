/*
A graph is minimally-connected if it is connected and there is no edge that can be removed
while still leaving the graph connected. For example, any binary tree is minimally-connected.

Given an undirected graph, check if the graph is minimally-connected.
You can choose to represent the graph as either an adjacency matrix or adjacency list.
 */

import java.util.ArrayList;
import java.util.List;

public class Question182 {
    // A minimally-connected graph with N vertices has exactly N-1 edges.
    // Count the number of edges in the graph, and check if it is equal to N-1.
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        System.out.println(g.isMinimallyConnected()); // Output: true
    }
    static class Graph {
        int vertices;
        List<List<Integer>> adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            this.adjList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
               adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int v, int w) {
            adjList.get(v).add(w);
            adjList.get(w).add(v);
        }

        private void dfsUtil(int v, boolean[] visited) {
            visited[v] = true;
            for (int n : adjList.get(v)) {
                if (!visited[n]) {
                    dfsUtil(n, visited);
                }
            }
        }

        // Check if the graph is connected: Perform a Depth First Search (DFS)
        public boolean isConnected() {
            boolean[] visited = new boolean[vertices];
            dfsUtil(0, visited);
            for (boolean b : visited) {
                if (!b) return false;
            }
            return true;
        }

        // Check if the graph is minimally-connected
        public boolean isMinimallyConnected() {
            if (!isConnected()) {
                return false;
            }

            int edgeCount = 0;
            for (List<Integer> edges : adjList) {
                edgeCount += edges.size();
            }

            return edgeCount / 2 == vertices - 1;
        }
    }


}
