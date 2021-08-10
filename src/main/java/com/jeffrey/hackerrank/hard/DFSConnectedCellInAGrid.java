package com.jeffrey.hackerrank.hard;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class DFSConnectedCellInAGridResult {

	/*
	 * Problem: DFS: Connected Cell in a Grid
	 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem
	 *
	 * Time Complexity: O(R*C), R = the row number of the grid, C = the column
	 * number of the grid
	 *
	 * Space Complexity: O(R*C)
	 *
	 */
	private static boolean[][] visited;

	public static int maxRegion(List<List<Integer>> grid) {
		// Write your code here
		int R = grid.size();
		int C = grid.get(0).size();
		int ans = 0;
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visited[i][j] || grid.get(i).get(j) == 0)
					continue;
				int region = dfs(grid, i, j);
				ans = Math.max(ans, region);
			}
		}
		return ans;

	}

	private static int dfs(List<List<Integer>> grid, int i, int j) {

		if (i < 0 || i >= grid.size() || j < 0 || j >= grid.get(0).size() || visited[i][j] || grid.get(i).get(j) == 0)
			return 0;
		visited[i][j] = true;
		int region = 1;
		region += dfs(grid, i - 1, j - 1);
		region += dfs(grid, i - 1, j);
		region += dfs(grid, i - 1, j + 1);
		region += dfs(grid, i, j - 1);
		region += dfs(grid, i, j + 1);
		region += dfs(grid, i + 1, j - 1);
		region += dfs(grid, i + 1, j);
		region += dfs(grid, i + 1, j + 1);
		return region;
	}

}

public class DFSConnectedCellInAGrid {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int m = Integer.parseInt(bufferedReader.readLine().trim());

		List<List<Integer>> grid = new ArrayList<>();

		IntStream.range(0, n).forEach(i -> {
			try {
				grid.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
						.collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int res = DFSConnectedCellInAGridResult.maxRegion(grid);

		bufferedWriter.write(String.valueOf(res));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}

