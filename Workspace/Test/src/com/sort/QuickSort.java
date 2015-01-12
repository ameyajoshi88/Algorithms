package com.sort;

public class QuickSort {
	private static void quickSort(int[] arr, int start, int end) {
		if(start >= end) {
			return;
		} else {
			int pIndex = partition(arr, start, end);
			quickSort(arr, start, pIndex - 1);
			quickSort(arr, pIndex + 1, end);
		}
	}
	
	private static int partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		int pIndex = start;
		
		for(int i=start; i<end; i++) {
			if(arr[i] <= pivot) {
				swap(arr, i, pIndex);
				pIndex++;
			}
		}
		
		swap(arr, pIndex, end);
		return pIndex;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void print(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void main(String args[]) {
		int arr[] = new int[]{20, 10, 5, 12, 6};
		
		System.out.println("Before Sorting:");
		print(arr);
		quickSort(arr, 0, arr.length - 1);
		System.out.println("\nAfter Sorting:");
		print(arr);
	}
}
