import java.util.Stack;

/**
 * Given an absolute pathname that may have . or .. as part of it, return the
 * shortest standardized path.
 * 
 * For example, given "/usr/bin/../bin/./scripts/../", return "/usr/bin/".
 */

public class Question222 {
    public static void main(String[] args) {
        String pathname = "/usr/bin/../bin/./scripts/../";
        String standardizedPath = shortestStandardizedPath(pathname);
        System.out.println(standardizedPath); // expected output: /usr/bin/
    }

    public static String shortestStandardizedPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");

        for (String component : components) {
            if (component.equals(".") || component.isEmpty()) {
                continue;
            } else if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(component);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }

        return result.length() > 0 ? result.toString() : "/";
    }
}
