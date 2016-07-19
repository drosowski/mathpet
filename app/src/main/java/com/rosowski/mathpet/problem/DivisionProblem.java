package com.rosowski.mathpet.problem;

import java.util.Random;

public class DivisionProblem implements MathProblem {

	private Tuple dividends;

	public DivisionProblem(int bound) {
		Random rand = new Random();
		int dividendRight = rand.nextInt(bound);
		if (dividendRight == 0) {
			dividendRight++;
		}
		int dividendLeft = dividendRight * rand.nextInt(bound);
		dividends = new Tuple(dividendLeft, dividendRight);
	}

	@Override
	public Tuple getNumbers() {
		return dividends;
	}

	@Override
	public boolean checkAnswer(int answer) {
		int quotient = dividends.left / dividends.right;
		return answer == quotient;
	}

}
