package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;

class BalancedBracketsResult {

	/*
	 * Problem: Balanced Brackets
	 * https://www.hackerrank.com/challenges/balanced-brackets/problem
	 *
	 * Time Complexity: O(N), N = the length of the input s string
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static String isBalanced(String s) {
		// Write your code here
		LinkedList<Character> stack = new LinkedList<Character>();
		for (char c : s.toCharArray()) {

			if (c == '}') {
				if (stack.size() == 0 || stack.pop() != '{')
					return "NO";
			} else if (c == ')') {
				if (stack.size() == 0 || stack.pop() != '(')
					return "NO";
			} else if (c == ']') {
				if (stack.size() == 0 || stack.pop() != '[')
					return "NO";
			} else {
				stack.push(c);
			}
		}
		return stack.size() == 0 ? "YES" : "NO";

	}

}

public class BalancedBrackets {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				String s = bufferedReader.readLine();

				String result = BalancedBracketsResult.isBalanced(s);

				bufferedWriter.write(result);
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
