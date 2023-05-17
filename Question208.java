/*
 * Given a linked list of numbers and a pivot k, 
 * partition the linked list so that all nodes less than k 
 * come before nodes greater than or equal to k.
 * 
 * For example, given the linked list 5 -> 1 -> 8 -> 0 -> 3 and k = 3, 
 * the solution could be 1 -> 0 -> 5 -> 8 -> 3.
 */

import java.util.LinkedList;

public class Question208 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(1);
        list.add(8);
        list.add(0);
        list.add(3);

        partition(list, 3);

        for (int num : list) {
            System.out.print(num + " -> ");
        }

        System.out.println("null");
    }

    public static void partition(LinkedList<Integer> list, int k) {
        LinkedList<Integer> less = new LinkedList<>();
        LinkedList<Integer> greater = new LinkedList<>();

        for (int num : list) {
            if (num < k) {
                less.add(num);
            } else {
                greater.add(num);
            }
        }

        less.addAll(greater);
        list.clear();
        list.addAll(less);
    }
}
