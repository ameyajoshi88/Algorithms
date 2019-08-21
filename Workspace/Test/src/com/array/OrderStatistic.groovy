package com.array

/**
 * @author Ameya
 * This program takes as input an array of integers and returns the kth minimum
 * integer
 * e.g arr = [11, 2, 0, 7, 19, 20]; k = 3 => 7
 * Reference: https://www.youtube.com/watch?v=D35llNtkCps
 */
class OrderStatistic {

  static int orderStatistic(int[] arr, int k) {
    if (arr == null || arr.length < k) {
      throw new IllegalArgumentException("Invalid input");
    }
    orderStatistic(arr, k, 0, arr.length - 1);
  }

  static int orderStatistic(int[] arr, int k, int start, int end) {
    int mid = arr[start]
    int[] arr2 = new int[end - start + 1]
    int p1 = 0
    int p2 = end - start
    for (int i = start + 1; i <= end; i++) {
      if (arr[i] < mid) {
        arr2[p1++] = arr[i]
      }
      else {
        arr2[p2--] = arr[i]
      }
    }
    arr2[p1] = arr[start]
    int midPos = p1 // or p2
    int kIndex = k - 1
    if (kIndex < midPos) {
      return orderStatistic(arr2, k, 0, midPos - 1)
    }
    else if (kIndex > midPos) {
      return orderStatistic(arr2, k - midPos - 1, midPos + 1, arr2.length - 1)
    }
    return mid
  }

  static void main(String[] args) {
    OrderStatistic os = new OrderStatistic()
    assert 7 == os.orderStatistic([11, 2, 0, 7, 19, 20] as int[], 3)
    assert 10 == os.orderStatistic([7, 10, 4, 3, 20, 15] as int[], 4)
    try {
      os.orderStatistic(null, 4)
    }
    catch (IllegalArgumentException e) {
      assert "Invalid input" == e.message
    }
    try {
      os.orderStatistic([7, 10] as int[], 4)
    }
    catch (IllegalArgumentException e) {
      assert "Invalid input" == e.message
    }
  }
}