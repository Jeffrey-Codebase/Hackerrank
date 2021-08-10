package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.stream.*;

class AbbreviationResult {

	/*
	 * Problem: Abbreviation 
	 * https://www.hackerrank.com/challenges/abbr/problem
	 *
	 * Time Complexity: O(N*M), N = the length of the a string, M = the length of the b string
	 *
	 * Space Complexity: O(N*M)
	 *
	 */

	private static int[][] dp;

	public static String abbreviation(String a, String b) {
		// Write your code here

		dp = new int[a.length()][b.length()];
		return check(a, 0, b, 0) ? "YES" : "NO";

	}

	private static boolean check(String a, int idxa, String b, int idxb) {
		if (idxb == b.length()) {
			while (idxa < a.length()) {
				if (Character.isUpperCase(a.charAt(idxa++)))
					return false;
			}
			return true;
		}
		if (idxa == a.length())
			return false;
		if (dp[idxa][idxb] != 0)
			return dp[idxa][idxb] == 1;
		boolean ans = false;
		if (Character.toUpperCase(a.charAt(idxa)) == b.charAt(idxb))
			ans = check(a, idxa + 1, b, idxb + 1);
		dp[idxa][idxb] = ans || (Character.isLowerCase(a.charAt(idxa)) && check(a, idxa + 1, b, idxb)) ? 1 : -1;
		return dp[idxa][idxb] == 1;
	}

}

public class Abbreviation {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String a = bufferedReader.readLine();

				String b = bufferedReader.readLine();

				String result = AbbreviationResult.abbreviation(a, b);

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

