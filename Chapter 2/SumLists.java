/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.5 Sum Lists
 * Desc: You have two numbers represented by a linked list, where each node 
 * contains a single digit. the digits are stored in reverse order, such that
 * the 1's digit is at the head of the list. Write a function that adds the
 * two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * EXAMPLE
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
 * Output: 9 -> 1 -> 2. That is, 912.
 *
 * Author: Aaron Bargotta
 * Date: 06/13/18
 */

import java.util.*;

// n := # nodes in n1; m := # nodes in n2
public class SumLists {
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

	// O(n + m) time and O(1) space; (O(max(n, m)) space for result)
	public static LinkedListNode sumListsReverse(LinkedListNode n1, LinkedListNode n2) {
		LinkedListNode res = new LinkedListNode(-1);
		LinkedListNode curr = res;
		int sum = 0;
		int multiplier = 1;
		while (n1 != null || n2 != null || sum > multiplier) {
			if (n1 != null) {
				sum += multiplier * n1.data;
				n1 = n1.next;
			}
			if (n2 != null) {
				sum += multiplier * n2.data;
				n2 = n2.next;
			}
			curr.next = new LinkedListNode((sum % (10 * multiplier)) / multiplier);
			curr = curr.next;
			
			multiplier *= 10;
		}
		return res.next;
	}

	public static LinkedListNode sumListsReverseAlt(LinkedListNode n1, LinkedListNode n2) {
		int carry = 0;
		LinkedListNode res = new LinkedListNode(-1);
		LinkedListNode curr = res;
		while (n1 != null || n2 != null || carry > 0) {
			int sum = carry;
			if (n1 != null) {
				sum += n1.data;
				n1 = n1.next;
			}
			if (n2 != null) {
				sum += n2.data;
				n2 = n2.next;
			}
			curr.next = new LinkedListNode(sum % 10);
			curr = curr.next;

			carry = sum / 10;
 		}
 		return res.next;
	}
	
	// O(n + m) time and O(1) space (modifies n1 and n2)
	public static LinkedListNode sumListsForward(LinkedListNode n1, LinkedListNode n2) {
		return reverse(sumListsReverse(reverse(n1), reverse(n2)));
	}

	private static LinkedListNode reverse(LinkedListNode n) {
		if (n == null) return null;

		LinkedListNode res = n;
		LinkedListNode next = n.next;
		n.next = null;	
		n = next;
		while (n != null) {
			next = n.next;
			n.next = res;
			res = n;
			n = next;
		}
		return res;
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

	private static void tester(int x, int y, boolean isReversed) {
		LinkedListNode n1 = generateLinkedList(x);
		LinkedListNode n2 = generateLinkedList(y);
		System.out.println("n1: " + n1);
		System.out.println("n2: " + n2);
		if (isReversed)
			System.out.println("sumListsReverse(n1, n2): " + sumListsReverse(n1, n2));
		else
			System.out.println("sumListsForward(n1, n2): " + sumListsForward(n1, n2));
	}

	public static void main(String[] args) {
		tester(3, 3, false);
		System.out.println();

		tester(4, 2, false);
		System.out.println();

		tester(6, 9, true);
		System.out.println();
	}
}
