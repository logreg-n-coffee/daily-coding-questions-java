/*
 * Given a string of parentheses, find the balanced string that can be produced
 * from it using the minimum number of insertions and deletions. If there are
 * multiple solutions, return any of them.
 * 
 * For example, given "(()", you could return "(())". Given "))()(", you could
 * return "()()()()".
 */

public class Question199 {
    public static void main(String[] args) {
        String input1 = "(()";
        String input2 = "))()(";
        System.out.println("Balanced String for '" + input1 + "' : " + getBalancedString(input1));
        System.out.println("Balanced String for '" + input2 + "' : " + getBalancedString(input2));
    }

    /**
     * Returns the balanced string for a given string of parentheses with the
     * minimum
     * number of insertions and deletions.
     *
     * @param s The input string of parentheses.
     * @return The balanced string.
     */
    public static String getBalancedString(String s) {
        int open = 0;
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If it's an open parenthesis, increment the open variable.
                open++;
            } else if (open > 0) {
                // If it's a close parenthesis and there's an open parenthesis to close,
                // decrement the open variable.
                open--;
            } else {
                // If it's a close parenthesis and there are no open parentheses to close,
                // add an open parenthesis to the result string.
                result.append('(');
            }
            // Add the current character to the result string.
            result.append(c);
        }

        // Add the required number of close parentheses to the result string
        // based on the value of open.
        while (open-- > 0) {
            result.append(')');
        }

        return result.toString();
    }
}
