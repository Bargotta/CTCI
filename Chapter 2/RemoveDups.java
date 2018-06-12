/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.1 Remove Dups
 * Desc: Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed.
 *
 * Author: Aaron Bargotta
 * Date: 06/11/18
 */

import java.util.*;

// n := # nodes in linked list
public class RemoveDups {
	private static class Node {
		private int val;
		private Node next;

		public Node(int v) {
			val = v;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(val);

			Node curr = next;
			while (curr != null) {
				sb.append(" ");
				sb.append(curr.val);
				curr = curr.next;
			}
			return sb.toString();
		}
	}

	// O(n) time and space
	public static Node removeDups(Node head) {
		if (head == null) return head;
		
		Node curr = head;
		HashSet<Integer> vals = new HashSet<Integer>();
		vals.add(curr.val);

		while (curr.next != null) {
			if (vals.contains(curr.next.val)) {
				curr.next = curr.next.next;
			}
			else {			
				vals.add(curr.next.val);
				curr = curr.next;
			}
		}

		return head;
	}

	// O(n^2) time and O(1) space
	public static Node removeDupsInPlace(Node head) {
		if (head == null) return head;

		Node curr = head;
		while (curr != null) {
			Node runner = curr;
			while (runner.next != null) {			
				if (curr.val == runner.next.val) {
					runner.next = runner.next.next;
				}
				else {
					runner = runner.next;
				}
			}
			curr = curr.next;
		}

		return head;
	}

	private static Node generateLinkedList(int n) {
		Random r = new Random();
		Node head = new Node(r.nextInt(n));
		Node curr = head;

		int count = 1;
		while (count++ < n) {
			curr.next = new Node(r.nextInt(n));
			curr = curr.next;
		}

		return head;
	}

	private static void tester(int n, boolean inPlace) {
		Node head = generateLinkedList(n);
		System.out.println("Linked List:");
		System.out.println(head);

		if (inPlace) {
			System.out.println("removeDupsInPlace(head):");
			System.out.println(removeDupsInPlace(head));
		}
		else {	
			System.out.println("removeDups(head):");
			System.out.println(removeDups(head));
		}
	}

	public static void main(String[] args) {
		tester(10, false);
		System.out.println();

		tester(10, true);
		System.out.println();
	}
}
