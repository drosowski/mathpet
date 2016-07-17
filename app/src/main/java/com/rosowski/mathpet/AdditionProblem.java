package com.rosowski.mathpet;

import java.util.Random;

/**
 * Created by danielrosowski on 13.07.16.
 */
public class AdditionProblem {

    private Tuple summands;

    public AdditionProblem(int bound) {
        Random rand = new Random();
        int summandLeft = rand.nextInt(bound);
        int summandRight = rand.nextInt(bound);
        summands = new Tuple(summandLeft, summandRight);
    }

    public Tuple getNumbers() {
        return summands;
    }

    public boolean checkAnswer(int answer) {
        int sum = summands.left + summands.right;
        return answer == sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdditionProblem that = (AdditionProblem) o;

        return summands != null ? summands.equals(that.summands) : that.summands == null;

    }

    @Override
    public int hashCode() {
        return summands != null ? summands.hashCode() : 0;
    }
}
