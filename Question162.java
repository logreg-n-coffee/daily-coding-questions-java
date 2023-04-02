/*
Given a list of words, return the shortest unique prefix of each word. For example, given the list:

dog
cat
apple
apricot
fish

Return the list:

d
c
app
apr
f
 */

import java.util.*;

public class Question162 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("dog", "cat", "apple", "apricot", "fish");
        UniquePrefix up = new UniquePrefix();
        List<String> uniquePrefixes = up.uniquePrefixes(words);
        System.out.println(uniquePrefixes);
    }

    static class TrieNode {
        Map<Character, TrieNode> children;
        int wordCount;

        TrieNode() {
            children = new HashMap<>();
            wordCount = 0;
        }
    }

    public static class UniquePrefix {
        private final TrieNode root;

        public UniquePrefix() {
            root = new TrieNode();
        }

        // Time and Space: O(L) - L is the length of the word being inserted
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
                node.wordCount++;
            }
        }

        // Time and Space: O(L) - L is the length of the word being inserted
        public String uniquePrefix(String word) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();
            for (char c : word.toCharArray()) {
                prefix.append(c);
                node = node.children.get(c);
                // if the word appeared only once, it is unique
                if (node.wordCount == 1) {
                    return prefix.toString();
                }
            }
            // if substrings not unique, return itself
            return word;
        }

        // // Time and Space: O(L * N) - L is the max length of the word being inserted, N is the number of words
        public List<String> uniquePrefixes(List<String> words) {
            // build trie
            for (String word : words) {
                insert(word);
            }

            // get uniquePrefix for each word
            List<String> result = new ArrayList<>();
            for (String word : words) {
                result.add(uniquePrefix(word));
            }
            return result;
        }
    }
}

/*
Understanding shortest unique prefix:
A prefix is a sequence of characters that appears at the beginning of a word or string. In the context of strings,
a prefix can be any substring that starts from the first character of the string and extends up to a certain length.

For example, let's consider the word "apple". Here are some prefixes of "apple":

"a"
"ap"
"app"
"appl"
The entire word "apple" can also be considered a prefix of itself.

In the problem you presented, the goal is to find the shortest unique prefix of each word in a list.
The unique prefix is the shortest possible substring at the beginning of the word that helps distinguish it from the other words in the list.

To better understand the concept of prefixes, consider the following examples:

In the list of words ["cat", "dog", "car"], the shortest unique prefixes are:
"cat" -> "ca"
"dog" -> "d"
"car" -> "car"

In the list of words ["apple", "apricot", "banana"], the shortest unique prefixes are:
"apple" -> "app"
"apricot" -> "apr"
"banana" -> "b"

In both examples, the shortest unique prefixes help identify each word in the list without ambiguity.
 */
