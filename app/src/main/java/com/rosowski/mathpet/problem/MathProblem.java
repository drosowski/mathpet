package com.rosowski.mathpet.problem;

import com.rosowski.mathpet.Tuple;

public interface MathProblem {

	Tuple getNumbers();

	boolean checkAnswer(int answer);

}