
/**
 * Given a string with repeated characters, rearrange the string so that no two
 * adjacent characters are the same. If this is not possible, return None.
 * 
 * For example, given "aaabbc", you could return "ababac". Given "aaab", return
 * None.
 */

import java.util.PriorityQueue;

public class Question231 {
    // solve the problem with greedy approach -
    public static void main(String[] args) {
        String s1 = "aaabbc";
        String s2 = "aaab";
        System.out.println(rearrangeString(s1)); // expected output: "ababac"
        System.out.println(rearrangeString(s2)); // expected output: "None"
    }

    static class Pair {
        int count;
        char ch;

        Pair(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }
    }

    public static String rearrangeString(String s) {
        // count the frequency of each character in the input string
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // store the counts in a priority queue in descending order (max heap) -
        // O(n log n)
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.count - a.count);
        for (char c = 'a'; c <= 'z'; c++) {
            if (freq[c - 'a'] > 0) {
                pq.offer(new Pair(freq[c - 'a'], c));
            }
        }

        // build the result string
        // repeatedly extract the two most frequent characters from the priority queue
        Pair prev = new Pair(-1, '#');
        String result = "";

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            result += curr.ch;

            if (prev.count > 0) {
                pq.offer(prev);
            }

            curr.count--;
            prev = curr;
        }

        return result.length() == s.length() ? result : "None";
    }
}
