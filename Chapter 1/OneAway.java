/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.5 One Away
 * Desc: There are three types of edits that can be performed on strings:
 * insert a character, remove a character, or replace a character. Given two
 * strings, write a function to check if they are one edit (or zero edits)
 * away.
 *
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 *
 * Author: Aaron Bargotta
 * Date: 06/10/18
 */

import java.util.*;

// n := # chars in s1; m := # chars in s2
public class OneAway {
	// O(max{n, m}) time and space => O(n) time and space
	public static boolean oneAway(String s1, String s2) {
		boolean oneInsertOrRemove = false;
		boolean oneReplace = false;
		Hashtable<Character, Integer> freqs = new Hashtable<Character, Integer>();

		for (char c : s1.toCharArray()) {
			int cFreq = freqs.getOrDefault(c, 0) + 1;
			freqs.put(c, cFreq);
		}

		for (char c : s2.toCharArray()) {
			int cFreq = freqs.getOrDefault(c, 0) - 1;
			freqs.put(c, cFreq);	
		}

		int lenDiff = Math.abs(s1.length() - s2.length());
		if (lenDiff == 0)
			oneReplace = oneReplaceAway(freqs);
		else if (lenDiff == 1)
			oneInsertOrRemove = oneInsertOrRemoveAway(freqs);
		else return false;

		return s1.equals(s2) || oneInsertOrRemove || oneReplace;
	}

	private static boolean oneReplaceAway(Hashtable<Character, Integer> freqs) {
		int sum = 0;
		int oneCount = 0;
		int nonZeroCount = 0;
		for (int v : freqs.values()) {
			sum += v;
			if (v != 0) nonZeroCount++;
			if (v == 1) oneCount++;

		}
		return sum == 0 && oneCount == 1 && nonZeroCount == 2;
	}

	private static boolean oneInsertOrRemoveAway(Hashtable<Character, Integer> freqs) {
		int nonZeroCount = 0;
		int sum = 0;
		for (int v : freqs.values()) {
			sum += v;
			if (v != 0) nonZeroCount++;
		}
		return nonZeroCount == 1 && (sum == 1 || sum == -1);
	}

	private static void tester(String s1, String s2) {
		System.out.println("oneAway('" + s1 + "', '" + s2 + "') = " + 
			oneAway(s1, s2));	
	}

	public static void main(String[] args) {
		tester("pale", "ple");
		tester("pales", "pale");
		tester("pale", "bale");
		tester("pale", "bake");
	}
}
