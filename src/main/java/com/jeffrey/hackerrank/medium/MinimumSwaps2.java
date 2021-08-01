package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;

public class MinimumSwaps2 {

	/*
	 * Problem: Minimum Swaps 2
	 * https://www.hackerrank.com/challenges/minimum-swaps-2/problem
	 *
	 * Time Complexity: O(N), N = the length of input array
	 *
	 * Space Complexity: O(N)
	 *
	 */
	static int minimumSwaps(int[] arr) {

		Map<Integer, Integer> v2i = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++)
			v2i.put(arr[i], i);
		int ans = 0;

		// keep change the value i+1 to ith position
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i + 1) {
				int idx = v2i.get(i + 1);
				arr[idx] = arr[i];
				v2i.put(arr[i], idx);
				ans++;
			}
		}
		return ans;

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

		int res = minimumSwaps(arr);

		bufferedWriter.write(String.valueOf(res));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}