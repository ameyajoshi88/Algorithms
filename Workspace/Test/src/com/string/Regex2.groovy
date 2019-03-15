package com.string

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * 
 * This program takes a string and a pattern as input and returns true if the
 * string matches the pattern and false otherwise
 * 
 * Technique: Recursion
 */

@CompileStatic
class Regex2 {

  Regex2(String s, String p) {
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
    return isMatch(0, 0)
  }
  
  boolean isMatch(int pIndex, int sIndex) {
    // Both need to be exhausted for a match
    if (pIndex >= p.length()) {
      return sIndex >= s.length()
    }
    
    // If only text has been exhausted, remaining pattern must
    // only consist of optional chars for a match
    if (sIndex >= s.length()) {
      // Remaining length should be a multiple of 2
      if ((p.length() - pIndex) % 2 != 0) {
        return false
      }
      // Every 2nd char must be '*'
      pIndex += 1 // Move to '*'
      while (pIndex < p.length()) {
        if (p.charAt(pIndex) != '*') {
          return false
        }
        pIndex += 2
      }
      return true
    }
    
    if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
      return (isMatch(pIndex + 2, sIndex) // 0 occurrences
        || ((p.charAt(pIndex) == s.charAt(sIndex) 
              || p.charAt(pIndex) == '.')// 1 or more occurrences
            && isMatch(pIndex, sIndex + 1)))
    }
    else {
      if (p.charAt(pIndex) == '.') {
        return isMatch(pIndex + 1, sIndex + 1)
      }
      else {
        return (p.charAt(pIndex) == s.charAt(sIndex) 
          && isMatch(pIndex + 1, sIndex + 1))
      }
    }
  }

  static void main(String[] args) {
    Regex2 r = new Regex2('aa', 'a')
    assert !r.isMatch()
    r = new Regex2('aa', 'a*')
    assert r.isMatch()
    r = new Regex2('ab', '.*')
    assert r.isMatch()
    r = new Regex2('aab', 'c*a*b')
    assert r.isMatch()
    r = new Regex2('mississippi', 'mis*is*p*.')
    assert !r.isMatch()
    r = new Regex2('aaa', 'ab*a*c*a')
    assert r.isMatch()
    r = new Regex2('aaa', 'aaaa')
    assert !r.isMatch()
    r = new Regex2('ab', '.*c')
    assert !r.isMatch()
    r = new Regex2('', '.*')
    assert r.isMatch()
  }
}
