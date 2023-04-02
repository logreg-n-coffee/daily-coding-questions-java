/*
Given a 32-bit integer, return the number with its bits reversed.

For example, given the binary number 1111 0000 1111 0000 1111 0000 1111 0000,
return 0000 1111 0000 1111 0000 1111 0000 1111.
 */

public class Question161 {
    public static void main(String[] args) {
        int num = 0b11110000111100001111000011110000; // Sample input
        int reversedNum = reverseBits(num);
        System.out.println(Integer.toBinaryString(reversedNum));
    }

    // Time: O(n) - Space: O(1)
    public static int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result <<= 1;  // Shift result left by 1 bit
            result |= (n & 1);  // Set the least significant bit of result to the least significant bit of n
            n >>= 1;  // Shift n right by 1 bit
        }

        return result;
    }
}
