package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class PairsResult {

	/*
	 * Problem: Pairs 
	 * https://www.hackerrank.com/challenges/pairs/problem
	 *
	 * Time Complexity: O(N), N = the length of the input arr list
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static int pairs(int k, List<Integer> arr) {
		// Write your code here
		Set<Integer> seen = new HashSet<Integer>();
		int ans = 0;
		for (int i : arr) {
			if (seen.contains(i - k))
				ans++;
			if (seen.contains(i + k))
				ans++;
			seen.add(i);
		}
		return ans;
	}

}

public class Pairs {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = PairsResult.pairs(k, arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
