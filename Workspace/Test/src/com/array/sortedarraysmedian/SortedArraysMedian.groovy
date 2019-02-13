package com.array.sortedarraysmedian

import com.linkedlist.Node

import groovy.transform.CompileStatic

/**
 * @author Ameya
 *
 * @see <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">Problem</a>
 *
 * e.g. 1
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * 
 * e.g. 2
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 * 
 */

@CompileStatic
class SortedArraysMedian {
  
  SortedArraysMedian(int[] nums1, int[] nums2) {
    this.nums1 = nums1
    this.nums2 = nums2
  }
  
  int[] nums1
  int[] nums2
  
  double median() {
    if (nums1.length == 0) {
      return median(nums2)
    }
    if (nums2.length == 0) {
      return median(nums1)
    }
    int nums1Pointer = 0
    int nums2Pointer = 0
    int size = nums1.length + nums2.length
    int mid = size / 2 as int
    // Since both arrays are sorted, we just need to 'mid' number of elements
    // of the merged array
    int[] merged = new int[mid + 1]
    for (int i = 0; i < mid + 1; i++) {
      if (nums1Pointer == nums1.length) { // exhausted nums1
        merged[i] = nums2[nums2Pointer++]
      }
      else if (nums2Pointer == nums2.length) { // exhausted nums2
        merged[i] = nums1[nums1Pointer++]
      }
      else if (nums1[nums1Pointer] <= nums2[nums2Pointer]) {
        merged[i] = nums1[nums1Pointer++]
      }
      else {
        merged[i] = nums2[nums2Pointer++]
      }
    }
    if (size % 2 == 0) {
      return (merged[mid] + merged[mid - 1]) / 2.0
    }
    else {
      return merged[mid]
    }
  }
  
  double median(int[] nums) {
    int size = nums.length
    if (size % 2 == 0) {
      int mid = size / 2 as int
      return (nums[mid] + nums[mid - 1]) / 2.0
    }
    else {
      return nums[size / 2 as int]
    }
  }
  
  static void main(String[] args) {
    int[] nums1 = [1, 3]
    int[] nums2 = [2]
    SortedArraysMedian sortedArraysMedian = new SortedArraysMedian(nums1, nums2)
    assert 2.0 == sortedArraysMedian.median()
    nums1 = [1, 2]
    nums2 = [3, 4]
    sortedArraysMedian = new SortedArraysMedian(nums1, nums2)
    assert 2.5 == sortedArraysMedian.median()
    nums1 = [1]
    nums2 = [2]
    sortedArraysMedian = new SortedArraysMedian(nums1, nums2)
    assert 1.5 == sortedArraysMedian.median()
  }
  
}
