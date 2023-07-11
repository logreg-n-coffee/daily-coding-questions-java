
/**
 * The sequence [0, 1, ..., N] has been jumbled, and the only clue you have for
 * its order is an array representing whether each number is larger or smaller
 * than the last. Given this information, reconstruct an array that is
 * consistent with it. 
 */

import java.util.*;

public class Question260 {
    public static void main(String[] args) {
        Character[] array = { null, '+', '+', '-', '+' };
        List<Integer> answer = reconstruct(array);
        System.out.println(answer);
    }

    public static List<Integer> reconstruct(Character[] array) {
        List<Integer> answer = new ArrayList<>();
        int n = array.length - 1;
        List<Integer> stack = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (array[i + 1] == '-') {
                stack.add(i);
            } else {
                answer.add(i);
                while (!stack.isEmpty()) {
                    answer.add(stack.remove(stack.size() - 1)); // remove last item
                }
            }
        }

        stack.add(n);

        while (!stack.isEmpty()) {
            answer.add(stack.remove(stack.size() - 1)); // remove last item
        }

        return answer;
    }
}