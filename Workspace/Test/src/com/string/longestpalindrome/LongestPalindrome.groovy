package com.string.longestpalindrome

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * 
 * This program takes a string as input and returns the longest palindromic
 * substring in it.
 * 
 * Reference: https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 */

@CompileStatic
class LongestPalindrome {
  
  public LongestPalindrome(String s) {
    this.s = s
  }

  String s
  
  String longestPalindrome() {
    if (s == null || s.isEmpty()) {
      return s
    }
    char[] input = s.toCharArray()
    int n = input.length
    int start = 0
    int maxLength = 1
    boolean[][] arr = new boolean[n][n]
    // 1. substrings of length 1 are all palindromes
    for (int i = 0; i < n; i++) {
      arr[i][i] = true
    }
    // 2. substrings of length 2
    for (int i = 0; i < n - 1; i++) {
      if (input[i] == input[i + 1]) {
        arr[i][i + 1] = true
        start = i
        maxLength = 2
      }
    }
    // 3. lengths 3 and above
    int j
    for (int k = 3; k <= n; k++) {
      for (int i = 0; i <= n - k; i ++) {
        j = i + k - 1
        if (input[i] == input[j] && arr[i + 1][j - 1]) {
          arr[i][j] = true
          start = i
          maxLength = k
        }
      }
    }
    return s.substring(start, start + maxLength)
  }
  
  static void main(String[] args) {
    LongestPalindrome lp = new LongestPalindrome('b')
    assert 'b' == lp.longestPalindrome()
    lp = new LongestPalindrome('baab')
    assert 'baab' == lp.longestPalindrome()
    lp = new LongestPalindrome('abaabd')
    assert 'baab' == lp.longestPalindrome()
    lp = new LongestPalindrome('forgeeksskeegfor')
    assert 'geeksskeeg' == lp.longestPalindrome()
  }
}
