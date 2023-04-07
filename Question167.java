import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a list of words, find all pairs of unique indices such that the concatenation of the two words is a palindrome.

For example, given the list ["code", "edoc", "da", "d"], return [(0, 1), (1, 0), (2, 3)].
 */
public class Question167 {
    public static void main(String[] args) {
        String[] words = {"code", "edoc", "da", "d"};
        List<int[]> result = findPalindromePairs(words);
        for (int[] pair : result) {
            System.out.println("(" + pair[0] + ", " + pair[1] + ")");
        }
    }

    // time complexity: O(n * m^2) - n is the number of words and m is the length of the longest word
    // space complexity: O(n) - hashmap
    public static List<int[]> findPalindromePairs(String[] words) {
        List<int[]> result = new ArrayList<>();
        // Create a HashMap to store the words and their indices.
        // This will allow for fast lookups when checking if a word's reverse exists.
        Map<String, Integer> wordIndexMap = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            wordIndexMap.put(words[i], i);
        }

        // Outer loop time: O(n); Inner loop iterate each word's length O(m) times O(m) checking palindrome
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                // For each word, iterate through all possible partitions (split word into left and right partitions).
                // The number of partitions is equal to the length of the word plus one.
                String left = words[i].substring(0, j);
                String right = words[i].substring(j);

                // Check if the left partition is a palindrome. If yes, reverse the right partition & look up in HashMap
                if (isPalindrome(left)) {
                    String reversedRight = new StringBuilder(right).reverse().toString();
                    Integer index = wordIndexMap.get(reversedRight);
                    if (index != null && index != i) {
                        result.add(new int[]{index, i});
                    }
                }

                if (isPalindrome(right) && right.length() != 0) {
                    String reversedLeft = new StringBuilder(left).reverse().toString();
                    Integer index = wordIndexMap.get(reversedLeft);
                    if (index != null && index != i) {
                        result.add(new int[]{i, index});
                    }
                }
            }
        }

        return result;
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
