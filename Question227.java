/*
 * Boggle is a game played on a 4 x 4 grid of letters. 
 * The goal is to find as many words as possible that can be formed by a sequence of adjacent letters in the grid, 
 * using each cell at most once. 
 * 
 * Given a game board and a dictionary of valid words, implement a Boggle solver.
 */

import java.util.*;

public class Question227 {
    // class to represent a Trie node
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
    }

    // private property to represent the root of the Trie
    private TrieNode root;

    // constructor
    public Question227(List<String> dictionary) {
        root = new TrieNode();
        for (String word : dictionary) {
            insert(word);
        }
    }

    // methods to insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isWord = true;
    }

    // method to find all valid words in the Boggle board
    public List<String> findWords(char[][] board) {
        List<String> result = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, visited, "", i, j, result);
            }
        }
        return result;
    }

    // helper methods to find all valid words in the Boggle board
    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, List<String> result) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return;

        if (visited[x][y])
            return;

        str += board[x][y];
        if (!startsWith(str))
            return;

        if (isWord(str) && !result.contains(str)) {
            result.add(str);
        }

        visited[x][y] = true;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0)
                    dfs(board, visited, str, x + dx, y + dy, result);
            }
        }
        visited[x][y] = false;
    }

    private boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null)
                return false;
        }
        return true;
    }

    private boolean isWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            if (node == null)
                return false;
        }
        return node.isWord;
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("GEEKS", "FOR", "QUIZ", "GO");
        Question227 solver = new Question227(dictionary);

        char boggle[][] = {
                { 'G', 'I', 'Z' },
                { 'U', 'E', 'K' },
                { 'Q', 'S', 'E' }
        };

        List<String> words = solver.findWords(boggle);
        System.out.println(words);
        for (String word : words) {
            System.out.println(word);
        }
    }
}
