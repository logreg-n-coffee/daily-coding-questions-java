import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create a basic sentence checker that takes in a stream of characters and
 * determines whether they form valid sentences. If a sentence is valid, the
 * program should print it out.
 * 
 * We can consider a sentence valid if it conforms to the following rules:
 * 
 * 1. The sentence must start with a capital letter, followed by a lowercase
 * letter or a space.
 * 2. All other characters must be lowercase letters, separators (,,;,:) or
 * terminal marks (.,?,!,‽).
 * 3. There must be a single space between each word.
 * 4. The sentence must end with a terminal mark immediately following a word.
 */

class Question263 {
    private static final Pattern sentencePattern = Pattern.compile(
            "^[A-Z][a-z\\s]*([a-z]|[,.:;])+\\s*([a-z\\s]*([a-z]|[,.:;])+\\s*)*[.?!‽]$");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sentences. An empty line ends the input.");

        StringBuilder builder = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                break;
            }

            builder.append(line);

            if (line.endsWith(".") || line.endsWith("?") || line.endsWith("!") || line.endsWith("‽")) {
                Matcher matcher = sentencePattern.matcher(builder.toString());

                if (matcher.matches()) {
                    System.out.println("Valid sentence: " + builder.toString());
                } else {
                    System.out.println("Invalid sentence: " + builder.toString());
                }

                builder.setLength(0); // reset the builder
            }
        }

        scanner.close();
    }
}
