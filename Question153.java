/*
Find an efficient algorithm to find the smallest distance (measured in number of words)
between any two given words in a string.

For example, given words "hello", and "world" and
a text content of "dog cat hello cat dog dog hello cat world",

return 1 because there's only one word "cat" in between the two words.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question153 {
    public static void main(String[] args) {
        System.out.println("min distance is: " + minDistance(
                "dog cat hello cat dog dog hello cat world",
                "hello",
                "world"
        ));
    }

    public static int minDistance(String text, String word0, String word1) {
        // split the words and save them as a list
        String[] words = text.split(" ");
        List<String> wordsList = Arrays.asList(words);
        wordsList = wordsList.stream().
                map(String::trim).collect(Collectors.toList());  // trim the list - remove leading and trailing spaces

        System.out.println(wordsList);

        // find the indices of all the word0 and word1
        List<Integer> word0Indices = new ArrayList<>();
        List<Integer> word1Indices = new ArrayList<>();

        for (int i = 0; i < wordsList.size(); i++) {
            if (wordsList.get(i).equals(word0)) {
                word0Indices.add(i);
            }
            if (wordsList.get(i).equals(word1)) {
                word1Indices.add(i);
            }
        }

        System.out.println(word0Indices);
        System.out.println(word1Indices);

        // if one of the words don't exist in the wordsList / text
        if (word0Indices.isEmpty() || word1Indices.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        // find min distance
        int i = 0, j = 0;  // two pointers
        int minDistance = Math.abs(word0Indices.get(i) - word1Indices.get(j));

        while (i < word0Indices.size() && j < word1Indices.size()) {
            int curDistance = Math.abs(word0Indices.get(i) - word1Indices.get(j));
            minDistance = Math.min(curDistance, minDistance);

            if (word0Indices.get(i) < word1Indices.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        return minDistance - 1;  // remove the last step to get to word1
    }
}
