
/**
 * Minimum spanning tree is the subset of edges of a tree that connect all its
 * vertices with the smallest possible total edge weight. Given an undirected
 * graph with weighted edges, compute the maximum weight spanning tree.
 */

import java.util.*;

public class Question234 {
    public static void main(String[] args) {
        String graphString = "/*\n" +
                "(0)--10--(1)--5--(2)\n" +
                " |       /  |  \n" +
                " 15    20  30\n" +
                " |   /      |\n" +
                "(3)---25--(4)\n" +
                "*/";
        System.out.println(graphString);

        int vertices = 5; // Number of vertices in graph
        int edges = 7; // Number of edges in graph
        Graph graph = new Graph(vertices, edges);

        // add edge 0-1
        graph.edge[0] = new Edge(0, 1, 10);

        // add edge 0-3
        graph.edge[1] = new Edge(0, 3, 15);

        // add edge 1-2
        graph.edge[2] = new Edge(1, 2, 5);

        // add edge 1-3
        graph.edge[3] = new Edge(1, 3, 20);

        // add edge 1-4
        graph.edge[4] = new Edge(1, 4, 30);

        // add edge 3-4
        graph.edge[5] = new Edge(3, 4, 25);

        // add edge 2-4
        graph.edge[6] = new Edge(2, 4, 35);

        graph.KruskalMST();
    }

    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        Edge[] edge;

        public Graph(int vertices, int edges) {
            this.vertices = vertices;
            edge = new Edge[edges];
        }

        class subset {
            int parent, rank;
        }

        int find(subset subsets[], int i) {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);

            return subsets[i].parent;
        }

        void Union(subset subsets[], int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;
            else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }

        void KruskalMST() {
            Edge result[] = new Edge[vertices];
            int e = 0;
            int i = 0;
            for (i = 0; i < vertices; ++i)
                result[i] = new Edge(0, 0, 0);

            Arrays.sort(edge, new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o2.weight - o1.weight;
                }
            });

            subset subsets[] = new subset[vertices];
            for (i = 0; i < vertices; ++i)
                subsets[i] = new subset();

            for (int v = 0; v < vertices; ++v) {
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            i = 0;

            while (e < vertices - 1) {
                Edge next_edge = new Edge(0, 0, 0);
                next_edge = edge[i++];

                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);

                if (x != y) {
                    result[e++] = next_edge;
                    Union(subsets, x, y);
                }
            }

            System.out.println("Edges of Maximum Weight Spanning tree");
            for (i = 0; i < e; ++i)
                System.out.println(result[i].src + " -- " + result[i].dest + " == " +
                        result[i].weight);
        }
    }

}
