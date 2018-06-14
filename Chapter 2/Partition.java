/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.4 Partition
 * Desc: Write code to partition a linked list around a value x, such that all
 * nodes less than x come before all nodes greater than or equal to x. If x is
 * contained within the list, the values of x only need to be after the elements
 * less than x (see below). The partition element x can appear anywhere in the
 * "right partition"; it does not need to appear between the left and right
 * partitions.
 * EXAMPLE
 * Input:  3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 * Author: Aaron Bargotta
 * Date: 06/13/18
 */

import java.util.*;

// n := # nodes in linked list
public class Partition {
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

	// O(n) time and O(1) space
	public static LinkedListNode partition(LinkedListNode n, int x) {
		LinkedListNode left = new LinkedListNode(-1);
		LinkedListNode right = new LinkedListNode(-1);
		LinkedListNode startL = left;
		LinkedListNode startR = right;

		while (n != null) {
			if (n.data < x) {
				left.next = n;
				left = left.next;
			}
			else {
				right.next = n;
				right = right.next;
			}
			n = n.next;
		}
		left.next = startR.next;
		right.next = null;

		return startL.next;
	}

	private static LinkedListNode generateLinkedList(int n) {
		Random r = new Random();
		LinkedListNode head = new LinkedListNode(r.nextInt(n));
		LinkedListNode curr = head;

		int count = 1;
		while (count++ < n) {
			curr.next = new LinkedListNode(r.nextInt(n));
			curr = curr.next;
		}

		return head;
	}

	private static void tester(int n, int x) {
		LinkedListNode head = generateLinkedList(n);
		System.out.println("Linked List:");
		System.out.println(head);

		System.out.println("partition(head, " + x + "):");
		System.out.println(partition(head, x));
	}
	
	public static void main(String[] args) {
		tester(10, 5);
		System.out.println();

		tester(1, 1);
		System.out.println();

		tester(3, 1);
		System.out.println();
	}
}