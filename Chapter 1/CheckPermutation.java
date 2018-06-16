/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.2 Check Permutation
 * Desc: Given two strings, write a method to decide if one is a permutation
 * of the other.
 *
 * Author: Aaron Bargotta
 * Date: 06/09/18
 */

import java.util.*;

// n := # chars in s1; m := # chars in s2
public class CheckPermutation {
	// O(n log(n)) time and O(1) space
	public static boolean checkPermutation(String s1, String s2) {
		if (s1.length() != s2.length()) return false;

		char[] cs1 = s1.toCharArray();
		char[] cs2 = s2.toCharArray();
		Arrays.sort(cs1);
		Arrays.sort(cs2);
		for (int i = 0; i < cs1.length; i++)
			if (cs1[i] != cs2[i]) return false;
		return true;
	}

	// O(n) time and O(n) space
	public static boolean checkPermutation2(String s1, String s2) {
		if (s1.length() != s2.length()) return false;

		HashMap<Character, Integer> s1Freq = new HashMap<Character, Integer>();
		for (char c : s1.toCharArray()) {
			int cFreq = s1Freq.getOrDefault(c, 0) + 1;
			s1Freq.put(c, cFreq);
		}

		for (char c : s2.toCharArray()) {
			int cFreq = s1Freq.getOrDefault(c, 0) - 1;
			s1Freq.put(c, cFreq);
		}

		for (Integer v : s1Freq.values()) {
			if (v != 0) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String s1 = "bookbub";
		String s2 = "kobubob";
		System.out.println("checkPermutation('" + s1 + "','" + s2 + "') = " +
			checkPermutation(s1, s2));
		System.out.println("checkPermutation2('" + s1 + "','" + s2 + "') = " +
			checkPermutation2(s1, s2));
		System.out.println();

		String s3 = "bookbub";
		String s4 = "kobubub";
		System.out.println("checkPermutation('" + s3 + "','" + s4 + "') = " +
			checkPermutation(s3, s4));
		System.out.println("checkPermutation2('" + s3 + "','" + s4 + "') = " +
			checkPermutation2(s3, s4));
	}

