package com.string.shorten;

/**
 * Given a string 's' and an integer 'k', shorten the string by removing all the
 * substrings that are of the length 'k' (k > 1) and contain the same character
 * e.g. s = 'abbcccbb'; k = 3
 * 
 * First, we will remove the 3 c's giving us abbbb Next, we will remove 3 b's
 * giving us ab
 * 
 * @author Ameya
 */
public class Shorten {

	public static void main(String[] args) {
		String s = "abbcccbb";
		int k = 3;
		System.out.println("s=" + s + " k=" + k + " answer=" + shorten(s, k));
		s = "abbcccbb";
		k = 2;
		System.out.println("s=" + s + " k=" + k + " answer=" + shorten(s, k));
		s = "agccccbddddbeeeebffffbggg";
		k = 4;
		System.out.println("s=" + s + " k=" + k + " answer=" + shorten(s, k));
	}

	private static String shorten(String s, int k) {
		int[] arr = new int[s.length()]; // arr[i] = At index i, how many consecutive s[i] characters are found when
											// going left to right
		arr[0] = 1;
		for (int i = 1; i < s.length(); i++) {
			char previous = s.charAt(i - 1);
			char current = s.charAt(i);
			if (current == previous) {
				arr[i] = arr[i - 1] + 1;
				if (arr[i] == k) { // k consecutive characters found
					s = s.substring(0, i - k + 1) + s.substring(i + 1); // shorten i.e. remove the consecutive
																		// characters
					i = i - k; // Re-align i to be in sync with the new string length
				}
			} else {
				arr[i] = 1;
			}
		}
		return s;
	}

}

// Output:

//s=abbcccbb k=3 answer=ab
//s=abbcccbb k=2 answer=ac
//s=agccccbddddbeeeebffffbggg k=4 answer=a
