package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class FraudulentActivityNotificationsResult {
	
	/*
	 * Problem: Fraudulent Activity Notifications
	 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
	 *
	 * Time Complexity: O(NlogD), N = the length of input e List, D = the value of input d
	 *
	 * Space Complexity: O(D)
	 *
	 */
	
	public static int activityNotifications(List<Integer> e, int d) {
		int ans = 0;
		TreeSet<Integer> low = new TreeSet<Integer>(
				(i1, i2) -> e.get(i1).equals(e.get(i2)) ? i1 - i2 : e.get(i1) - e.get(i2));
		TreeSet<Integer> high = new TreeSet<Integer>(
				(i1, i2) -> e.get(i1).equals(e.get(i2)) ? i1 - i2 : e.get(i1) - e.get(i2));
		for (int i = 0; i < d; i++) {
			if (low.size() > high.size()) {
				low.add(i);
				high.add(low.pollLast());
			} else {
				high.add(i);
				low.add(high.pollFirst());
			}
		}
		for (int i = d; i < e.size(); i++) {
			int mm = d % 2 == 0 ? e.get(low.last()) + e.get(high.first()) : e.get(low.last()) * 2;
			if (e.get(i) >= mm)
				ans++;
			if (high.remove(i - d)) {
				low.add(i);
				high.add(low.pollLast());
			} else {
				low.remove(i - d);
				high.add(i);
				low.add(high.pollFirst());
			}
		}

		return ans;

	}

}

public class FraudulentActivityNotifications {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int d = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = FraudulentActivityNotificationsResult.activityNotifications(expenditure, d);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}

