package com.jeffrey.hackerrank.hard;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class MatrixResult {

	static class Group {
		int id;
		Group parent;
		boolean hasMachine;

		public Group(int id, boolean hasMachine) {
			this.id = id;
			this.hasMachine = hasMachine;
		}

		public Group getRootGroup() {
			Group g = this;
			while (g.parent != null)
				g = g.parent;
			return g;
		}
	}
	/*
	 * Problem: Matrix
	 * https://www.hackerrank.com/challenges/matrix/problem
	 *
	 * Time Complexity: O(M + RlogR), M = the length of machines list, R = the number of roads
	 *
	 * Space Complexity: O(N), N = the number of the city
	 *
	 */

	public static int minTime(List<List<Integer>> roads, List<Integer> machines) {
		// Write your code here
		int gid = 1;
		int ans = 0;
		Map<Integer, Group> gmap = new HashMap<Integer, Group>();
		for (int m : machines)
			gmap.put(m, new Group(gid++, true));
		Collections.sort(roads, (l1, l2) -> l2.get(2) - l1.get(2));
		for (List<Integer> r : roads) {
			if (!gmap.containsKey(r.get(0))) {
				if (!gmap.containsKey(r.get(1))) {
					Group group = new Group(gid++, false);
					gmap.put(r.get(0), group);
					gmap.put(r.get(1), group);
				} else {
					gmap.put(r.get(0), gmap.get(r.get(1)).getRootGroup());
				}
			} else {
				if (!gmap.containsKey(r.get(1))) {
					gmap.put(r.get(1), gmap.get(r.get(0)).getRootGroup());
				} else {
					Group g1 = gmap.get(r.get(0)).getRootGroup();
					Group g2 = gmap.get(r.get(1)).getRootGroup();
					if (g1 != g2) {
						if (g1.hasMachine) {
							if (g2.hasMachine)
								ans += r.get(2);
							else
								g2.parent = g1;
						} else {
							g1.parent = g2;
						}
					}
				}

			}

		}
		return ans;
	}

}

public class Matrix {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<List<Integer>> roads = new ArrayList<>();

		IntStream.range(0, n - 1).forEach(i -> {
			try {
				roads.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
						.collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		List<Integer> machines = IntStream.range(0, k).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		int result = MatrixResult.minTime(roads, machines);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
