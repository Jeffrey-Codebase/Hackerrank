package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class CastleOnTheGridResult {

	/*
	 * Problem: Castle on the Grid
	 * https://www.hackerrank.com/challenges/castle-on-the-grid/problem
	 *
	 * Time Complexity: O(R*C), R = the row number of the grid, C = the column
	 * number of the grid
	 *
	 * Space Complexity: O(R*C)
	 *
	 */

	public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
		// Write your code here
		int R = grid.size();
		int C = grid.get(0).length();
		boolean[][] visited = new boolean[R][C];
		LinkedList<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { startX, startY });
		int ans = 0;
		while (q.size() > 0) {
			int L = q.size();
			for (int i = 0; i < L; i++) {
				int[] p = q.poll();
				if (p[0] == goalX && p[1] == goalY)
					return ans;
				if (visited[p[0]][p[1]])
					continue;
				visited[p[0]][p[1]] = true;

				int x = p[0];
				while (x > 0 && grid.get(x - 1).charAt(p[1]) != 'X' && !visited[x - 1][p[1]])
					q.offer(new int[] { --x, p[1] });

				x = p[0];
				while (x < R - 1 && grid.get(x + 1).charAt(p[1]) != 'X' && !visited[x + 1][p[1]])
					q.offer(new int[] { ++x, p[1] });

				int y = p[1];
				while (y > 0 && grid.get(p[0]).charAt(y - 1) != 'X' && !visited[p[0]][y - 1])
					q.offer(new int[] { p[0], --y });

				y = p[1];
				while (y < C - 1 && grid.get(p[0]).charAt(y + 1) != 'X' && !visited[p[0]][y + 1])
					q.offer(new int[] { p[0], ++y });
			}
			ans++;
		}
		return -1;
	}

}

public class CastleOnTheGrid {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<String> grid = IntStream.range(0, n).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(toList());

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int startX = Integer.parseInt(firstMultipleInput[0]);

		int startY = Integer.parseInt(firstMultipleInput[1]);

		int goalX = Integer.parseInt(firstMultipleInput[2]);

		int goalY = Integer.parseInt(firstMultipleInput[3]);

		int result = CastleOnTheGridResult.minimumMoves(grid, startX, startY, goalX, goalY);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}

