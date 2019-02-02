package com.string

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * This program generates all possible binary strings of
 * the given length n
 * 
 * Reference:
 * https://codereview.stackexchange.com/questions/24690/print-all-binary-strings-of-length-n#answer-184262 
 */

@CompileStatic
class BinaryStrings {

  public BinaryStrings(int n) {
    this.n = n;
  }

  int n
  Set<String> result = []

  void generate() {
    binaryStrings('')
  }

  void binaryStrings(String s) {
    if (n == s.length()) {
      result << s
      return
    }
    binaryStrings("0${s}")
    binaryStrings("1${s}")
  }

  public static void main(String[] args) {
    BinaryStrings binaryStrings = new BinaryStrings(3)
    binaryStrings.generate()
    assert 8 == binaryStrings.result.size()
    println(binaryStrings.result)
  }
}