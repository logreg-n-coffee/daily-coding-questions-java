/**
 * Given a string and a number of lines k, print the string in zigzag form. In
 * zigzag, characters are printed out diagonally from top left to bottom right
 * until reaching the kth line, then back up to top right, and so on.
 * 
 * For example, given the sentence "thisisazigzag" and k = 4, you should print:
 * 
 * t-----a-----g
 * -h---s-z---a-
 * --i-i---i-z--
 * ---s------g---
 */

public class Question253 {
    public static void main(String[] args) {
        printZigzag("THISISAZIGZAG", 4);
    }

    public static void printZigzag(String s, int k) {
        // Guard clause to prevent invalid line count
        if (k <= 0) {
            return;
        }

        // Initialize an array of StringBuilders to store each line
        StringBuilder[] lines = new StringBuilder[k];
        for (int i = 0; i < k; i++) {
            lines[i] = new StringBuilder();
        }

        // Set initial conditions for zigzag - starting at line 0, moving down
        int index = 0;
        boolean goingDown = true;

        // Iterate over each character in the string
        for (char c : s.toCharArray()) {
            // Append a space or the character to each line depending on the current index
            for (int i = 0; i < k; i++) {
                if (i == index) {
                    lines[i].append(c);
                } else {
                    lines[i].append(" ");
                }
            }

            // Change direction if we've hit the top or bottom
            if (index == k - 1) {
                goingDown = false;
            } else if (index == 0) {
                goingDown = true;
            }

            // Move to the next line, direction depends on whether we're moving up or down
            index += goingDown ? 1 : -1;
        }

        // Print out each line
        for (StringBuilder line : lines) {
            System.out.println(line.toString());
        }
    }
}
