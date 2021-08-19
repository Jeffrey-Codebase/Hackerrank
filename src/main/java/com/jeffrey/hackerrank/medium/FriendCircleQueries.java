package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;

public class FriendCircleQueries {
	static class Group {
		int count;
		Group parent;

		public Group() {
			this.count = 2;
		}

		public Group getRootGroup() {
			Group g = this;
			while (g.parent != null)
				g = g.parent;
			return g;
		}
	}

	/*
	 * Problem: Friend Circle Queries
	 * https://www.hackerrank.com/challenges/friend-circle-queries/problem
	 *
	 * Time Complexity: O(logN * M), N = the number of distinct numbers in queries,
	 * M = the length of the queries array
	 *
	 * Space Complexity: O(N+M)
	 *
	 */
	static int[] maxCircle(int[][] queries) {
		int max = 0;
		Map<Integer, Group> map = new HashMap<Integer, Group>();
		int[] ans = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int[] q = queries[i];
			if (!map.containsKey(q[0])) {
				if (!map.containsKey(q[1])) {
					Group g = new Group();
					map.put(q[0], g);
					map.put(q[1], g);
					max = Math.max(max, 2);
				} else {
					Group g = map.get(q[1]).getRootGroup();
					g.count++;
					map.put(q[0], g);
					max = Math.max(max, g.count);
				}
			} else {
				if (!map.containsKey(q[1])) {
					Group g = map.get(q[0]).getRootGroup();
					g.count++;
					map.put(q[1], g);
					max = Math.max(max, g.count);
				} else {
					Group g1 = map.get(q[0]).getRootGroup();
					Group g2 = map.get(q[1]).getRootGroup();
					if (g1 != g2) {
						g1.count += g2.count;
						g2.parent = g1;
						max = Math.max(max, g1.count);
					}
				}
			}
			ans[i] = max;
		}
		return ans;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[][] queries = new int[q][2];

		for (int i = 0; i < q; i++) {
			String[] queriesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 2; j++) {
				int queriesItem = Integer.parseInt(queriesRowItems[j]);
				queries[i][j] = queriesItem;
			}
		}

		int[] ans = maxCircle(queries);

		for (int i = 0; i < ans.length; i++) {
			bufferedWriter.write(String.valueOf(ans[i]));

			if (i != ans.length - 1) {
				bufferedWriter.write("\n");
			}
		}

		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
