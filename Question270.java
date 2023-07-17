
/**
 * A network consists of nodes labeled 0 to N. You are given a list of edges (a,
 * b, t), describing the time t it takes for a message to be sent from node a to
 * node b. Whenever a node receives a message, it immediately passes the message
 * on to a neighboring node, if possible.
 * 
 * Assuming all nodes are connected, determine how long it will take for every
 * node to receive a message that begins at node 0.
 * 
 * For example, given N = 5, and the following edges:
 * 
 * edges = [
 * (0, 1, 5),
 * (0, 2, 3),
 * (0, 5, 4),
 * (1, 3, 8),
 * (2, 3, 1),
 * (3, 5, 10),
 * (3, 4, 5)
 * ]
 * You should return 9, because propagating the message from 0 -> 2 -> 3 -> 4
 * will take that much time. For 0 -> 5, it takes 4, and 0 -> 1, it takes 5. 
 * However, by considering covering all nodes, we need 9 units of time.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Question270 {
    public static void main(String[] args) {
        int[][] edges = {
                { 0, 1, 5 },
                { 0, 2, 3 },
                { 0, 5, 4 },
                { 1, 3, 8 },
                { 2, 3, 1 },
                { 3, 5, 10 },
                { 3, 4, 5 }
        };
        int N = 5;
        System.out.println(messageTime(N, edges)); // Output: 9
    }

    static class Edge {
        int to;
        int time;

        public Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    static class Node implements Comparable<Node> {
        int node;
        int time;

        public Node(int node, int time) {
            this.node = node;
            this.time = time;
        }

        @Override
        public int compareTo(Node other) {
            return this.time - other.time;
        }
    }

    // solve the problem using Dijkstra's algorithm
    public static int messageTime(int N, int[][] edges) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) { // changed here
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];

            graph.get(from).add(new Edge(to, time));
            graph.get(to).add(new Edge(from, time)); // add this line if the graph is undirected\
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        minHeap.add(new Node(0, 0));

        // record the visited nodes
        boolean[] visited = new boolean[N + 1]; // changed here

        // time required for each node to receive the message
        int[] time = new int[N + 1]; // changed here

        // for each node, initialize the time to infinity
        Arrays.fill(time, Integer.MAX_VALUE);

        // time required for node 0 to receive the message is 0
        time[0] = 0;

        // core of dijkstra's algorithm
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            int currNode = node.node;

            // if the node is already visited, skip it
            if (visited[currNode]) {
                continue;
            }

            // mark the node as visited
            visited[currNode] = true;

            // update the time required for each node to receive the message
            for (Edge edge : graph.get(currNode)) {
                if (!visited[edge.to] && time[currNode] + edge.time < time[edge.to]) {
                    time[edge.to] = time[currNode] + edge.time;
                    minHeap.add(new Node(edge.to, time[edge.to]));
                }
            }
        }

        // if all nodes are visited, return the maximum time
        int maxTime = 0;
        for (int i = 1; i <= N; i++) { // changed here
            maxTime = Math.max(maxTime, time[i]);
        }
        return maxTime;
    }
}
