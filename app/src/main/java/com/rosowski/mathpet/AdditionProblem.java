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
}
