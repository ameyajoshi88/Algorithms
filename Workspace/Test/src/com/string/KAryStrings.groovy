package com.string

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * This program generates all possible k-ary strings of
 * the given length n
 */

@CompileStatic
class KAryStrings {
	
	public KAryStrings(int n, int k) {
		this.n = n;
		this.k = k
	}

	int n
	int k
	Set<String> result = []
	
	void generate() {
		kAryStrings('')
	}
	
	void kAryStrings(String s) {
		if (n == s.length()) {
			result << s
			return
		}
		for (int i = 0; i < k; i++) {
			kAryStrings("${i}${s}")
		}
	}
	
	public static void main(String[] args) {
		KAryStrings kAryStrings = new KAryStrings(3, 2) // ~binary
		kAryStrings.generate()
		assert 8 == kAryStrings.result.size()
		println(kAryStrings.result)
	}
		
}