/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.8 Loop Detection
 * Desc: Given a circular linked list, implement an algorithm that returns the
 * node at the beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer
 * points to an earlier node, so as to make a loop in the linked list.
 * EXAMPLE
 * Input:  A -> B -> C -> D -> E -> C [the same C as earlier]
 * Output: C
 *
 * Author: Aaron Bargotta
 * Date: 06/15/18
 */

public class LoopDetection {
	private static class LinkedListNode {
		private int data;
		private LinkedListNode next;

		public LinkedListNode(int c) {
			data = c;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(data);
			sb.append("->");
			if (next != null) sb.append(next.data);
			else sb.append("null");
			return sb.toString();
		}
	}

	public static LinkedListNode loopDetection(LinkedListNode n) {
		LinkedListNode slow = n.next;
		LinkedListNode fast = n.next.next;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next.next;
		}

		slow = n;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	private static LinkedListNode generateLinkedList(int n, boolean loop) {
		LinkedListNode head = new LinkedListNode(1);
		LinkedListNode curr = head;

		int count = 1;
		while (count++ < n) {
			curr.next = new LinkedListNode(count);
			curr = curr.next;
		}
		if (loop) curr.next = head;
		return head;
	}

	private static void printLoop(LinkedListNode stem, LinkedListNode loop) {
		while (stem != loop.next) {
			System.out.print(stem + " ");
			stem = stem.next;
		}

		LinkedListNode curr = loop.next;
		while (curr != loop) {
			System.out.print(curr + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	private static void join(LinkedListNode stem, LinkedListNode loop) {
		while (stem.next != null) {
			stem = stem.next;
		}
		stem.next = loop;
	}

	private static void tester(int k, int n) {
		LinkedListNode stem = generateLinkedList(k, false);
		LinkedListNode loop = generateLinkedList(n, true);
		join(stem, loop);

		printLoop(stem, loop);
		System.out.println("loopDetection(loop) = " + loopDetection(stem));
	}

	public static void main(String[] args) {
		tester(3, 7);
		System.out.println();

		tester(10, 6);
		System.out.println();

		tester(0, 6);
		System.out.println();
	}
}
