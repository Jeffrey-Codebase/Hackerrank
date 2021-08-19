package com.jeffrey.hackerrank.medium;

public class TreeHuffmanDecoding {

	class Decoding {

		/*
		 * Problem: Tree: Huffman Decoding
		 * https://www.hackerrank.com/challenges/tree-huffman-decoding/problem
		 *
		 * Time Complexity: O(N), N = the length of the input s string
		 *
		 * Space Complexity: O(H), H = the height of the tree
		 *
		 */
		int idx = 0;

		void decode(String s, Node root) {
			StringBuilder sb = new StringBuilder();
			while (idx < s.length()) {
				Node p = root;
				helper(s, p, sb);
			}
			System.out.println(sb.toString());
		}

		void helper(String s, Node root, StringBuilder sb) {
			if (idx == s.length()) {
				sb.append(root.data);
				return;
			}
			if (s.charAt(idx) == '0') {
				if (root.left == null) {
					sb.append(root.data);
				} else {
					idx++;
					helper(s, root.left, sb);
				}
			} else {
				if (root.right == null) {
					sb.append(root.data);
				} else {
					idx++;
					helper(s, root.right, sb);
				}
			}
		}

	}

	abstract class Node implements Comparable<Node> {
		public int frequency; // the frequency of this tree
		public char data;
		public Node left, right;

		public Node(int freq) {
			frequency = freq;
		}

		// compares on the frequency
		@Override
		public int compareTo(Node tree) {
			return frequency - tree.frequency;
		}
	}
}
