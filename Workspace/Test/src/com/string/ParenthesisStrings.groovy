package com.string

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * This program generates all possible valid strings using n pairs of parentheses
 * or in other words generates all possible n pairs of balanced parentheses
 */

@CompileStatic
class ParenthesisStrings {

  public ParenthesisStrings(int n) {
    this.n = n;
  }

  int n
  Set<String> result = []

  void generate() {
    parenthesesStrings('')
  }

  void parenthesesStrings(String s) {
    if (n * 2 == s.length()) {
      result << s
      return
    }
    parenthesesStrings("(${s})")
    parenthesesStrings("()${s}")
    parenthesesStrings("${s}()")
  }

  public static void main(String[] args) {
    ParenthesisStrings parenthesesStrings = new ParenthesisStrings(3)
    parenthesesStrings.generate()
    assert 5 == parenthesesStrings.result.size()
    println(parenthesesStrings.result)
  }
}