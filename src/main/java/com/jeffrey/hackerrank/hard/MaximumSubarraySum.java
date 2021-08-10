package com.jeffrey.hackerrank.hard;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class MaximumSubarraySumResult {

	/*
	 * Problem: Maximum Subarray Sum
	 * https://www.hackerrank.com/challenges/maximum-subarray-sum/problem
	 *
	 * Time Complexity: O(NlogN), N = the length of the input a list
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static long maximumSum(List<Long> a, long m) {
		// Write your code here

		long[] prefix_sum_mod = new long[a.size()];
		prefix_sum_mod[0] = a.get(0) % m;
		long ans = prefix_sum_mod[0];
		for (int i = 1; i < a.size(); i++) {
			prefix_sum_mod[i] = (a.get(i) + prefix_sum_mod[i - 1]) % m;
			ans = Math.max(ans, prefix_sum_mod[i]);
			if (ans == m - 1)
				return ans;
		}

		// two possible way to find the max mod result
		// find the biggest positive mod value
		// find the biggest negative mod value
		TreeSet<Long> seen = new TreeSet<Long>();
		seen.add(prefix_sum_mod[0]);
		for (int i = 1; i < prefix_sum_mod.length; i++) {
			ans = Math.max(ans, (prefix_sum_mod[i] - seen.first()) % m);
			Long higher = seen.higher(prefix_sum_mod[i]);
			if (higher != null)
				ans = Math.max(ans, (prefix_sum_mod[i] + m - higher) % m);
			if (ans == m - 1)
				return ans;
			seen.add(prefix_sum_mod[i]);
		}
		return ans;

	}

}

public class MaximumSubarraySum {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int n = Integer.parseInt(firstMultipleInput[0]);

				long m = Long.parseLong(firstMultipleInput[1]);

				List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Long::parseLong).collect(toList());

				long result = MaximumSubarraySumResult.maximumSum(a, m);

				bufferedWriter.write(String.valueOf(result));
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
