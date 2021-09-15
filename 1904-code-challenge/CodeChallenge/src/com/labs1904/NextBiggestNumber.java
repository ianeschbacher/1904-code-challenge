package com.labs1904;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextBiggestNumber {

	public static void main(String[] args) {
		Integer input = Integer.parseInt(args[0]);

		Integer nextBiggestNumber = getNextBiggestNumber(input);
		System.out.println("Input: " + input);
		System.out.println("Next biggest number: " + nextBiggestNumber);
	}

	public static int getNextBiggestNumber(Integer i) {
		List<Integer> digits = intToList(i);

		return searchForSwapDigit(digits);
	}

	private static Integer searchForSwapDigit(List<Integer> digits) {
		List<Integer> previous = new ArrayList<>();
		boolean swapDigitFound = false;
		List<Integer> stableDigits = new ArrayList<>();
		Integer swapDigit = -1;

		for (Integer i : digits) {

			if (swapDigitFound) {
				stableDigits.add(i);
			} else {
				if (!previous.isEmpty() && i < previous.get(previous.size() - 1)) {
					swapDigit = findNextLowest(i, previous);
					previous.remove(swapDigit);
					previous.add(i);
					swapDigitFound = true;
				} else {
					previous.add(i);
				}
			}
		}

		if (!swapDigitFound) {
			return swapDigit;
		}

		Collections.sort(previous);
		Collections.reverse(stableDigits);

		stableDigits.add(swapDigit);
		stableDigits.addAll(previous);

		return rebuildInt(stableDigits);
	}

	private static Integer findNextLowest(Integer digitToSwap, List<Integer> previous) {

		List<Integer> candidates = new ArrayList<>();

		for (Integer i : previous) {
			if (i > digitToSwap) {
				candidates.add(i);
			}
		}

		Collections.sort(candidates);

		return candidates.get(0);

	}

	private static List<Integer> intToList(Integer i) {

		List<Integer> digits = new ArrayList<>();
		Integer temp = i;
		while (temp > 0) {
			digits.add(temp % 10);
			temp = temp / 10;
		}
		return digits;
	}

	private static Integer rebuildInt(List<Integer> digits) {
		StringBuilder sb = new StringBuilder();

		for (Integer i : digits) {
			sb.append(i.toString());
		}

		return Integer.parseInt(sb.toString());
	}
}
