package com.jeffrey.hackerrank.hard;

import java.io.*;
import java.util.*;

class ReverseShuffleMergeResult {

	/*
	 * Problem: Reverse Shuffle Merge
	 * https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem
	 *
	 * Time Complexity: O(N), N = the length of the input string
	 *
	 * Space Complexity: O(N)
	 *
	 */

	public static String reverseShuffleMerge(String s) {
		// Write your code here
		int[] remain = new int[26];
		int[] need = new int[26];
		char[] ca = s.toCharArray();
		for (char c : ca) {
			remain[c - 'a']++;
		}
		for (int i = 0; i < 26; i++)
			need[i] = remain[i] / 2;
		LinkedList<Character> q = new LinkedList<Character>();
		for (int i = ca.length - 1; i >= 0; i--) {
			if (need[ca[i] - 'a'] > 0) {
				while (q.size() > 0 && ca[i] < q.peekLast() && remain[q.peekLast() - 'a'] > need[q.peekLast() - 'a']) {
					need[q.pollLast() - 'a']++;
				}
				need[ca[i] - 'a']--;
				q.add(ca[i]);
			}
			remain[ca[i] - 'a']--;
		}
		StringBuilder sb = new StringBuilder();
		for (char c : q)
			sb.append(c);
		return sb.toString();

	}

}

public class ReverseShuffleMerge {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		String result = ReverseShuffleMergeResult.reverseShuffleMerge(s);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
