/**
 * Cracking the Coding Interview Chapter 2: Linked Lists
 * Interview Question: 2.6 Palindrome
 * Desc: Implement a function to check if a linked list is a palindrome.
 *
 * Author: Aaron Bargotta
 * Date: 06/14/18
 *
 * Notes: Several different approaches:
 * 1. O(n) time and O(1) space: 
 *  - Find the center of the linked list and reverse. Then, compare the two halves.
 * 2. O(n) time and space: 
 *  - Ensure there is only one set of characters with an odd count.
 *  - Push everything onto a stack, then traverse the linked list again popping 
 *    elements from the stack and comparing.
 */

import java.util.*;

// n := # of nodes in n
public class Palindrome {
	private static class LinkedListNode {
		private char data;
		private LinkedListNode next;

		public LinkedListNode(char c) {
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

	// O(n) time and O(n) space
	public static boolean palindrome(LinkedListNode n) {
		Hashtable<Character, Integer> letterCount = new Hashtable<Character, Integer>();
		int oddCount = 0;
		while (n != null) {
			char c = n.data;
			int count = letterCount.getOrDefault(c, 0) + 1;
			letterCount.put(c, count);
			n = n.next;
		}

		for (int v : letterCount.values()) {
			if (v % 2 == 1) oddCount++;
		}

		return (oddCount <= 1);
	}

	// O(n) time and O(1) space
	public static boolean palindromeInPlace(LinkedListNode n) {
		LinkedListNode m = split(n);
		LinkedListNode n2 = reverse(m);
		while (n2 != null) {
			if (n2.data != n.data)
				return false;
			n = n.next;
			n2 = n2.next;
		}
		return true;
	}

	private static LinkedListNode split(LinkedListNode n) {
		if (n == null || n.next == null) return n;

		LinkedListNode zero = new LinkedListNode('\0');
		zero.next = n;
		LinkedListNode slow = zero;
		LinkedListNode fast = zero;
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast.next == null) break;
			fast = fast.next;
		}
		LinkedListNode mid = slow.next;
		slow.next = null;
		return mid;
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

	private static LinkedListNode stringToLinkedList(String s) {
		LinkedListNode res = new LinkedListNode('\0');
		LinkedListNode curr = res;
		for (char c : s.toCharArray()) {
			curr.next = new LinkedListNode(c);
			curr = curr.next;
		}
		return res.next;
	}

	private static void tester(String s, boolean inPlace) {
		LinkedListNode n = stringToLinkedList(s);
		System.out.println("n: " + n);
		if (inPlace)
			System.out.println("palindrome('" + s + "'): " + palindrome(n));
		else
			System.out.println("palindromeInPlace('" + s + "'): " + palindromeInPlace(n));
	}

	public static void main(String[] args) {
		tester("abcdefedcba", false);
		System.out.println();

		tester("abcdefedcba", true);
		System.out.println();

		tester("a", false);
		System.out.println();

		tester("a", true);
		System.out.println();

		tester("aabaa", false);
		System.out.println();

		tester("aabaa", true);
		System.out.println();

		tester("aabca", false);
		System.out.println();

		tester("aabca", true);
		System.out.println();
	}
}
