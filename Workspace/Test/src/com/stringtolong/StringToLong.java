package com.stringtolong;

public class StringToLong {
	private static final char ZERO = '0';
	private static final String LONG_STRING_ERROR_MSG = "The given string is too long. " +
														"It cannot be converted to 'long'";
	private static final String REGEX_DIGITS_ONLY = "^-?[0-9]*$";
	private static final String INVALID_STRING_ERROR_MSG = "Invalid string";
	private static final char MINUS_SIGN = '-';
	
	private static long stringToLong(String str) {     
		long number = 0;
		boolean isNumberNegative = false;
		
		// check for negative number
		if(str.charAt(0) == MINUS_SIGN) {
			isNumberNegative = true;
			// ignore the sign for calculation
			str = str.substring(1);
		}
		
		// index of last element in String
		int index = str.length() - 1;
		long multiplier = 1;
		int digit = 0;
		long prevNumber = 0;
		
		// create the number starting from the unit's place
		while(index >= 0) {
			digit = str.charAt(index) - ZERO;
			prevNumber = number;
			number += digit * multiplier;
			if(number < prevNumber) {
				// This can happen when given number is greater than Long.MAX_VALUE
				throw new IllegalArgumentException(LONG_STRING_ERROR_MSG);
			}
			index--;
			multiplier *= 10;
		}
		
		// make the computed number negative if there was a minus sign
		if(isNumberNegative) {
			number *= -1;
		}
		
		return number;
	}
	
	private static boolean isValidLongString(String str) {
		return str.matches(REGEX_DIGITS_ONLY);
	}
		
	public static void main(String args[]) {
		String str = "-1222222222222234342";
		
		if(isValidLongString(str)) {
			System.out.println(stringToLong(str));
		} else {
			System.out.println(INVALID_STRING_ERROR_MSG);
		}
	}
}
