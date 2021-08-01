package com.jeffrey.hackerrank.hard;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class ArrayManipulationResult {

	/*
	 * Problem: Array Manipulation
	 * https://www.hackerrank.com/challenges/crush/problem
	 *
	 * Time Complexity: O(NlogN), N = the length of input queries List
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static long arrayManipulation(int n, List<List<Integer>> queries) {
		// Write your code here
		Collections.sort(queries, (l1, l2) -> l1.get(0) - l2.get(0));
		long ans = 0;
		PriorityQueue<List<Integer>> q = new PriorityQueue<List<Integer>>((l1, l2) -> l1.get(1) - l2.get(1));
		long sum = 0;
		// remove the interval in the queue which endTime is smaller than the startTime
		// of the next interval
		// add next interval into the queue
		// record the sum of the weight of all intervals in the queue
		for (List<Integer> interval : queries) {
			while (q.size() > 0 && q.peek().get(1) < interval.get(0)) {
				sum -= q.poll().get(2);
			}
			q.add(interval);
			sum += interval.get(2);
			ans = Math.max(ans, sum);
		}
		return ans;

	}

}

public class ArrayManipulation {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int m = Integer.parseInt(firstMultipleInput[1]);

		List<List<Integer>> queries = new ArrayList<>();

		IntStream.range(0, m).forEach(i -> {
			try {
				queries.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		long result = ArrayManipulationResult.arrayManipulation(n, queries);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
