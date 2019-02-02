package com.towersofhanoi

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * This program solves the TowerOfHanoi problem
 * The algorithm is taken from the book
 * Data Structures and Algorithms Made Easy in Java
 * 
 * Algorithm:
 *  
 * Move the top n-1 disks from Source to Auxiliary tower,
 * Move the nth disk from Source to Destination tower,
 * Move the n-1 disks from Auxiliary tower to Destination tower.
 */

@CompileStatic
class TowersOfHanoi {
	
	static void solution(List<Integer> discs, char src, char dest, char aux) {
		if (discs.size() == 1) {
			println("Move disk ${discs.first()} from ${src} to ${dest}")
		}
		else {
			solution(discs.subList(0, discs.size() - 1), src, aux, dest)
			println("Move disk ${discs.last()} from ${src} to ${dest}")
			solution(discs.subList(0, discs.size() - 1), aux, dest, src)
		}
	}
	
	public static void main(String[] args) {
		solution([1, 2, 3], 'A' as char, 'B' as char, 'C' as char)
	}
	
}