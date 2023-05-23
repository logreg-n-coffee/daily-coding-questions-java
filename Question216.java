
/**
 * Given a number in Roman numeral format, convert it to decimal.
 * 
 * The values of Roman numerals are as follows:
 * 
 * {
 * 'M': 1000,
 * 'D': 500,
 * 'C': 100,
 * 'L': 50,
 * 'X': 10,
 * 'V': 5,
 * 'I': 1
 * }
 * In addition, note that the Roman numeral system uses subtractive notation for
 * numbers such as IV and XL.
 * 
 * For the input XIV, for instance, you should return 14.
 */

import java.util.HashMap;
import java.util.Map;

public class Question216 {
    private static Map<Character, Integer> romanToDecimalMap;

    // static block to init the map
    static {
        romanToDecimalMap = new HashMap<Character, Integer>();
        romanToDecimalMap.put('M', 1000);
        romanToDecimalMap.put('D', 500);
        romanToDecimalMap.put('C', 100);
        romanToDecimalMap.put('L', 50);
        romanToDecimalMap.put('X', 10);
        romanToDecimalMap.put('V', 5);
        romanToDecimalMap.put('I', 1);
    }

    public static void main(String[] args) {
        String romanNumeral = "XIV";
        int decimalValue = romanToDecimal(romanNumeral);
        System.out.println("Decimal value of " + romanNumeral + " is: " + decimalValue);
    }

    public static int romanToDecimal(String roman) {
        int result = 0;
        int length = roman.length();

        for (int i = 0; i < length; i++) {
            int currentVal = romanToDecimalMap.get(roman.charAt(i));

            if (i < length - 1) {
                int nextVal = romanToDecimalMap.get(roman.charAt(i + 1));

                if (currentVal < nextVal) {
                    result -= currentVal;
                } else {
                    result += currentVal;
                }
            } else {
                result += currentVal;
            }
        }

        return result;
    }
}
