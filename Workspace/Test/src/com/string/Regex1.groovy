package com.string

import groovy.transform.CompileStatic

/**
 * @author Ameya
 *
 * This program takes a string and a pattern as input and returns true if the
 * string matches the pattern and false otherwise
 *
 * Technique: Dynamic Programming
 * Reference: https://www.youtube.com/watch?v=l3hda49XcDE
 */

@CompileStatic
class Regex1 {

  Regex1(String s, String p) {
    this.s = s
    this.p = p
  }

  String s
  String p

  boolean isMatch() {
    if (s == null || p == null) {
      return false
    }
    if (p.isEmpty()) {
      return s.isEmpty()
    }
    
    boolean[][] arr = new boolean[s.length() + 1][p.length() + 1]
    // Initialize first row, col
    arr[0][0] = true
    for (int j = 1; j <= p.length(); j++) {
      if (p.charAt(j - 1) == '*') {
        arr[0][j] = arr[0][j - 2]
      }
    }
    // Fill rest of the arr
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= p.length(); j++) {
        // Case 1
        if (p.charAt(j - 1) == '*') {
          arr[i][j] = arr[i][j - 2]
          if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
            arr[i][j] = arr[i][j] || arr[i - 1][j]
          }
        }
        // Case 2
        else if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
          arr[i][j] = arr[i - 1][j - 1]
        }
      }
    }
    return arr[s.length()][p.length()]
  }

  static void main(String[] args) {
    Regex1 r = new Regex1('aa', 'a')
    assert !r.isMatch()
    r = new Regex1('aa', 'a*')
    assert r.isMatch()
    r = new Regex1('ab', '.*')
    assert r.isMatch()
    r = new Regex1('aab', 'c*a*b')
    assert r.isMatch()
    r = new Regex1('mississippi', 'mis*is*p*.')
    assert !r.isMatch()
    r = new Regex1('aaa', 'ab*a*c*a')
    assert r.isMatch()
    r = new Regex1('aaa', 'aaaa')
    assert !r.isMatch()
    r = new Regex1('ab', '.*c')
    assert !r.isMatch()
    r = new Regex1('', '.*')
    assert r.isMatch()
  }
}
