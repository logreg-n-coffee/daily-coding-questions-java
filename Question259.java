
/**
 * Ghost is a two-person word game where players alternate appending letters to
 * a word. The first person who spells out a word, or creates a prefix for which
 * there is no possible continuation, loses. Here is a sample game:
 * 
 * Player 1: g
 * Player 2: h
 * Player 1: o
 * Player 2: s
 * Player 1: t [loses]
 * 
 * Given a dictionary of words, determine the letters the first player should
 * start with, such that with optimal play they cannot lose.
 * 
 * For example, if the dictionary is ["cat", "calf", "dog", "bear"]. You can
 * start with "c", "d", or "b", but the only winning start letter would be b.
 */

import java.util.*;

class Trie {
    private Map<Character, Trie> trie = new HashMap<>();
    private boolean isWordEnd = false;

    // no-arg constructor
    public Trie() {
    }

    public Trie(List<String> words) {
        for (String word : words) {
            add(word);
        }
    }

    public void add(String word) {
        Trie node = this;
        for (char letter : word.toCharArray()) {
            node = node.trie.computeIfAbsent(letter, k -> new Trie());
        }
        node.isWordEnd = true;
    }

    public Trie get(String prefix) {
        Trie node = this;
        for (char letter : prefix.toCharArray()) {
            node = node.trie.get(letter);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    public boolean isWordEnd() {
        return this.isWordEnd;
    }

    public Set<Character> getKeys() {
        return this.trie.keySet();
    }
}

public class Question259 {
    public static boolean isWinning(Trie trie, String prefix) {
        Trie node = trie.get(prefix);
        if (node == null || node.isWordEnd()) {
            return false;
        } else {
            for (char nextLetter : node.getKeys()) {
                if (isWinning(trie, prefix + nextLetter)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static List<Character> optimalStartingLetters(List<String> words) {
        Trie trie = new Trie(words);
        List<Character> winners = new ArrayList<>();
        for (char letter : trie.getKeys()) {
            if (isWinning(trie, String.valueOf(letter))) {
                winners.add(letter);
            }
        }
        return winners;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("dog", "bear", "coat", "cat");
        System.out.println(optimalStartingLetters(words));
    }
}
