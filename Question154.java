/*
Implement a stack API using only a heap. A stack implements the following methods:

push(item), which adds an element to the stack
pop(), which removes and returns the most recently added element (or throws an error if there is nothing on the stack)

Recall that a heap has the following operations:
push(item), which adds a new key to the heap
pop(), which removes and returns the max value of the heap
 */

import java.util.PriorityQueue;

public class Question154 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());  // prints 3
        System.out.println(stack.pop());  // prints 2
        System.out.println(stack.pop());  // prints 1
    }

    // implement a stack using MaxHeap (priority - the larger the number is, the newer the head of the MaxHeap)
    static class Stack {
        private final PriorityQueue<Item> heap;
        private int seqNum;

        // constructor
        public Stack() {
            this.heap = new PriorityQueue<>();
            this.seqNum = 0;
        }

        public void push(int item) {
            this.heap.offer(new Item(item, this.seqNum++));
        }

        public int pop() {
            if (this.heap.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return this.heap.poll().getItem();
        }
    }

    static class Item implements Comparable<Item> {
        private final int item;
        private final int seqNum;

        // constructor
        public Item(int item, int seqNum) {
            this.item = item;
            this.seqNum = seqNum;
        }

        public int getItem() {
            return item;
        }

        @Override
        public int compareTo(Item other) {
            return Long.compare(other.seqNum, this.seqNum);  // other corresponds to the new item
        }
    }
}
