package com.string;

import java.util.Set;
import java.util.TreeSet;
/**
 * 
 * @author Ameya
 * This program finds all the permutations of a given string
 * Consider the string character by character and keep creating permutations
 * abc: a - a
 * 		b - ab, ba
 *      c - cab, abc, acb, cba, bac, bca
 */
public class Permutations {
	
	private static Set<String> permutations(String str) {
		Set<String> permutations = new TreeSet<String>();
		permutations.add(str.substring(0, 1));
		
		for(int index = 1; index < str.length(); index++) {
			String currentChar = str.substring(index, index + 1);
			
			permutations = allPermutationsWith(permutations, currentChar);
		}
		
		return permutations;
	}
	
	private static Set<String> allPermutationsWith(Set<String> input, String character) {
		Set<String> permutations = new TreeSet<String>();
		
		for(String currentWord : input) {
			// insert at the beginning and end
			
			// suffix
			permutations.add(currentWord + character);
			
			// prefix
			permutations.add(character + currentWord);
			
			// insert in the middle at possible positions
			for(int index = 0; index < currentWord.length()-1; index++) {
				String prefix = currentWord.substring(0, index + 1);
				String suffix = currentWord.substring(index + 1);
				permutations.add(prefix + character + suffix);
			}
		}
		
		return permutations;
	}
	
	public static void main(String args[]) {
		Set<String> result = permutations("abcd");
		
		System.out.println("Number of permutations = " + result.size());
		for(String permutation : result) {
			System.out.println(permutation);
		}
	}
}
