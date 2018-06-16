/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.7 Intersection
 * Desc: Given two (singly) linked lists, determine if the two lists intersect.
 * Return the intersecting node. Note that the intersection is defined based
 * on reference, not value. That is, if the kth node of the first linked list is
 * the exact same node (by reference) as the jth node of the second linked list,
 * the they are intersecting
 *
 * Author: Aaron Bargotta
 * Date: 06/14/18
 *
 * Note: A hashtable could also be used to store the references of each node.
 */

import java.util.*;

// n := # nodes in n1; m := # nodes in n2
public class Intersection {
	private static class LinkedListNode {
		private int data;
		private LinkedListNode next;

		public LinkedListNode(int c) {
			data = c;
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

	// O(nm) time and O(1) space
	public static LinkedListNode intersection(LinkedListNode n1, LinkedListNode n2) {
		while (n1 != null) {
			LinkedListNode runner = n2;
			while (runner != null) {
				if (n1 == runner) return n1;
				runner = runner.next;
			}
			n1 = n1.next;
		}
		return null;
	}

	// O(n + m) time and O(1) space
	public static LinkedListNode intersectionFast(LinkedListNode n1, LinkedListNode n2) {
		int len1 = length(n1);
		int len2 = length(n2);
		LinkedListNode shorter = (len1 < len2) ? n1 : n2;
		LinkedListNode longer = (len1 < len2) ? n2 : n1;

		for (int i = 0; i < Math.abs(len1 - len2); i++) {
			longer = longer.next;
		}

		while (longer != null && shorter != null) {
			if (longer == shorter) return longer;
			longer = longer.next;
			shorter = shorter.next;
		}
		return null;
	}

	private static int length(LinkedListNode n) {
		int len = 0;
		while (n != null) {
			n = n.next;
			len++;
		}
		return len;
	}

	private static LinkedListNode generateLinkedList(int n) {
		Random r = new Random();
		LinkedListNode head = new LinkedListNode(r.nextInt(10));
		LinkedListNode curr = head;

		int count = 1;
		while (count++ < n) {
			curr.next = new LinkedListNode(r.nextInt(10));
			curr = curr.next;
		}

		return head;
	}

	public static void main(String[] args) {
		LinkedListNode n1 = generateLinkedList(5);
		LinkedListNode n2 = generateLinkedList(4);
		LinkedListNode n3 = generateLinkedList(5);
		LinkedListNode n4 = generateLinkedList(4);
		n2.next.next.next.next = n1.next.next;
		System.out.println("n1: " + n1);
		System.out.println("n2: " + n2);
		System.out.println("intersectionFast(n1, n2) = " + intersectionFast(n1, n2));
		System.out.println();

		System.out.println("n3: " + n3);
		System.out.println("n4: " + n4);
		System.out.println("intersectionFast(n3, n4) = " + intersectionFast(n3, n4));
	}
}
