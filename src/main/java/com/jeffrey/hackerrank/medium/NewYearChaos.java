package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class NewYearChaosResult {

	/*
	 * Problem: New Year Chaos
	 * https://www.hackerrank.com/challenges/new-year-chaos/problem
	 *
	 * Time Complexity: O(NlogN), N = the length of input q List
	 *
	 * Space Complexity: O(N)
	 *
	 */
	public static void minimumBribes(List<Integer> q) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		int ans = 0;
		// the ith number needs to bribe all the smaller numbers in the i+1~N position.
		for (int i = q.size() - 1; i >= 0; i--) {
			int num = q.get(i);
			int smaller = set.headSet(num).size();

			if (smaller > 2) {
				System.out.println("Too chaotic");
				return;
			}
			ans += smaller;
			set.add(num);
		}
		System.out.println(ans);

	}

}

public class NewYearChaos {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				NewYearChaosResult.minimumBribes(q);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
	}
}
