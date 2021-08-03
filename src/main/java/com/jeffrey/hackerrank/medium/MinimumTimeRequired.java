package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;

public class MinimumTimeRequired {

	/*
	 * Problem: Minimum Time Required
	 * https://www.hackerrank.com/challenges/minimum-time-required/problem
	 *
	 * Time Complexity: O(log(Long.MAX_VALUE)* N), N = the length of the input
	 * machines array
	 *
	 * Space Complexity: O(1)
	 *
	 */
	static long minTime(long[] machines, long goal) {
		long s = 1, e = Long.MAX_VALUE - 1;
		while (s < e) {

			long time = (s + e) / 2;
			long sum = 0;
			boolean pass = false;
			for (long m : machines) {
				sum += time / m;
				if (sum >= goal) {
					pass = true;
					break;
				}
			}
			if (pass)
				e = time;
			else
				s = time + 1;
		}
		return s;
		// TLE Solution
		// Time Complexity: O(N+GOAL*logN)
		// Map<Long,Integer> cnt = new HashMap<Long,Integer>();
		// for(long m : machines){
		// cnt.put(m, cnt.getOrDefault(m,0)+1);
		// }
		// PriorityQueue<Long[]> q = new
		// PriorityQueue<Long[]>((m1,m2)->m1[0].compareTo(m2[0]));
		// for(long m : cnt.keySet()){
		// q.add(new Long[]{m,m});
		// }
		// long ans = 0;
		// while(goal>0){
		// Long[] m = q.poll();
		// ans=m[0];
		// m[0] += m[1];
		// goal -= cnt.get(m[1]);
		// q.offer(m);
		// }
		// return ans;

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] nGoal = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nGoal[0]);

		long goal = Long.parseLong(nGoal[1]);

		long[] machines = new long[n];

		String[] machinesItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			long machinesItem = Long.parseLong(machinesItems[i]);
			machines[i] = machinesItem;
		}

		long ans = minTime(machines, goal);

		bufferedWriter.write(String.valueOf(ans));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
