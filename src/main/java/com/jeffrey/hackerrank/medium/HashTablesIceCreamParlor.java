package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class HashTablesIceCreamParlorResult {

	/*
	 * Problem: Hash Tables: Ice Cream Parlor
	 * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem
	 *
	 * Time Complexity: O(N), N = the length of input cost list
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static void whatFlavors(List<Integer> cost, int money) {
		// Write your code here
		Map<Integer, Integer> seen = new HashMap<Integer, Integer>();
		for (int i = 0; i < cost.size(); i++) {
			int c = cost.get(i);
			int target = money - c;
			if (seen.containsKey(target)) {
				System.out.println((seen.get(target) + 1) + " " + (i + 1));
				return;
			}
			seen.put(c, i);
		}
	}
}

public class HashTablesIceCreamParlor {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				int money = Integer.parseInt(bufferedReader.readLine().trim());

				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				HashTablesIceCreamParlorResult.whatFlavors(cost, money);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
	}
}
