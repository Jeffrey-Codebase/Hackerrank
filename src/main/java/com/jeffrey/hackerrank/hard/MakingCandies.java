package com.jeffrey.hackerrank.hard;

import java.io.*;

class MakingCandiesResult {

	/*
	 * Problem: Making Candies 
	 * https://www.hackerrank.com/challenges/making-candies/problem
	 *
	 * Time Complexity:                   m*w
	 *                   log   n  *  max( ---  , log   n )
	 *                      m*w            p        m*w
	 *                   
	 * Space Complexity: O(1)
	 *
	 */

	public static long minimumPasses(long m, long w, long p, long n) {
		// Write your code here
		long now = 1;
		if ((double) n / m / w <= 1)
			return 1;
		long candy = m * w;
		long ans = (long) Math.ceil((double) n / (m * w));
		while (candy < n) {
			// can purchase
			//     do purchase
			//     count required time
			// 	   you could't make the required time small than one day. So, we can end the
			//       loop when we find the remaining time == 1.
			// cannot
			//     spend time to produce more candy
			if (candy >= p) {
				candy -= p;
				if (w > m)
					m++;
				else
					w++;
				long remain = (long) Math.ceil((double) (n - candy) / (m * w));
				ans = Math.min(ans, now + remain);
				if (remain == 1) {
					now++;
					break;
				}
			} else {
				long process = (long) Math.ceil((double) (p - candy) / (m * w));
				now += process;
				candy += process * (m * w);
			}

		}
		return Math.min(ans, now);
	}
}

public class MakingCandies {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		long m = Long.parseLong(firstMultipleInput[0]);

		long w = Long.parseLong(firstMultipleInput[1]);

		long p = Long.parseLong(firstMultipleInput[2]);

		long n = Long.parseLong(firstMultipleInput[3]);

		long result = MakingCandiesResult.minimumPasses(m, w, p, n);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
