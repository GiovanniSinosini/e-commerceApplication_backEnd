package com.giosinosini.springboot3.services.validation.utils;

public class PT {

	public static boolean isValidNIF(String nif) {
		final int max = 9;
		if (!nif.matches("[0-9]+") || nif.length() != max)
			return false;
		int checkSum = 0;

		for (int i = 0; i < max - 1; i++) {
			checkSum += (nif.charAt(i) - '0') * (max - i);
		}
		int checkDigit = 11 - (checkSum % 11);
		if (checkDigit >= 10)
			checkDigit = 0;
		return checkDigit == nif.charAt(max - 1) - '0';
	}

}
