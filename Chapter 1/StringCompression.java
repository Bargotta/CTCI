/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.6 String Compression
 * Desc: Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become
 * a2b1c5a3. If the "compressed" string would not become smaller than the
 * original string, your method should return the original string. You can
 * assume the string has only uppercase and lowercase letters (a - z).
 *
 * Author: Aaron Bargotta
 * Date: 06/10/18
 */

import java.util.*;

// n := # chars in s
public class StringCompression {
	// O(n) time and space
	public static String stringCompression(String s) {
		StringBuilder res = new StringBuilder();
		char prev = s.charAt(0);
		int count = 0;
		for (char c : s.toCharArray()) {
			if (prev == c) count++;
			else {
				res.append(prev);
				res.append(count);
				prev = c;
				count = 1;
			}
		}
		res.append(prev);
		res.append(count);
		return (res.length() < s.length()) ? res.toString() : s;
	}

	private static void tester(String s1) {
		System.out.println("stringCompression('" + s1 + "') = " +
			stringCompression(s1));
	}

	public static void main(String[] args) {
		tester("aabcccccaaa");
		tester("abcd");
	}
}