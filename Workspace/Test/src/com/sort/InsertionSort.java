package com.sort;

public class InsertionSort {
	
	private static int[] sort(int[] input) {
		int swapCount = 0;
		for(int i=1; i<input.length; i++) {
			for(int j=i; j>0; j--) {
				if(input[j] < input[j-1]) {
					input = swap(j, j-1, input);
					swapCount++;
				} else {
					break;
				}
			}
		}
		System.out.println(swapCount);
		return input;
	}
	
	private static int[] swap(int i, int j, int[] input) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
		
		return input;
	}
	
	private static void print(int[] input) {
		System.out.print("\n");
		for(int i=0; i<input.length; i++) {
			System.out.print(input[i] + " ");
		}
	}
	
	public static void main(String args[]) {
		int[] input = new int[]{3, 1, 7, 4, 6, 2};
		int[] output = sort(input);
		
		print(output);
	}
}
