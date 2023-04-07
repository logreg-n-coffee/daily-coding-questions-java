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

    }

    public static List<int[]> findPalindromePairs(String[] words) {
        List<int[]> result = new ArrayList<>();
        Map<String, Integer> wordIndexMap = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            wordIndexMap.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); i++) {
                String left = words[i].substring(0, j);
                String right = words[i].substring(j);

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
