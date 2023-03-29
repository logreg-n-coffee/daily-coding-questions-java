/*
Given a string, determine whether any permutation of it is a palindrome.

For example, carrace should return true, since it can be rearranged to form racecar, which is a palindrome.
daily should return false, since there's no rearrangement that can form a palindrome.
 */

import java.util.HashMap;
import java.util.List;

public class Question157 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("carrace"));  // offset list will be [1] (extra 'e' appeared 1 time) - valid
        System.out.println(isPalindrome("raceeecar"));  // offset list will be [3] ('e' appeared 3 times) - valid
        System.out.println(isPalindrome("daily"));  // offset list will be [1, 1, 1, 1, 1] (none can be canceled)
        System.out.println(isPalindrome("abcabc"));  // offset list will be [] (everything canceled)
    }

    public static boolean isPalindrome(String str) {
        HashMap<Character, Integer> stringToCount = new HashMap<>();

        // build a hashmap for frequency count
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);

            if (!stringToCount.containsKey(currChar)) {
                stringToCount.put(currChar, 1);
            } else {
                stringToCount.put(currChar, stringToCount.get(currChar) + 1);
            }
        }

        // if the char count is divisible by 2 it means that they can be offset / canceled out
        List<Integer> offset = stringToCount.values().stream().filter(x -> x % 2  != 0).toList();

        // we can allow everything to be offset then the offset list size will be zero
        // we can allow everything except only one character (in the center)
        return offset.size() == 1 || offset.size() == 0;
    }
}
