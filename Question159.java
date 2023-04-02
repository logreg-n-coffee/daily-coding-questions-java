/*
Given a string, return the first recurring character in it, or null if there is no recurring character.

For example, given the string "acbbac", return "b". Given the string "abcdef", return null.
 */

import java.util.ArrayList;

public class Question159 {
    public static void main(String[] args) {
        System.out.println(firstRecurringChar("acbbac"));
        System.out.println(firstRecurringChar("abcdef"));
    }

    // Solve with O(n) time and space
    public static Character firstRecurringChar(String str) {
        ArrayList<Character> foundChars = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // if key does not exist, add it in
            if (!foundChars.contains(currentChar)) {
                foundChars.add(currentChar);
            } else {
                // found a recurring character
                return currentChar;
            }
        }

        return null;
    }
}
