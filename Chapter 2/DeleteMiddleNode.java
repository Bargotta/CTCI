/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.2 Return Kth to Last
 * Desc: Implement an algorithm to delete a node in the middle (i.e., any node
 * but the first and last node, not necessarily the exact middle) of a singly
 * linked list, given only access to that node.
 * EXAMPLE
 * Input: the node c from the linked list a->b->c->d->e->f
 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 *
 * Author: Aaron Bargotta
 * Date: 06/13/18
 */

// n := # of nodes in linked list
public class DeleteMiddleNode {
	private static class LinkedListNode {
		private int data;
		private LinkedListNode next;

		public LinkedListNode(int v) {
			data = v;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(data);

			LinkedListNode curr = next;
			while (curr != null) {
				sb.append(" ");
				sb.append(curr.data);
				curr = curr.next;
			}
			return sb.toString();
		}
	}

	// O(1) time and O(1) space
	public static boolean deleteMiddleNode(LinkedListNode middle) {
		if (middle == null || middle.next == null) return false;
		LinkedListNode n = middle.next;
		middle.data = n.data;
		middle.next = n.next;
		n = null;
		return true;
	}

	private static LinkedListNode generateLinkedList(int n) {
		int count = 1;
		LinkedListNode head = new LinkedListNode(count);
		LinkedListNode curr = head;

		while (count++ < n) {
			curr.next = new LinkedListNode(count);
			curr = curr.next;
		}

		return head;
	}

	private static void tester(int n, int mid) {
		LinkedListNode head = generateLinkedList(n);
		System.out.println("Linked List:");
		System.out.println(head);

		LinkedListNode middle = head;
		for (int i = 1; i < mid; i ++) {
			middle = middle.next;
		}
		System.out.println("deleteMiddleNode('del node " + mid + "'):");
		deleteMiddleNode(middle);
		System.out.println(head);
	}

	public static void main(String[] args) {
		tester(5, 1);
		System.out.println();

		tester(5, 2);
		System.out.println();

		tester(5, 3);
		System.out.println();

		tester(5, 4);
		System.out.println();

		tester(3, 2);
		System.out.println();

		tester(10, 7);
		System.out.println();
	}
}