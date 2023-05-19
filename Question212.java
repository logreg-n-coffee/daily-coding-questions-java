/**
 * Spreadsheets often use this alphabetical encoding for its columns: "A", "B",
 * "C", ..., "AA", "AB", ..., "ZZ", "AAA", "AAB", ....
 * 
 * Given a column number, return its alphabetical column id. For example, given
 * 1, return "A". Given 27, return "AA".
 */

public class Question212 {
    public static void main(String[] args) {
        System.out.println(getColumnName(1)); // A
        System.out.println(getColumnName(27)); // AA
        System.out.println(getColumnName(28)); // AB
    }

    public static String getColumnName(int columnNumber) {
        StringBuilder columnName = new StringBuilder();

        while (columnNumber > 0) {
            // Subtract 1 from the column number because the naming convention is 1-indexed
            // rather than 0-indexed.
            // Take the remainder (rem) when the adjusted column number is divided by 26.
            // This will give the "digit" in the 26-based system, which ranges from 0 to 25.
            int rem = (columnNumber - 1) % 26;

            // Convert this number into the corresponding ASCII letter by adding the ASCII
            // value of 'A', and append it to the beginning of the column id.
            columnName.append((char) (rem + 'A'));

            // Divide the column number by 26, and continue the process until the column
            // number becomes 0.
            columnNumber = (columnNumber - 1) / 26;
        }

        return columnName.reverse().toString();
    }
}
