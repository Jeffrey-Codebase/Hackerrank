package com.jeffrey.hackerrank.medium;

import java.io.*;
import java.util.*;

public class TripleSum {

	/*
	 * Problem: Triple sum 
	 * https://www.hackerrank.com/challenges/triple-sum/problem
	 *
	 * Time Complexity: O(AlogA+BlogB+ClogC), A = the length of a array, B = the
	 * length of b array, C = the length of c array
	 *
	 * Space Complexity: O(1)
	 *
	 */
	static long triplets(int[] a, int[] b, int[] c) {
		long ans = 0;
		int idxa = 0, idxc = 0;
		long cnta = 0, cntc = 0;
		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);
		for (int i = 0; i < b.length; i++) {
			if (i < b.length - 1 && b[i] == b[i + 1])
				continue;
			while (idxa < a.length && a[idxa] <= b[i]) {
				if (idxa == a.length - 1 || a[idxa] != a[idxa + 1])
					cnta++;
				idxa++;

			}
			while (idxc < c.length && c[idxc] <= b[i]) {
				if (idxc == c.length - 1 || c[idxc] != c[idxc + 1])
					cntc++;
				idxc++;
			}
			ans += cnta * cntc;
		}
		return ans;

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] lenaLenbLenc = scanner.nextLine().split(" ");

		int lena = Integer.parseInt(lenaLenbLenc[0]);

		int lenb = Integer.parseInt(lenaLenbLenc[1]);

		int lenc = Integer.parseInt(lenaLenbLenc[2]);

		int[] arra = new int[lena];

		String[] arraItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < lena; i++) {
			int arraItem = Integer.parseInt(arraItems[i]);
			arra[i] = arraItem;
		}

		int[] arrb = new int[lenb];

		String[] arrbItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < lenb; i++) {
			int arrbItem = Integer.parseInt(arrbItems[i]);
			arrb[i] = arrbItem;
		}

		int[] arrc = new int[lenc];

		String[] arrcItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < lenc; i++) {
			int arrcItem = Integer.parseInt(arrcItems[i]);
			arrc[i] = arrcItem;
		}

		long ans = triplets(arra, arrb, arrc);

		bufferedWriter.write(String.valueOf(ans));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}