package com.rosowski.mathpet.problem;

import java.util.Random;

public class MultiplicationProblem implements MathProblem {

    private Tuple factors;

    public MultiplicationProblem(int bound) {
        Random rand = new Random();
        int factorLeft = rand.nextInt(bound);
        int factorRight = rand.nextInt(bound);
        factors = new Tuple(factorLeft, factorRight);
    }
    
	@Override
	public Tuple getNumbers() {
		return factors;
	}

	@Override
	public boolean checkAnswer(int answer) {
		int product = factors.left * factors.right;
		return product == answer;
	}

}
