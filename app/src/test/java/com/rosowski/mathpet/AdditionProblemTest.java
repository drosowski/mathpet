package com.rosowski.mathpet;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by danielrosowski on 13.07.16.
 */
public class AdditionProblemTest {

    @Test
    public void should_generate_numbers_below_bound() {
        int bound = 10;
        Tuple numbers = new AdditionProblem(bound).getNumbers();
        assertTrue(numbers.left < bound);
        assertTrue(numbers.right < bound);
    }

    @Test
    public void should_check_answer_correctly() {
        int bound = 10;
        AdditionProblem additionProblem = new AdditionProblem(bound);
        Tuple numbers = additionProblem.getNumbers();

        assertFalse(additionProblem.checkAnswer(bound * 2));
        assertTrue(additionProblem.checkAnswer(numbers.left + numbers.right));
    }
}