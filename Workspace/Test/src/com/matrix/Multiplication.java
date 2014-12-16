package com.matrix;

/**
 * @author Ameya
 * Matrix Multiplication
 * Assumption: 
 * Multiplication is possible
 * i.e number of columns of matrix1 = number of rows of matrix2
 */
public class Multiplication {
	
	private static int[][] multiply(int[][] m1, int[][] m2) {
		int m1Rows = m1.length;
		int m2Rows = m2.length;
		int m2Cols = m2[0].length;
		
		// result will contain the multiplication
		int[][] result = new int[m1Rows][m2Cols];
		
		for(int i=0; i<m1Rows; i++) {
			for(int k=0; k<m2Cols; k++) {
				int sum = 0;
				for(int j=0; j<m2Rows; j++) {
					sum += m1[i][j] * m2[j][k];
				}
				result[i][k] = sum;
			}
		}
		
		return result;
	}
	
	private static void display(int[][] m) {
		int rows = m.length;
		int cols = m[0].length;
		
		for(int i=0; i<rows; i++) {
			System.out.print("\n");
			for(int j=0; j<cols; j++) {
				System.out.print(m[i][j] + " ");
			}
		}
		
		System.out.println("\n");
	}
	
	public static void main(String args[]) {
		int[][] m1 =  new int[2][3];
		int[][] m2 =  new int[3][2];
		
		m1[0][0] = 1;
		m1[0][1] = 0;
		m1[0][2] = -2;
		
		m1[1][0] = 0;
		m1[1][1] = 3;
		m1[1][2] = -1;
		
		m2[0][0] = 0;
		m2[0][1] = 3;
		
		m2[1][0] = -2;
		m2[1][1] = -1;
		
		m2[2][0] = 0;
		m2[2][1] = 4;
		
		System.out.println("\nMatrix 1 =");
		display(m1);
		System.out.println("\nMatrix 2 =");
		display(m2);
		System.out.println("\nResult =");
		display(multiply(m1, m2));
		
	}
}
