package com.issorted

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * 
 * Algorithm 1:
 * 
 * Divide the array into 2 parts, each of them should be sorted and
 * the last element of the first array must be <= first element of the
 * second array
 * 
 * Algorithm 2:
 * 
 * Let n = size of array
 * Consider all n elements, check if the last 2 elements of the array are sorted
 * If yes, consider n - 1 elements and do the same thing
 * 
 */

@CompileStatic
class IsSortedUsingRecursion {
	
	static boolean isSorted1(int[] arr) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Array cannot be null/empty")
		}
		return isSorted1(arr, 0, arr.length - 1) 
	}
	
	static boolean isSorted1(int[] arr, int lower, int upper) {
		if (lower == upper) {
			return true
		}
		if (upper - lower == 1) {
			return arr[lower] <= arr[upper]
		}
		int mid = (lower + upper) / 2 as int
		return (isSorted1(arr, lower, mid) 
			&& isSorted1(arr, mid + 1, upper) 
			&& arr[mid] <= arr[mid + 1])
	}
	
	static boolean isSorted2(int[] arr) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Array cannot be null/empty")
		}
		return isSorted2(arr, arr.length - 1)
	}
	
	static boolean isSorted2(int[] arr, int last) {
		if (last == 0) {
			return true
		}
		return (arr[last - 1] <= arr[last]
			&& isSorted2(arr, last - 1))
	}
	
	public static void main(String[] args) {
		assert isSorted1([1, 2, 3, 4, 5, 6] as int[])
		assert isSorted1([1, 2, 3, 3, 4, 5, 6] as int[])
		assert !isSorted1([1, 2, 45, 3, 4, 5] as int[])
		assert isSorted1([1] as int[])
		
		assert isSorted2([1, 2, 3, 4, 5, 6] as int[])
		assert isSorted2([1, 2, 3, 3, 4, 5, 6] as int[])
		assert !isSorted2([1, 2, 45, 3, 4, 5] as int[])
		assert isSorted2([1] as int[])
		println ('Done')
	}
	
}