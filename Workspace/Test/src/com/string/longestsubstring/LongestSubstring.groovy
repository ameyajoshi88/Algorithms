package com.string.longestsubstring

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * 
 * @see <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters">Problem</a>
 * 
 * Input: abcabcbb
 * Output: abc 
 */

@CompileStatic
class LongestSubstring {

  LongestSubstring(String input) {
    this.input = input
  }

  String input

  String longestSubString() {
    if (input == null) {
      return null
    }
    Set<Character> seen = new HashSet<>()
    char[] charArray = input.toCharArray()
    String result = ''
    String soFar = ''
    for (int i = 0; i < charArray.length; i++) {
      char character = charArray[i]
      if (!seen.contains(character)) {
        soFar += character
        seen << character
      }
      else {
        // Update
        result = updateResult(soFar, result) ? soFar : result
        // Reset (slide the window forward by 1)
        seen.remove(soFar.charAt(0))
        soFar = soFar.substring(1)
        i--
      }
    }
    // Update - Trailing characters could be the result
    result = updateResult(soFar, result) ? soFar : result
    return result
  }
  
  private boolean updateResult(String soFar, String result) {
    return !soFar.isEmpty() && soFar.length() > result.length()
  }

  static void main(String[] args) {
    LongestSubstring longestSubstring1 = new LongestSubstring('bbbbb')
    assert 'b' == longestSubstring1.longestSubString()
    LongestSubstring longestSubstring2 = new LongestSubstring('abcabcbb')
    assert 'abc' == longestSubstring2.longestSubString()
    LongestSubstring longestSubstring3 = new LongestSubstring('pwwkew')
    assert 'wke' == longestSubstring3.longestSubString()
    LongestSubstring longestSubstring4 = new LongestSubstring('abcadbcbb')
    assert 'bcad' == longestSubstring4.longestSubString()
    LongestSubstring longestSubstring5 = new LongestSubstring('a')
    assert 'a' == longestSubstring5.longestSubString()
  }
}
