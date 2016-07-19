package com.rosowski.mathpet;

public interface MathProblem {

	Tuple getNumbers();

	boolean checkAnswer(int answer);

}