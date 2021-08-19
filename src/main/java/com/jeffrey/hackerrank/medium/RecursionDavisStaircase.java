package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.stream.*;

class RecursionDavisStaircaseResult {

	/*
	 * Problem: Recursion: Davis' Staircase
	 * https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem
	 *
	 * Time Complexity: O(N), N = the input Integer n
	 *
	 * Space Complexity: O(1)
	 *
	 */
	public static int stepPerms(int n) {
		// Write your code here
		int[] preSteps = new int[3];
		preSteps[0] = 1;
		preSteps[1] = 2;
		preSteps[2] = 4;
		int step = 3;
		for (; step < n; step++) {
			preSteps[step % 3] = preSteps[0] + preSteps[1] + preSteps[2];
		}
		return preSteps[(n - 1) % 3];
	}
}

public class RecursionDavisStaircase {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int s = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, s).forEach(sItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				int res = RecursionDavisStaircaseResult.stepPerms(n);

				bufferedWriter.write(String.valueOf(res));
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}

