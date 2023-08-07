
/**
 * A teacher must divide a class of students into two teams to play dodgeball.
 * Unfortunately, not all the kids get along, and several refuse to be put on
 * the same team as that of their enemies.
 * 
 * Given an adjacency list of students and their enemies, write an algorithm
 * that finds a satisfactory pair of teams, or returns False if none exists.
 * 
 * For example, given the following enemy graph you should return the teams {0,
 * 1, 4, 5} and {2, 3}.
 * 
 * students = {
 * 0: [3],
 * 1: [2],
 * 2: [1, 4],
 * 3: [0, 4, 5],
 * 4: [2, 3],
 * 5: [3]
 * }
 * 
 * On the other hand, given the input below, you should return False.
 * 
 * students = {
 * 0: [3],
 * 1: [2],
 * 2: [1, 3, 4],
 * 3: [0, 2, 4, 5],
 * 4: [2, 3],
 * 5: [3]
 * }
 */

import java.util.*;

public class Question292 {
    static class Graph {
        int V;
        List<List<Integer>> adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // solve the problem with graph coloring (dfs)
        boolean isBipartite(int src, int[] colorArr) {
            colorArr[src] = 1;

            for (int neighbor : adjList.get(src)) {
                if (colorArr[neighbor] == -1) {
                    colorArr[neighbor] = 1 - colorArr[src];
                    if (!isBipartite(neighbor, colorArr))
                        return false;
                } else if (colorArr[neighbor] == colorArr[src])
                    return false;
            }
            return true;
        }

        List<Set<Integer>> divideTeams() {
            int[] colorArr = new int[V];
            Arrays.fill(colorArr, -1);

            for (int i = 0; i < V; i++) {
                if (colorArr[i] == -1) {
                    if (!isBipartite(i, colorArr)) {
                        return null;
                    }
                }
            }

            Set<Integer> team1 = new HashSet<>();
            Set<Integer> team2 = new HashSet<>();

            for (int i = 0; i < V; i++) {
                if (colorArr[i] == 1) {
                    team1.add(i);
                } else {
                    team2.add(i);
                }
            }

            return Arrays.asList(team1, team2);
        }
    }

    public static void main(String[] args) {
        int V = 6;
        Graph g = new Graph(V);

        // Add edges to the graph
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 5);

        List<Set<Integer>> teams = g.divideTeams();
        if (teams != null) {
            System.out.println("Team 1: " + teams.get(0));
            System.out.println("Team 2: " + teams.get(1));
        } else {
            System.out.println("No possible division");
        }
    }
}
