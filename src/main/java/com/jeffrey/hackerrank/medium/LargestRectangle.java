package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class LargestRectangleResult {

	/*
	 * Problem: Largest Rectangle
	 * https://www.hackerrank.com/challenges/largest-rectangle/problem
	 *
	 * Time Complexity: O(N), N = the length of the input h list
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static long largestRectangle(List<Integer> h) {
		// Write your code here
		int ans = 0;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(-1);
		for (int i = 0; i < h.size(); i++) {
			while (q.peekLast() != -1 && h.get(q.peekLast()) >= h.get(i)) {
				int idx = q.pollLast();
				ans = Math.max(ans, h.get(idx) * (i - q.peekLast() - 1));
			}
			q.add(i);
		}
		while (q.peekLast() != -1) {
			int idx = q.pollLast();
			ans = Math.max(ans, h.get(idx) * (h.size() - q.peekLast() - 1));
		}
		return ans;

	}

}

public class LargestRectangle {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		long result = LargestRectangleResult.largestRectangle(h);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
