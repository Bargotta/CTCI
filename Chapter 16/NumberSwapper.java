/**
 * Cracking the Coding Interview Chapter 16: Moderate
 * Interview Question: 16.1 Number Swapper
 * Desc: Write a function to swap a number in place (that is, without temporary
 * variables).
 *
 * Author: Aaron Bargotta
 * Date: 07/16/18
 */

public class NumberSwapper {
	public static void numberSwapper(int a, int b) {
		System.out.println("BEFORE: a = " + a + ", b = " + b);
		a = a - b;
		b = a + b;
		a = b - a;
		System.out.println("AFTER: a = " + a + ", b = " + b);
	}

	public static void numberSwapperXOR(int a, int b) {
		System.out.println("BEFORE: a = " + a + ", b = " + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("AFTER: a = " + a + ", b = " + b);
	}

	public static void main(String[] args) {
		numberSwapper(10, 38);
		numberSwapperXOR(10, 38);
		numberSwapper(-5, 0);
		numberSwapperXOR(-5, 0);
	}
}