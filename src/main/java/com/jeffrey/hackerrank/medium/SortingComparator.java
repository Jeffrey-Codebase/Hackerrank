package com.jeffrey.hackerrank.medium;

import java.util.*;

class Player {
	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
}

class Checker implements Comparator<Player> {
	// complete this method
	@Override
	public int compare(Player a, Player b) {
		return a.score == b.score ? a.name.compareTo(b.name) : b.score - a.score;
	}
}
