/*
Given a string, split it into as few strings as possible such that each string is a palindrome.

For example, given the input string racecarannakayak, return ["racecar", "anna", "kayak"].

Given the input string abc, return ["a", "b", "c"].
 */


import java.util.ArrayList;
import java.util.List;

public class Question181 {
    public static void main(String[] args) {
        String s1 = "racecarannakayak";
        String s2 = "abc";

        System.out.println(splitIntoPalindromes(s1)); // Output: ["racecar", "anna", "kayak"]
        System.out.println(splitIntoPalindromes(s2)); // Output: ["a", "b", "c"]
    }

    public static List<String> splitIntoPalindromes(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }

        for (int k = 3; k < n + 1; k++) {
            for (int i = 0; (i + k - 1) < n; i++) {
                int j = i + k - 1;
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }

        List<List<String>> cuts = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            cuts.add(null);
        }
        cuts.set(0, new ArrayList<>());

        for (int i = 0; i < cuts.size(); i++) {
            for (int j = 0; j < i; j++) {
                int matrix_i = i - 1;

                if (dp[j][matrix_i]) {
                    if (cuts.get(i) == null || (cuts.get(j).size() + 1 < cuts.get(i).size())) {
                        List<String> temp = new ArrayList<>(cuts.get(j));
                        temp.add(s.substring(j, i));
                        cuts.set(i, temp);
                    }
                }
            }
        }

        return cuts.get(n);
    }
}
