/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.9 String Rotation
 * Desc: Assume you have a method isSubstring which checks if one word is a
 * substring of another. Given two strings, s1 and s2, write code to check
 * if s2 is a rotation of s1 using only one call to isSubstring (e.g.,
 * "waterbottle" is a rotation of "erbottlewat").
 *
 * Author: Aaron Bargotta
 * Date: 06/10/18
 */

import java.util.*;

public class StringRotation {
	public static boolean stringRotation(String s1, String s2) {
		if (s1.length() == 0 || s1.length() != s2.length()) return false;
		return isSubstring(s1 + s1, s2);
	}

	private static boolean isSubstring(String s1, String s2) {
		return s1.contains(s2);
	}

	private static void tester(String s1, String s2) {
		System.out.println("stringRotation('" + s1 + "', '" + s2 + "') = " +
			stringRotation(s1, s2));
	}

	public static void main(String[] args) {
		tester("waterbottle", "erbottlewat");
		tester("waterbottle", "erbottlewar");
	}
}
