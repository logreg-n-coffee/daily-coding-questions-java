
/**
 * A cryptarithmetic puzzle is a mathematical game where the digits of some
 * numbers are represented by letters. Each letter represents a unique digit.
 * 
 * For example, a puzzle of the form:
 * 
 * **SEND
 * + MORE
 * --------
 * *MONEY
 * may have the solution:
 * 
 * {'S': 9, 'E': 5, 'N': 6, 'D': 7, 'M': 1, 'O', 0, 'R': 8, 'Y': 2}
 * Given a three-word puzzle like the one above, create an algorithm that finds
 * a solution.
 */

import java.util.*;

public class Question250 {
    public static void main(String[] args) {
        String word1 = "SEND";
        String word2 = "MORE";
        String word3 = "MONEY";
        Solver solver = new Solver(word1, word2, word3);
        Map<Character, Integer> solution = solver.solve();
        System.out.println(solution);
    }

    static class Solver {
        private String word1;
        private String word2;
        private String word3;

        public Solver(String word1, String word2, String word3) {
            this.word1 = word1;
            this.word2 = word2;
            this.word3 = word3;
        }

        public Map<Character, Integer> solve() {
            String uniqueLetters = getUniqueLetters(word1 + word2 + word3);
            return solve(uniqueLetters, new HashMap<>(), new HashSet<>());
        }

        private String getUniqueLetters(String str) {
            Set<Character> set = new LinkedHashSet<>();
            for (char c : str.toCharArray()) {
                set.add(c);
            }
            StringBuilder uniqueLetters = new StringBuilder(set.size());
            for (Character c : set) {
                uniqueLetters.append(c);
            }
            return uniqueLetters.toString();
        }

        private Map<Character, Integer> solve(String uniqueLetters, Map<Character, Integer> letterToDigit,
                Set<Integer> usedDigits) {
            if (uniqueLetters.length() == 0) {
                if (isValidSolution(letterToDigit)) {
                    return new HashMap<>(letterToDigit);
                }
                return null;
            }
            char letter = uniqueLetters.charAt(0);
            String remainingLetters = uniqueLetters.substring(1);

            for (int digit = 0; digit <= 9; digit++) {
                // forbid leading zero(s)
                if (digit == 0 && (word1.indexOf(letter) == 0 || word2.indexOf(letter) == 0
                        || word3.indexOf(letter) == 0)) {
                    continue;
                }

                if (usedDigits.contains(digit)) {
                    continue;
                }
                letterToDigit.put(letter, digit);
                usedDigits.add(digit);
                Map<Character, Integer> solution = solve(remainingLetters, letterToDigit, usedDigits);
                if (solution != null) {
                    return solution;
                }
                usedDigits.remove(digit);
                letterToDigit.remove(letter);
            }
            return null;
        }

        private boolean isValidSolution(Map<Character, Integer> letterToDigit) {
            int num1 = wordToNumber(word1, letterToDigit);
            int num2 = wordToNumber(word2, letterToDigit);
            int num3 = wordToNumber(word3, letterToDigit);
            return num1 + num2 == num3;
        }

        private int wordToNumber(String word, Map<Character, Integer> letterToDigit) {
            int num = 0;
            for (char c : word.toCharArray()) {
                int digit = letterToDigit.get(c);
                num = num * 10 + digit;
            }
            return num;
        }
    }
}
