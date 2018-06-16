/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.1 Is Unique
 * Desc: Implement an algorithm to determine if a string has all unique 
 * characters. What if you cannot use additional data structures?
 *
 * Author: Aaron Bargotta
 * Date: 06/09/18
 */

import java.util.*;

// n := number of chars in the string
public class IsUnique {
	// O(n) time and O(n) space
	public static boolean isUnique(String s) {
		HashSet<Character> freq = new HashSet<Character>();
		for (char c : s.toCharArray()) {
			if (freq.contains(c)) return false;
			else freq.add(c);
		}
		return true;
	}

	// O(n log(n)) and O(1) space
	public static boolean isUniqueNoDS(String s) {
		char[] cs = s.toCharArray();
		Arrays.sort(cs);
		for (int i = 1; i < cs.length; i++)
			if (cs[i - 1] == cs[i]) return false;
		return true;
	}

	public static void main(String[] args) {
		String unique = "abcdef";
		String notUnique = "abcdcf";
		System.out.println(unique + " : " + isUnique(unique));
		System.out.println(notUnique + " : " + isUnique(notUnique));
		System.out.println();

		System.out.println(unique + " : " + isUniqueNoDS(unique));
		System.out.println(notUnique + " : " + isUniqueNoDS(notUnique));
	}
}
