package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;

public class FindTheNearestClone {

	/*
	 * Problem: Find the nearest clone
	 * https://www.hackerrank.com/challenges/find-the-nearest-clone/problem
	 *
	 * Time Complexity: O(N+E), N = the number of the nodes, E = the length of the
	 * graphFrom array
	 *
	 * Space Complexity: O(N+E)
	 *
	 */
	static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
		// solve here
		boolean hasSameId = false;
		for (int i = 1; i <= graphNodes; i++) {
			if (ids[i - 1] == ids[val - 1] && i != val) {
				hasSameId = true;
				break;
			}
		}
		if (!hasSameId)
			return -1;
		Map<Integer, List<Integer>> adj = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < graphFrom.length; i++) {
			if (!adj.containsKey(graphFrom[i]))
				adj.put(graphFrom[i], new ArrayList<Integer>());
			if (!adj.containsKey(graphTo[i]))
				adj.put(graphTo[i], new ArrayList<Integer>());
			adj.get(graphFrom[i]).add(graphTo[i]);
			adj.get(graphTo[i]).add(graphFrom[i]);
		}
		LinkedList<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[graphNodes + 1];
		visited[val] = true;
		if (!adj.containsKey(val))
			return -1;

		for (int a : adj.get(val)) {
			q.add(a);
		}
		int len = 1;
		while (q.size() > 0) {
			int L = q.size();
			for (int i = 0; i < L; i++) {
				int n = q.poll();
				if (ids[n - 1] == ids[val - 1])
					return len;
				visited[n] = true;
				if (adj.containsKey(n)) {
					for (int a : adj.get(n)) {
						if (!visited[a])
							q.add(a);
					}
				}

			}
			len++;
		}
		return -1;

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] graphNodesEdges = scanner.nextLine().split(" ");
		int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
		int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

		int[] graphFrom = new int[graphEdges];
		int[] graphTo = new int[graphEdges];

		for (int i = 0; i < graphEdges; i++) {
			String[] graphFromTo = scanner.nextLine().split(" ");
			graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
			graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
		}

		long[] ids = new long[graphNodes];

		String[] idsItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < graphNodes; i++) {
			long idsItem = Long.parseLong(idsItems[i]);
			ids[i] = idsItem;
		}

		int val = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

		bufferedWriter.write(String.valueOf(ans));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
