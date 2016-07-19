package com.rosowski.mathpet.problem;

public class ProblemFactory {

	public MathProblem createFor(String problemClass, int bound) {
		Class<?> clazz;
		try {
			clazz = Class.forName(problemClass);
			return (MathProblem) clazz.getConstructor(int.class).newInstance(bound);
		} catch (Exception e) {
			throw new RuntimeException("Something bad happend!", e);
		}
	}
}
