
/**
 * A step word is formed by taking a given word, adding a letter, and
 * anagramming the result. For example, starting with the word "APPLE", you can
 * add an "A" and anagram to get "APPEAL". An anagram is a word or phrase formed
 * by rearranging the letters of another word or phrase.
 * 
 * Given a dictionary of words and an input word, create a function that returns
 * all valid step words.
 */

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Question266 {
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("test");
        dictionary.add("best");
        dictionary.add("rest");
        dictionary.add("nest");
        dictionary.add("net");
        dictionary.add("taste");
        dictionary.add("tent");
        dictionary.add("pest");
        dictionary.add("jest");
        dictionary.add("tests");
        dictionary.add("tset");
        dictionary.add("tes");
        dictionary.add("est");
        dictionary.add("tex");

        String word = "test";
        Set<String> stepWords = getStepWords(word, dictionary);

        System.out.println("Step words for " + word + ": " + Arrays.toString(stepWords.toArray()));
    }

    public static Set<String> getStepWords(String word, Set<String> dictionary) {
        Set<String> stepWords = new HashSet<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                StringBuilder builder = new StringBuilder(word);

                // change a letter
                builder.setCharAt(i, alphabet.charAt(j));
                // if we change a letter, there is a possibility that the original word is in
                // the dict
                if (dictionary.contains(builder.toString()) && !builder.toString().equals(word)) {
                    stepWords.add(builder.toString());
                }

                // remove a letter
                builder.deleteCharAt(i);
                if (dictionary.contains(builder.toString())) {
                    stepWords.add(builder.toString());
                }

                // reset builder
                builder = new StringBuilder(word);

                // add a letter
                builder.insert(i, alphabet.charAt(j));
                if (dictionary.contains(builder.toString())) {
                    stepWords.add(builder.toString());
                }
            }
        }

        // add a letter to the end
        for (int j = 0; j < alphabet.length(); j++) {
            StringBuilder builder = new StringBuilder(word);
            builder.append(alphabet.charAt(j));
            if (dictionary.contains(builder.toString())) {
                stepWords.add(builder.toString());
            }
        }

        return stepWords;
    }
}