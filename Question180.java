/*
Given a stack of N elements, interleave the first half of the stack
with the second half reversed using only one other queue. This should be done in-place.

Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.

For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3].
If the stack is [1, 2, 3, 4], it should become [1, 4, 2, 3].

Hint: Try working backwards from the end state.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Question180 {
    public static void main(String[] args) {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        stack1.push(5);

        interleaveStack(stack1);
        System.out.println(stack1);

        Stack<Integer> stack2 = new Stack<>();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        stack2.push(4);

        interleaveStack(stack2);
        System.out.println(stack2);
    }

    public static void interleaveStack(Stack<Integer> stack) {
        int n = stack.size();
        int halfSize = n / 2;  // floor
        Queue<Integer> queue = new LinkedList<>();

        // Step 1: Put all elements into the queue and get (5, 4, 3, 2, 1)
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // Step 2: Rotate stack.size() / 2 elements by taking them off the queue (5, 4)
        // and putting them back to get (3, 2, 1, 5, 4)
        for (int i = 0; i < halfSize; i++) {
            queue.add(queue.poll());
        }

        // Step 3: Put Math.ceil(n / 2.0) elements into the stack. The queue is now (5, 4) and stack is [3, 2, 1]
        for (int i = 0; i < Math.ceil(n / 2.0); i++) {
            stack.push(queue.poll());
        }

        // Step 4: Pair them up stack.size() / 2 times. If stack is still not empty, pop one more time
        for (int i = 0; i < halfSize; i++) {
            queue.add(stack.pop());
            queue.add(queue.poll());
        }
        if (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // Step 5: Move all elements from the queue to the stack
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
    }
}
