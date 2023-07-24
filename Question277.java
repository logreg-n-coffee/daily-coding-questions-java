/**
 * UTF-8 is a character encoding that maps each symbol to one, two, three, or
 * four bytes.
 * 
 * For example, the Euro sign, €, corresponds to the three bytes 11100010
 * 10000010 10101100. The rules for mapping characters are as follows:
 * 
 * For a single-byte character, the first bit must be zero.
 * For an n-byte character, the first byte starts with n ones and a zero. The
 * other n - 1 bytes all start with 10.
 * Visually, this can be represented as follows.
 * 
 * Bytes | Byte format
 * -----------------------------------------------
 * 1 | 0xxxxxxx
 * 2 | 110xxxxx 10xxxxxx
 * 3 | 1110xxxx 10xxxxxx 10xxxxxx
 * 4 | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * Write a program that takes in an array of integers representing byte values,
 * and returns whether it is a valid UTF-8 encoding.
 */

public class Question277 {
    public static void main(String[] args) {
        // An example of valid UTF-8 encoding
        int[] bytes1 = { 197, 130, 1 };

        // An example of invalid UTF-8 encoding
        int[] bytes2 = { 235, 140, 4 };

        // Output whether the provided byte arrays are valid UTF-8 encodings
        System.out.println(isValidUtf8(bytes1)); // Should print: true
        System.out.println(isValidUtf8(bytes2)); // Should print: false
    }

    /**
     * This method checks if an array of integers represent a valid UTF-8 encoding.
     *
     * @param data The array of integers to check
     * @return boolean value indicating if the input is valid UTF-8 encoding
     */
    public static boolean isValidUtf8(int[] data) {
        int count = 0; // This will hold the number of continuation bytes expected after the current
                       // byte

        for (int i = 0; i < data.length; i++) {
            // If no continuation bytes are expected, check if the current byte starts a new
            // character
            if (count == 0) {
                // Byte starts with '110', so one continuation byte is expected
                if (data[i] >> 5 == 0b110) // The right shift operator (>> 5) shifts the bits five places to the right,
                                           // effectively removing the least significant five bits.
                    count = 1;
                // Byte starts with '1110', so two continuation bytes are expected
                else if (data[i] >> 4 == 0b1110) // The right shift operator (>> 4) shifts the bits four places to the
                                                 // right, effectively removing the least significant four bits.
                    count = 2;
                // Byte starts with '11110', so three continuation bytes are expected
                else if (data[i] >> 3 == 0b11110) // The right shift operator (>> 3) shifts the bits three places to
                                                  // the right, effectively removing the least significant three
                                                  // bits.
                    count = 3;
                // If byte starts with '1', it's an invalid encoding
                else if (data[i] >> 7 != 0) // The right shift operator (>> 7) shifts the bits seven places to the
                                            // right, effectively removing all but the most significant bit.
                    return false;
            } else {
                // If continuation bytes are expected, check if the current byte is a
                // continuation byte. Continuation bytes start with '10'
                if (data[i] >> 6 != 0b10)
                    return false;
                // Decrement the count of expected continuation bytes
                count--;
            }
        }
        // If no continuation bytes are expected, it's a valid encoding
        return count == 0;
    }
}
