package com.jeffrey.hackerrank.medium;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class MaxMinResult {

	/*
	 * Problem: Max Min 
	 * https://www.hackerrank.com/challenges/angry-children/problem
	 *
	 * Time Complexity: O(NlogN), N = the length of the input arr list
	 *
	 * Space Complexity: O(1)
	 *
	 */

    public static int maxMin(int k, List<Integer> arr) {
    // Write your code here
        Collections.sort(arr);
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<=arr.size()-k;i++){
            ans = Math.min(ans,arr.get(i+k-1)-arr.get(i));
        }
        return ans;

    }

}

public class MaxMin {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = MaxMinResult.maxMin(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}