/**
 * Cracking the Coding Interview Chapter 1: Arrays and Strings
 * Interview Question: 1.7 Rotate Matrix
 * Desc: Given an image represented by an NxN matrix, where each pixel in the
 * image is 4 bytes, write a method to rotate the image by 90 degrees. Can
 * you do this in place?
 *
 * Author: Aaron Bargotta
 * Date: 06/10/18
 */

import java.util.*;

// n := total # of elements in the array
public class RotateMatrix {
	// O(n) time and space
	public static int[][] rotateMatrix(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] res = new int[cols][rows];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				res[c][rows - r - 1] = matrix[r][c];
			}
		}
		return res;
	}

	// O(n) time and O(1) space
	public static int[][] rotateMatrixInPlace(int[][] matrix) {
		for (int i = 0; i < matrix.length / 2; i++) {
			rotateLayer(i, matrix);
		}
		return matrix;
	}

	private static void rotateLayer(int layer, int[][] matrix) {
		int first = layer;
		int last = matrix.length - layer - 1;
		for (int i = first; i < last; i++) {
			int offset = i - first;
			int temp = matrix[first][i];
			// left -> top
			matrix[first][i] = matrix[last - offset][first];
			// bottom -> left
			matrix[last - offset][first] = matrix[last][last - offset];
			// right -> bottom
			matrix[last][last - offset] = matrix[i][last];
			// top -> right
			matrix[i][last] = temp;
		}
	}

	private static void fillMatrix(int[][] matrix) {
		int count = 0;
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				matrix[r][c] = count++;
			}
		}
	}

	private static String sexyPrint(int[][] matrix) {
		return Arrays.deepToString(matrix).replaceAll("], ", "]\n ");
	}

	private static void tester(int[][] matrix, boolean inPlace) {
		if (inPlace) {	
			System.out.println("rotateMatrixInPlace(matrix):");
			System.out.println(sexyPrint(rotateMatrixInPlace(matrix)));
		}
		else {
			System.out.println("rotateMatrix(matrix):");
			System.out.println(sexyPrint(rotateMatrix(matrix)));
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[9][9];
		fillMatrix(matrix);

		System.out.println("matrix:");
		System.out.println(sexyPrint(matrix));
		System.out.println();

		tester(matrix, false);
		System.out.println();

		fillMatrix(matrix);
		tester(matrix, true);
		System.out.println();
	}
}
