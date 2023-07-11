import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Huffman coding is a method of encoding characters based on their frequency.
 * Each letter is assigned a variable-length binary string, such as 0101 or
 * 111110, where shorter lengths correspond to more common letters. To
 * accomplish this, a binary tree is built such that the path from the root to
 * any leaf uniquely maps to a character. When traversing the path, descending
 * to a left child corresponds to a 0 in the prefix, while descending right
 * corresponds to 1.
 * 
 * Here is an example tree (note that only the leaf nodes have letters):
 *
 * --------*--------
 * ------/---\------
 * ----*-------*----
 * ---/-\-----/-\---
 * --*---a---t---*--
 * -/-------------\-
 * c---------------s
 * 
 * Example Explained:
 * 'c': Starting from the root, we go left once, and reach 'c'. Therefore, the
 * Huffman code for 'c' is '0'.
 * 
 * 'a': From the root, we go left, then right, and reach 'a'. So, 'a' is
 * represented as '01'.
 * 
 * 't': From the root, we go right, then left, and reach 't'. So, 't' is
 * represented as '10'.
 * 
 * 's': Starting from the root, we go right once, and reach 's'. Therefore, 's'
 * is '1'.
 * 
 * With this encoding, cats would be represented as 0000110111.
 * 
 * Given a dictionary of character frequencies, build a Huffman tree, and use it
 * to determine a mapping between characters and their encoded binary strings.
 * (discrepancy is possible due to the potential for multiple valid Huffman
 * Trees depending on the ordering of characters with the same frequency.)
 */

public class Question261 {
    static class HuffmanNode {
        char c;
        int freq;
        HuffmanNode left;
        HuffmanNode right;
    }

    static class MyComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y) {
            return x.freq - y.freq;
        }
    }

    public static void main(String[] args) {
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] charFreq = { 5, 9, 12, 13, 16, 45 };

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(charArray.length, new MyComparator());

        for (int i = 0; i < charArray.length; i++) {
            HuffmanNode hn = new HuffmanNode();

            hn.c = charArray[i];
            hn.freq = charFreq[i];

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            f.freq = x.freq + y.freq;
            f.c = '-';

            f.left = x;
            f.right = y;

            root = f;

            q.add(f);
        }

        printCode(root, "");
    }

    static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
}
