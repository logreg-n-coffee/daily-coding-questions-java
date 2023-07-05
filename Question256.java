/**
 * Given a linked list, rearrange the node values such that they appear in
 * alternating low -> high -> low -> high ... form. For example, given 1 -> 2 ->
 * 3 -> 4 -> 5, you should return 1 -> 3 -> 2 -> 5 -> 4.
 */

public class Question256 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Create nodes for the linked list
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        // Connect nodes to form the linked list: 1 -> 2 -> 3 -> 4 -> 5
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // Display original linked list
        System.out.println("Original linked list: ");
        printLinkedList(node1);

        // Call the rearrangeLinkedList method to rearrange the linked list
        rearrangeLinkedList(node1);

        // Display rearranged linked list
        System.out.println("Rearranged linked list: ");
        printLinkedList(node1);
    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void rearrangeLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode current = head;
        boolean isNextGreater = false;

        while (current != null && current.next != null) {
            // Swap the current node's value with the next node's value if isNextGreater is
            // true and the current node's value is greater than the next node's value, or
            // if isNextGreater is false and the current node's value is less than the next
            // node's value
            if ((isNextGreater && current.val < current.next.val)
                    || (!isNextGreater && current.val > current.next.val)) {
                int temp = current.val;
                current.val = current.next.val;
                current.next.val = temp;
            }

            // drive forward
            current = current.next;

            // toggle the isNextGreater flag
            isNextGreater = !isNextGreater;
        }
    }
}
