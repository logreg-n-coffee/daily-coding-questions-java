/*
Given a string s and a list of words words, where each word is the same length,
find all starting indices of substrings in s that is a concatenation of every word in words exactly once.

For example, given s = "dogcatcatcodecatdog" and words = ["cat", "dog"],
return [0, 13], since "dogcat" starts at index 0 and "catdog" starts at index 13.

Given s = "barfoobazbitbyte" and words = ["dog", "cat"],
return [] since there are no substrings composed of "dog" and "cat" in s.

The order of the indices does not matter.
 */

import java.util.*;

public class Question172 {
    public static void main(String[] args) {
        System.out.println(findSubstring("dogcatcatcodecatdog", new String[]{"cat", "dog"}));
        System.out.println(findSubstring("barfoobazbitbyte", new String[]{"dog", "cat"}));
    }

    // complexity analysis: time: O(N * M * L) - space: O(M)
    // N is the length of the input string s
    // M is the number of words in the input array words
    // L is the length of each word in the input array words
    public static List<Integer> findSubstring(String s, String[] words) {
        // Initialize an empty result list
        List<Integer> result = new ArrayList<>();

        // Edge case: if string or words array is empty or null, return empty result list
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        Map<String, Integer> wordsCount = new HashMap<>();
        for (String word : words) {
            wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1);
        }

        // Create a HashMap to store the count of each word in the words array
        int wordLength = words[0].length();  // each word has the same length per question
        int totalWords = words.length;

        // // Iterate through the string s to find substrings - solve with a sliding window
        for (int i = 0; i <= s.length() - wordLength * totalWords; i++) {
            // Create a HashMap to store the count of words seen in the current substring
            Map<String, Integer> seenWords = new HashMap<>();

            int j = 0;
            for (; j < totalWords; j++) {
                int wordStart = i + j * wordLength;
                String word = s.substring(wordStart, wordStart + wordLength);

                // If the word is not in the words array, break the loop
                if (!wordsCount.containsKey(word)) {
                    break;
                }

                // Update the seenWords HashMap with the current word
                seenWords.put(word, seenWords.getOrDefault(word, 0) + 1);

                // If the current word count in seenWords is greater than the count in wordsCount, break the loop
                if (seenWords.get(word) > wordsCount.get(word)) {
                    break;
                }
            }

            // If the current word count in seenWords is greater than the count in wordsCount, break the loop
            if (j == totalWords) {
                result.add(i);
            }
        }

        return result;
    }
}
