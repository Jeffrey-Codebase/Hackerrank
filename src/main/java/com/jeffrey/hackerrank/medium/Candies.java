package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class CandiesResult {

	/*
	 * Problem: Candies
	 * https://www.hackerrank.com/challenges/candies/problem
	 *
	 * Time Complexity: O(N), N = the length of the input arr list
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static long candies(int n, List<Integer> arr) {
		// Write your code here
		int[] dp = new int[arr.size()];
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			if(arr.get(i)>arr.get(i-1))
                dp[i]=1+dp[i-1];
            else
                dp[i]=1;
		}
		long ans = dp[dp.length - 1];
		for (int i = dp.length - 2; i >= 0; i--) {
			if (arr.get(i) > arr.get(i + 1))
				dp[i] = Math.max(dp[i], dp[i + 1] + 1);
			ans += dp[i];
		}
		return ans;
	}

}

public class Candies {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		long result = CandiesResult.candies(n, arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
