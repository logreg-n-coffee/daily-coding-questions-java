import java.util.*;

/**
 * Given a set of characters C and an integer k, a De Bruijn sequence is a
 * cyclic sequence in which every possible k-length string of characters in C
 * occurs exactly once.
 * 
 * For example, suppose C = {0, 1} and k = 3. Then our sequence should contain
 * the substrings {'000', '001', '010', '011', '100', '101', '110', '111'}, and
 * one possible solution would be 00010111.
 * 
 * Create an algorithm that finds a De Bruijn sequence.
 */

public class Question264 {
    private List<Integer> sequence;
    private int[][] adjacencyMatrix;
    private int alphabetSize;

    public Question264(int alphabetSize, int sequenceLength) {
        this.alphabetSize = alphabetSize;
        this.adjacencyMatrix = new int[(int) Math.pow(alphabetSize, sequenceLength - 1)][alphabetSize];
        this.sequence = new ArrayList<>();
        populateAdjacencyMatrix();
    }

    private void populateAdjacencyMatrix() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < alphabetSize; j++) {
                adjacencyMatrix[i][j] = 1;
            }
        }
    }

    public String generateSequence() {
        dfs(0);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sequence.size() - 1; ++i) {
            builder.append((sequence.get(i) % alphabetSize));
        }
        return builder.toString();
    }

    private void dfs(int node) {
        for (int i = 0; i < alphabetSize; i++) {
            if (adjacencyMatrix[node][i] > 0) {
                adjacencyMatrix[node][i]--;
                dfs((node * alphabetSize + i) % adjacencyMatrix.length);
            }
        }
        sequence.add(node);
    }

    public static void main(String[] args) {
        Question264 deBruijn = new Question264(2, 3);
        String sequence = deBruijn.generateSequence();
        System.out.println("De Bruijn sequence: " + sequence);
    }
}
