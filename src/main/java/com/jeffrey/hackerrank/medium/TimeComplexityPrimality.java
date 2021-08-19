package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.stream.*;

class TimeComplexityPrimalityResult {
	/*
	 * Problem: Time Complexity: Primality
	 * https://www.hackerrank.com/challenges/ctci-big-o/problem
	 *
	 * Time Complexity: O(square root of n)
	 *
	 * Space Complexity: O(1)
	 */

	public static String primality(int n) {
		// Write your code here
		if (n == 1)
			return "Not prime";
		for (int i = 2; i <= n / i; i++) {
			if (n % i == 0)
				return "Not prime";
		}
		return "Prime";
	}

}

public class TimeComplexityPrimality {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int p = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, p).forEach(pItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				String result = TimeComplexityPrimalityResult.primality(n);

				bufferedWriter.write(result);
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}

