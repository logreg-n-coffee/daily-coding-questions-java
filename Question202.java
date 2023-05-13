/**
 * Write a program that checks whether an integer is a palindrome. For example,
 * 121 is a palindrome, as well as 888. 678 is not a palindrome. Do not convert
 * the integer into a string.
 */

public class Question202 {
    public static void main(String[] args) {
        int number = 121;
        System.out.println("Is " + number + " a palindrome? " + isPalindrome(number));

        number = 888;
        System.out.println("Is " + number + " a palindrome? " + isPalindrome(number));

        number = 678;
        System.out.println("Is " + number + " a palindrome? " + isPalindrome(number));
    }

    public static boolean isPalindrome(int number) {
        if (number < 0) {
            return false;
        }

        int reversed = 0;
        int originalNumber = number;

        // compute the reversed number using arithmetic operations
        while (number != 0) {
            int remainder = number % 10;
            reversed = reversed * 10 + remainder;
            number /= 10;
        }

        // compare the original number against the reversed number
        return originalNumber == reversed;
    }
}
