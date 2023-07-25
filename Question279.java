
/**
 * A classroom consists of N students, whose friendships can be represented in
 * an adjacency list. For example, the following descibes a situation where 0 is
 * friends with 1 and 2, 3 is friends with 6, and so on.
 * 
 * {0: [1, 2],
 * 1: [0, 5],
 * 2: [0],
 * 3: [6],
 * 4: [],
 * 5: [1],
 * 6: [3]}
 * Each student can be placed in a friend group, which can be defined as the
 * transitive closure of that student's friendship relations. In other words,
 * this is the smallest set such that no student in the group has any friends
 * outside this group. For the example above, the friend groups would be {0, 1,
 * 2, 5}, {3, 6}, {4}.
 * 
 * Given a friendship list such as the one above, determine the number of friend
 * groups in the class.
 */

import java.util.*;

public class Question279 {
    // solve the problem with dfs
    // The time complexity for this problem is O(N + E), where N is the number of
    // students (vertices) and E is the number of friendships (edges). This is
    // because we're using Depth-First Search (DFS) to visit each student exactly
    // once, and for each student, we're visiting all of their friends.

    // The space complexity is O(N) for the visited array and the recursion stack in
    // the worst case scenario (if the friendships form a tree-like structure).
    // Additionally, we are storing the groups of friends, which in total can
    // include all 'N' students, hence contributing to the space complexity of O(N).
    // Therefore, the overall space complexity is also O(N).
    private Map<Integer, List<Integer>> adjList;
    private boolean[] visited;

    public Question279(Map<Integer, List<Integer>> adjList) {
        this.adjList = adjList;
        this.visited = new boolean[adjList.size()];
    }

    public List<List<Integer>> getFriendGroups() {
        List<List<Integer>> friendGroups = new ArrayList<>();
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                List<Integer> group = new ArrayList<>();
                dfs(i, group);
                friendGroups.add(group);
            }
        }
        return friendGroups;
    }

    private void dfs(int node, List<Integer> group) {
        visited[node] = true;
        group.add(node);

        List<Integer> friends = adjList.get(node);

        for (Integer friend : friends) {
            if (!visited[friend]) {
                dfs(friend, group);
            }
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjList.put(0, Arrays.asList(1, 2));
        adjList.put(1, Arrays.asList(0, 5));
        adjList.put(2, Arrays.asList(0));
        adjList.put(3, Arrays.asList(6));
        adjList.put(4, new ArrayList<>());
        adjList.put(5, Arrays.asList(1));
        adjList.put(6, Arrays.asList(3));

        Question279 friendGroups = new Question279(adjList);

        List<List<Integer>> groups = friendGroups.getFriendGroups();

        for (List<Integer> group : groups) {
            System.out.println(group);
        }

        System.out.println("Solution: " + groups.size()); // Output: 3
    }
}
