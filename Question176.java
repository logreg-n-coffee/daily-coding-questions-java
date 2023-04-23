/*
Determine whether there exists a one-to-one character mapping from one string s1 to another s2.

For example, given s1 = abc and s2 = bcd, return true since we can map a to b, b to c, and c to d.

Given s1 = foo and s2 = bar, return false since the o cannot map to two characters.
 */

import java.util.Map;
import java.util.HashMap;

public class Question176 {
    public static void main(String[] args) {
        System.out.println(hasOneToOneMapping("abc", "bcd"));  // true
        System.out.println(hasOneToOneMapping("foo", "bar"));  // false
    }

    public static boolean hasOneToOneMapping(String s1, String s2) {
        // edge case
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Character> charMap = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            // checks if the character c1 from string s1 is already in the charMap
            if (charMap.containsKey(c1)) {
                // if it is, it means there is an existing mapping for c1
                // then checks whether the value associated with c1 in the charMap is equal to c2
                if (charMap.get(c1) != c2) {
                    // If they are not equal, it means that c1 is mapped to a different character,
                    // which violates the one-to-one mapping condition,
                    return false;
                }
            } else {
                // Now, the character c1 from string s1 is already in the charMap
                // check if the character c2 from string s2 is already a value in the charMap.
                if (charMap.containsValue(c2)) {
                    //  If it is, it means that another character from string s1 has been mapped to c2,
                    //  violating the one-to-one mapping condition
                    return false;
                }
                // we can create a new one-to-one mapping between c1 and c2
                charMap.put(c1, c2);
            }
        }

        return true;
    }
}
