package com.rosowski.mathpet;

import java.util.Random;

public class SubstractionProblem implements MathProblem {

	private Tuple minuends;

	public SubstractionProblem(int bound) {
		Random rand = new Random();
		int minuendLeft = rand.nextInt(bound);
		int minuendRight = rand.nextInt(bound);
		if (minuendLeft > minuendRight) {
			minuends = new Tuple(minuendLeft, minuendRight);
		} else {
			minuends = new Tuple(minuendRight, minuendLeft);
		}
	}

	@Override
	public Tuple getNumbers() {
		return minuends;
	}

	@Override
	public boolean checkAnswer(int answer) {
		int difference = minuends.left - minuends.right;
		return answer == difference;
	}

}
