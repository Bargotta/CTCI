/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.3 URLify
 * Desc: Write a method to replace all spaces in a string with '%20'. You may
 * assume that the string has sufficient space at the end to hold the
 * additional characters, and that you are given the "true" length of the
 * string.
 *
 * EXAMPLE:
 * Input:  "Mr John Smith    ", 13
 * Output: "Mr%20John%20Smith"
 *
 * Author: Aaron Bargotta
 * Date: 06/10/18
 */

import java.util.*;

public class URLify {
	public static String urlify(String s, int len) {
		char[] cs = s.toCharArray();
		int last = len - 1;
		int trueLast = s.length() - 1;

		while (last >= 0) {
			if (cs[last] == ' ') {
				replaceSpace(cs, trueLast);
				trueLast -= 3;
			}
			else {
				cs[trueLast] = cs[last];
				trueLast--;
			}
			last--;
		}
		return new String(cs);
	}

	private static void replaceSpace(char[] cs, int i) {
		cs[i - 2] = '%';
		cs[i - 1] = '2';
		cs[i] 	  = '0';
	}

	public static void main(String[] args) {
		String input = "Mr John Smith    ";
		System.out.println("URLify('" + input + "', 13) = '" + 
			urlify(input, 13) + "'");
	}
}
