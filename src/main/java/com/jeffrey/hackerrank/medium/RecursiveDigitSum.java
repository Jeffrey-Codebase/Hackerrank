package com.jeffrey.hackerrank.medium;

import java.io.*;

class RecursiveDigitSumResult {

	/*
	 * Problem: Tree: Recursive Digit Sum
	 * https://www.hackerrank.com/challenges/recursive-digit-sum/problem
	 *
	 * Time Complexity: O(N), N = the length of the input n string
	 *
	 * Space Complexity: O(1)
	 *
	 */

	public static int superDigit(String n, int k) {
		// Write your code here
		long digSum = 0;
		for (char c : n.toCharArray()) {
			digSum += (c - '0');
		}
		return helper(digSum * k);
	}

	private static int helper(long n) {
		if (n < 10)
			return (int) n;
		long digSum = 0;
		while (n > 10) {
			digSum += n % 10;
			n /= 10;
		}
		digSum += n;
		return helper(digSum);
	}

}

public class RecursiveDigitSum {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		String n = firstMultipleInput[0];

		int k = Integer.parseInt(firstMultipleInput[1]);

		int result = RecursiveDigitSumResult.superDigit(n, k);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
