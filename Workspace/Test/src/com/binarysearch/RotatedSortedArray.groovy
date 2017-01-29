package com.binarysearch

/**
 * @author Ameya
 * This program takes as input an array of distinct integers that is sorted
 * in ascending order and rotated(may or may not be)
 * e.g.
 * 4 5 6 7 1 2 3 -> rotated form of 1 2 3 4 5 6 7
 * The aim is to search for an element in this array and do better than O(n)
 * Output will be the index of the element if found else -1
 *
 * Solution:
 * Find the point of rotation in the array using modified binary search and then depending
 * on the element, perform binary search on 1 part of the rotated array
 * 0 to point of rotation or point of rotation to end
 * This way the element can be searched in O(log(n)) + O(log(n))
 *
 */
class RotatedSortedArray {

    static int search(int[] arr, int elem) {
        int arrLength = arr.length;
        // 1. null or empty
        if(arr == null || arrLength == 0) {
            return -1
        }
        // 2. length 1
        if(arrLength == 1) {
            return arr[0] == elem ? 0 : -1
        }
        // 3. check if not rotated(or rotated so that back to original order)
        if(arr[0] < arr[arrLength - 1]) {
            return binarySearch(arr, 0, arrLength - 1, elem)
        }
        // 4. rotated
        int pointOfRotation = searchPointOfRotation(arr, 0, arrLength-1)
        if(elem == arr[pointOfRotation]) {
            return pointOfRotation
        }
        else if (elem > arr[0]) {
            return binarySearch(arr, 0, pointOfRotation-1, elem)
        }
        else {
            return binarySearch(arr, pointOfRotation+1, arrLength-1, elem)
        }
                
    }

    private static int searchPointOfRotation(int[] arr, int start, int end) {
        int mid = (start + end)/2
        if(arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]) {
            return mid
        }
        if(arr[mid] < arr[start]) {
            return searchPointOfRotation(arr, start, mid-1)
        }
        else {
            return searchPointOfRotation(arr, mid+1, end)
        }
    }

    private static int binarySearch(int[] arr, int start, int end, int elem) {
        if(start > end) {
            return -1
        }
        int mid = (start + end)/2
        if(arr[mid] == elem) {
            return mid
        }
        if(arr[mid] < elem) {
            binarySearch(arr, mid+1, end, elem)
        }
        else {
            binarySearch(arr, start, mid-1, elem)
        }
    }
}

class RotatedSortedArrayTest {
    static void main(String[] args) {
        assert 2 == RotatedSortedArray.search([14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13] as int[], 16)
        assert -1 == RotatedSortedArray.search([14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13] as int[], 18)
        assert 7 == RotatedSortedArray.search([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13] as int[], 8)
        assert -1 == RotatedSortedArray.search([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13] as int[], 18)
    }
}
