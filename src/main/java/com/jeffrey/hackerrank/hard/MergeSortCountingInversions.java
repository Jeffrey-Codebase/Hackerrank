package com.jeffrey.hackerrank.hard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

class MergeSortCountingInversionsResult {

	/*
	 * Problem: Merge Sort: Counting Inversions
	 * https://www.hackerrank.com/challenges/ctci-merge-sort/problem
	 *
	 * Time Complexity: O(NlogN), N = the length of input List
	 *
	 * Space Complexity: O(N)
	 *
	 */

	private static long ans;

	public static long countInversions(List<Integer> arr) {
		ans = 0;
		helper(arr, 0, arr.size() - 1);
		return ans;
	}

	private static List<Integer> helper(List<Integer> arr, int s, int e) {
		List<Integer> result = new ArrayList<Integer>();
		if (s == e) {
			result.add(arr.get(s));
		} else {
			int m = (s + e) / 2;
			List<Integer> left = helper(arr, s, m);
			List<Integer> right = helper(arr, m + 1, e);

			int idx1 = 0, idx2 = 0;
			while (idx1 < left.size() && idx2 < right.size()) {
				if (left.get(idx1) <= right.get(idx2)) {
					result.add(left.get(idx1++));
				} else {
					ans += (left.size() - idx1);
					result.add(right.get(idx2++));
				}
			}
			while (idx1 < left.size())
				result.add(left.get(idx1++));
			while (idx2 < right.size())
				result.add(right.get(idx2++));
		}
		return result;
	}

}

public class MergeSortCountingInversions {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				long result = MergeSortCountingInversionsResult.countInversions(arr);

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

