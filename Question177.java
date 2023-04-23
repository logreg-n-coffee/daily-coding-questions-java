/*
Given a linked list and a positive integer k, rotate the list to the right by k places.

For example, given the linked list 7 -> 7 -> 3 -> 5 and k = 2, it should become 3 -> 5 -> 7 -> 7.

Given the linked list 1 -> 2 -> 3 -> 4 -> 5 and k = 3, it should become 3 -> 4 -> 5 -> 1 -> 2.
 */

public class Question177 {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(7);
        head1.next = new ListNode(7);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(5);

        ListNode rotatedHead1 = rotateList(head1, 2);
        printList(rotatedHead1);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);

        ListNode rotatedHead2 = rotateList(head2, 3);
        printList(rotatedHead2);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode rotateList(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode current = head;

        // Find the length of the linked list.
        int length = 1;

        while (current.next != null) {
            current = current.next;
            length++;
        }

        // Adjust the value of k to handle cases when k is greater than the length of the list.
        k = k % length;

        // Edge case
        if (k == 0) {
            return head;
        }

        // Find the (length - k)th node in the list.
        current.next = head;  // make the list circular
        int stepsToNewHead = length - k;

        while (stepsToNewHead-- > 0) {
            current = current.next;
        }

        // Update the links in the list to rotate the list by k places.
        head = current.next;
        current.next = null;  // break the circular list

        return head;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.println(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
