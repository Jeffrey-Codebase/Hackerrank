package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class RoadsAndLibrariesResult {

	/*
	 * Problem: Roads and Libraries
	 * https://www.hackerrank.com/challenges/torque-and-development/problem
	 *
	 * Time Complexity: O(N+E), N = the number of the cities, E = the number of the
	 * roads
	 *
	 * Space Complexity: O(N+E), N = the number of the cities, E = the number of the
	 * roads
	 *
	 */

	public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
		// Write your code here
		Map<Integer, List<Integer>> adj = new HashMap<Integer, List<Integer>>();
		for (List<Integer> r : cities) {
			if (!adj.containsKey(r.get(0)))
				adj.put(r.get(0), new ArrayList<Integer>());
			if (!adj.containsKey(r.get(1)))
				adj.put(r.get(1), new ArrayList<Integer>());
			adj.get(r.get(0)).add(r.get(1));
			adj.get(r.get(1)).add(r.get(0));
		}
		boolean[] visited = new boolean[n + 1];
		long ans = 0;
		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			LinkedList<Integer> q = new LinkedList<Integer>();
			q.add(i);
			int cnt = 0;
			while (q.size() > 0) {
				int c = q.poll();
				if (visited[c])
					continue;
				visited[c] = true;
				cnt++;
				if (adj.containsKey(c)) {
					for (int a : adj.get(c)) {
						if (!visited[a])
							q.add(a);
					}
				}
			}
			/*
			 * There are two options: 1. build the road to make the cities connected, then
			 * build one library 2. build one library for each city
			 */
			ans += Math.min(cnt * c_lib, --cnt * c_road + c_lib);
		}

		return ans;
	}

}

public class RoadsAndLibraries {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int n = Integer.parseInt(firstMultipleInput[0]);

				int m = Integer.parseInt(firstMultipleInput[1]);

				int c_lib = Integer.parseInt(firstMultipleInput[2]);

				int c_road = Integer.parseInt(firstMultipleInput[3]);

				List<List<Integer>> cities = new ArrayList<>();

				IntStream.range(0, m).forEach(i -> {
					try {
						cities.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
								.map(Integer::parseInt).collect(toList()));
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				});

				long result = RoadsAndLibrariesResult.roadsAndLibraries(n, c_lib, c_road, cities);

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

