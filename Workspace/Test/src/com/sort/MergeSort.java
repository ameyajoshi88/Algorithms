package com.sort;

public class MergeSort {
	
	private static void mergesort(int[] arr, int i, int j) {
		if(i == j) {
			return;
		} else {
			int mid = (i + j)/2;
			mergesort(arr, i, mid);
			mergesort(arr, mid + 1, j);
			merge(arr, i, j, mid);
			
		}
	}

	private static void merge(int[] arr, int i, int j, int mid) {
		int p1 = i;
		int p2 = mid + 1;
		int temp[] = new int[arr.length];
		int count = i;
		
		// when both parts are available
		while(p1 <= mid && p2 <= j) {
			if(arr[p1] <= arr[p2]) {
				temp[count++] = arr[p1++];
			} else {
				temp[count++] = arr[p2++];
			}
		}
		
		// when first part is exhausted
		if(p1 > mid) {
			while(p2 <= j) {
				temp[count++] = arr[p2++];
			}
		}
		
		// when second part is exhausted
		if(p2 > j) {
			while(p1 <= mid) {
				temp[count++] = arr[p1++];
			}
		}
		
		// copy back the merged elements
		for(count = i; count <= j; count++) {
			arr[count] = temp[count];
		}
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
		mergesort(arr, 0, arr.length - 1);
		System.out.println("\nAfter Sorting:");
		print(arr);
	}
}
