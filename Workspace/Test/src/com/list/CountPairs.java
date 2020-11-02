package com.list;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of integers 'l' and an integer 'k', count how many distinct
 * pairs of integers satisfy the equation 'b = a + k' (1, 2) and (2, 1) are
 * considered the same pair
 * 
 * @author Ameya
 */

public class CountPairs {

	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1, 1, 2, 3, 4, 5);
		int k = 4;
		System.out.println("l=" + l + " k=" + k + " answer=" + countPairs(l, k));
		l = Arrays.asList(1, 1, 2, 3, 4, 5);
		k = 2;
		System.out.println("l=" + l + " k=" + k + " answer=" + countPairs(l, k));
	}

	public static int countPairs(List<Integer> l, int k) {
		Set<Integer> needA = new HashSet<>();
		Set<Integer> needB = new HashSet<>();
		Set<Integer> paired = new HashSet<>();
		int pairs = 0;

		for (int i = 0; i < l.size(); i++) {
			int a = l.get(i);
			int b = l.get(i);

			if (needA.contains(a) && !paired.contains(a)) {
				pairs++;
				paired.add(a);
				paired.add(a + k);
			}

			if (needB.contains(b) && !paired.contains(b)) {
				pairs++;
				paired.add(b);
				paired.add(b - k);
			}

			needA.add(b - k);
			needB.add(a + k);
		}
		return pairs;
	}

}

// Output:
//l=[1, 1, 2, 3, 4, 5] k=4 answer=1
//l=[1, 1, 2, 3, 4, 5] k=2 answer=3
