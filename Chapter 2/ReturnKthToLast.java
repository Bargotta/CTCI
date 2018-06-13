/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.2 Return Kth to Last
 * Desc: Implement an algorithm to find the Kth to last element of a singly 
 * linked list
 *
 * Author: Aaron Bargotta
 * Date: 06/12/18
 */

import java.util.*;

// n := # nodes in linked list
public class ReturnKthToLast {
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

	private static int getLength(LinkedListNode head) {
		int len = 0;
		while (head != null) {
			head = head.next;
			len++;
		}
		return len;
	}

	// O(n) time and O(1) space
	public static int returnKthToLast(LinkedListNode head, int k) {
		int n = getLength(head);
		if (n == 0 || k < 1 || k > n) return -1;

		LinkedListNode curr = head;
		for (int i = 0; i < n - k; i++) {
			curr = curr.next;
		}
		return curr.data;
	}

	// O(n) time and O(1) space
	public static int returnKthToLast2(LinkedListNode head, int k) {
		if (head == null || k < 1) return -1;

		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		for (int i = 0; i < k; i++) {
			if (p1 == null) return -1;
			p1 = p1.next;
		}

		while (p1 != null) {
			p2 = p2.next;
			p1 = p1.next;
		}
		return p2.data;
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

	private static void tester(int n, int k) {
		LinkedListNode head = generateLinkedList(n);
		System.out.println("Linked List:");
		System.out.println(head);

		System.out.println("returnKthToLast(head, " + k + "):");
		System.out.println(returnKthToLast2(head, k));
	}

	public static void main(String[] args) {
		tester(5, 3);
		System.out.println();

		tester(1, 0);
		System.out.println();

		tester(5, 0);
		System.out.println();

		tester(5, 5);
		System.out.println();

		tester(1, 1);
		System.out.println();

		tester(10, 4);
		System.out.println();
	}
}