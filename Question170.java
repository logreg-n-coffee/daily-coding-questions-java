/*
Given a start word, an end word, and a dictionary of valid words,
find the shortest transformation sequence from start to end such that
only one letter is changed at each step of the sequence, and each transformed word exists in the dictionary.

If there is no possible transformation, return null.
Each word in the dictionary have the same length as start and end and is lowercase.

For example, given start = "dog", end = "cat",
and dictionary = {"dot", "dop", "dat", "cat"},
return ["dog", "dot", "dat", "cat"].

Given start = "dog", end = "cat",
and dictionary = {"dot", "tod", "dat", "dar"},
return null as there is no possible transformation from dog to cat.
 */

import java.util.*;

public class Question170 {
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>(Arrays.asList("dot", "dop", "dat", "cat"));
        System.out.println(shortestTransformation("dog", "cat", dictionary));

        dictionary = new HashSet<>(Arrays.asList("dot", "tod", "dat", "dar"));
        System.out.println(shortestTransformation("cat", "dog", dictionary));
    }

    // use breath first search to find the shortest path
    // complexity: N is the number of words in the dictionary; M is the length of the word
    public static List<String> shortestTransformation(String start, String end, Set<String> dictionary) {
        Queue<List<String>> queue = new LinkedList<>();
        List<String> startList = new ArrayList<>();
        startList.add(start);
        queue.offer(startList);

        while (!queue.isEmpty()) {
            List<String> currentPath = queue.poll();
            String currentWord = currentPath.get(currentPath.size() - 1);

            if (currentWord.equals(end)) {
                return currentPath;
            }

            for (String word : getOneLetterDiffWords(currentWord, dictionary)) {
                List<String> newPath = new ArrayList<>(currentPath);
                newPath.add(word);
                queue.offer(newPath);
                dictionary.remove(word);
            }
        }
        return null;
    }

    public static Set<String> getOneLetterDiffWords(String currentWord, Set<String> dictionary) {
        Set<String> oneLetterDiffWords = new HashSet<>();

        for (String word : dictionary) {
            if (isOneLetterDiff(currentWord, word)) {
                oneLetterDiffWords.add(word);
            }
        }

        return oneLetterDiffWords;
    }

    private static boolean isOneLetterDiff(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return diffCount == 1;
    }
}
