package com.jeffrey.hackerrank.medium;

import java.util.*;

/*
 * Problem: Queues: A Tale of Two Stacks
 * https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem
 *
 * Time Complexity: O(N), N = the size of stack LinkedList
 *
 * Space Complexity: O(N)
 *
 */
class MyQueue<T> {
	LinkedList<T> stack;
	LinkedList<T> re_stack;
	int size;

	public MyQueue() {
		stack = new LinkedList<T>();
		re_stack = new LinkedList<T>();
		size = 0;
	}

	public void enqueue(T data) {
		stack.push(data);
		size++;
	}

	public void dequeue() {
		if (re_stack.size() == 0) {
			reverse();
		}
		re_stack.pop();
		size--;
	}

	public T peek() {
		if (re_stack.size() == 0) {
			reverse();
		}
		return re_stack.peek();
	}

	private void reverse() {
		for (int i = 0; i < size; i++) {
			re_stack.push(stack.pop());
		}
		while (stack.size() > 0)
			stack.pop();
	}
}

public class QueuesATaleOfTwoStacks {

	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<Integer>();

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			int operation = scan.nextInt();
			if (operation == 1) { // enqueue
				queue.enqueue(scan.nextInt());
			} else if (operation == 2) { // dequeue
				queue.dequeue();
			} else if (operation == 3) { // print/peek
				System.out.println(queue.peek());
			}
		}
		scan.close();
	}
}
