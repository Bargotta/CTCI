/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.4 Palindrome Permutation
 * Desc: Given a string, write a function to check if it is a permutation of 
 * a palindrome. A palindrome is a word or phrase that is the same forwards
 * and backwards. A permutation is a rearrangement of letters. The palindrome
 * does not need to be limited to just dictionary words.
 *
 * EXAMPLE
 * Input:  Tact Coa
 * Output: True (permutations: "taco cat", "atcocta", etc.)
 *
 * Author: Aaron Bargotta
 * Date: 06/10/18
 */

import java.util.*;

// n := # chars in s
public class PalindromePermutation {
	// O(n) space and O(n) time
	public static boolean palindromePermutation(String s) {
		HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
		
		for (char c : s.toCharArray()) {
			if (c != ' ') {
				char lowerCaseC = Character.toLowerCase(c);
				int cFreq = freqs.getOrDefault(lowerCaseC, 0) + 1;
				freqs.put(lowerCaseC, cFreq);				
			}
		}

		int oddFreqCount = 0;
		for (int v : freqs.values()) {
			if (v % 2 != 0) oddFreqCount++;
		}

		return (oddFreqCount <= 1);
	}

	public static void main(String[] args) {
		String input = "Tact Coa";
		System.out.println("palindromePermutation('" + input + "') = " + 
			palindromePermutation(input));

		String input2 = "Tact Coad";
		System.out.println("palindromePermutation('" + input2 + "') = " + 
			palindromePermutation(input2));

	}
}