import java.util.Stack;

/**
 * Given a string consisting of parentheses, single digits, and positive and
 * negative signs, convert the string into a mathematical expression to obtain
 * the answer.
 * 
 * Don't use eval or a similar built-in parser.
 * 
 * For example, given '-1 + (2 + 3)', you should return 4.
 */

public class Question274 {
    public static void main(String[] args) {
        String expression = "-1 + (2 + 3)";
        System.out.println(evaluate(expression));
    }

    public static int evaluate(String expression) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        char[] chars = expression.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            // skip whitespace
            if (chars[i] == ' ') {
                continue;
            }

            // handle negative numbers
            if (chars[i] == '-' && Character.isDigit(chars[i + 1])) {
                StringBuilder sb = new StringBuilder();
                sb.append('-'); // add the negative sign
                while (i + 1 < chars.length && Character.isDigit(chars[i + 1])) {
                    sb.append(chars[++i]); // pre-increment i here
                }
                values.push(Integer.parseInt(sb.toString()));
            }

            // push opening brace
            else if (chars[i] == '(') {
                ops.push(chars[i]);
            }

            // push number
            else if (Character.isDigit(chars[i])) {
                StringBuilder sb = new StringBuilder();
                while (i < chars.length && Character.isDigit(chars[i])) {
                    sb.append(chars[i++]); // post increment i here
                }
                values.push(Integer.parseInt(sb.toString()));
                i--; // decrement i because it was incremented in the last loop
            }

            // push operator
            else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {
                while (!ops.isEmpty() && hasPrecedence(chars[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(chars[i]);
            }

            // push closing brace
            else if (chars[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }
}
