package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;

public class MaxArraySum {

	/*
	 * Problem: Max Array Sum
	 * https://www.hackerrank.com/challenges/max-array-sum/problem
	 *
	 * Time Complexity: O(N), N = the length of the input arr array
	 *
	 * Space Complexity: O(N)
	 *
	 */
	static int maxSubsetSum(int[] arr) {
		int[] dp = new int[arr.length + 1];
		dp[arr.length - 1] = arr[arr.length - 1] > 0 ? arr[arr.length - 1] : 0;
		for (int i = arr.length - 2; i >= 0; i--) {
			dp[i] = arr[i] > 0 ? Math.max(arr[i] + dp[i + 2], dp[i + 1]) : dp[i + 1];
		}
		return dp[0];
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] arr = new int[n];

		String[] arrItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int arrItem = Integer.parseInt(arrItems[i]);
			arr[i] = arrItem;
		}

		int res = maxSubsetSum(arr);

		bufferedWriter.write(String.valueOf(res));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
