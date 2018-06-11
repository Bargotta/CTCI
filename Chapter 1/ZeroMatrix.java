/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.8 Zero Matrix
 * Desc: Write an algorithm such that if an element in an MxN matrix is zero,
 * its entire row and column are set to zero.
 *
 * Author: Aaron Bargotta
 * Date: 06/10/18
 */

import java.util.*;

public class ZeroMatrix {
	// O(MN) time and O(max(N, M)) space
	public static boolean zeroMatrix(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return false;

		HashSet<Integer> rowsToChange = new HashSet<Integer>();
		HashSet<Integer> colsToChange = new HashSet<Integer>();

		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				if (matrix[r][c] == 0) {
					rowsToChange.add(r);
					colsToChange.add(c);
				}
			}
		}
		// set rows to zero
		for (int r : rowsToChange) {
			for (int c = 0; c < matrix[0].length; c++) {
				matrix[r][c] = 0;
			}
		}
		// set cols to zero
		for (int c : colsToChange) {
			for (int r = 0; r < matrix.length; r++) {
				matrix[r][c] = 0;
			}
		}
		return true;
	}

	// O(MN) time and O(1) space
	public static boolean zeroMatrixInPlace(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return false;
		boolean nullifyFirstRow = false;
		boolean nullifyFirstCol = false;
		int rows = matrix.length;
		int cols = matrix[0].length;

		// check if first row or col have zeros
		for (int r = 0; r < rows; r++) {
			if (matrix[r][0] == 0) nullifyFirstCol = true;
		}
		for (int c = 0; c < cols; c++) {
			if (matrix[0][c] == 0) nullifyFirstRow = true;
		}

		// check rest of matrix for zeros
		for (int r = 1; r < rows; r++) {
			for (int c = 1; c < cols; c++) {
				if (matrix[r][c] == 0) {
					matrix[r][0] = 0;
					matrix[0][c] = 0;
				}
			}
		}

		// set rows and cols to zero based on values from first row and col
		for (int r = 1; r < rows; r++) {
			if (matrix[r][0] == 0) setRowToZero(matrix, r);
		}
		for (int c = 1; c < cols; c++) {
			if (matrix[0][c] == 0) setColToZero(matrix, c);
		}

		// nullify first row and col if necessary
		if (nullifyFirstRow) setRowToZero(matrix, 0);
		if (nullifyFirstCol) setColToZero(matrix, 0);

		return true;
	}

	private static void setRowToZero(int[][] matrix, int r) {
		for (int c = 0; c < matrix[0].length; c++) {
			matrix[r][c] = 0;
		}
	}

	private static void setColToZero(int[][] matrix, int c) {
		for (int r = 0; r < matrix.length; r++) {
			matrix[r][c] = 0;
		}

	}

	private static void fillMatrix(int[][] matrix) {
		int count = 0;
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				if (Math.random() < 0.1) matrix[r][c] = 0;
				else matrix[r][c] = count;
				count++;
			}
		}
	}

	private static String sexyPrint(int[][] matrix) {
		return Arrays.deepToString(matrix).replaceAll("], ", "]\n ");
	}

	private static void tester(int[][] matrix, boolean inPlace) {
		System.out.println("matrix:");
		System.out.println(sexyPrint(matrix));
		System.out.println();

		if (inPlace) {
			System.out.println("zeroMatrixInPlace(matrix):");
			zeroMatrixInPlace(matrix);
			System.out.println(sexyPrint(matrix));
		}
		else {	
			System.out.println("zeroMatrix(matrix):");
			zeroMatrix(matrix);
			System.out.println(sexyPrint(matrix));
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[3][5];

		fillMatrix(matrix);
		tester(matrix, false);
		System.out.println();

		fillMatrix(matrix);
		tester(matrix, true);
		System.out.println();
	}
}