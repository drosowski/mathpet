package com.rosowski.mathpet.problem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rosowski.mathpet.Tuple;

/**
 * Created by danielrosowski on 13.07.16.
 */
public class DivisionProblemTest {

    @Test
    public void should_generate_right_number_below_bound() {
        int bound = 10;
        Tuple numbers = new DivisionProblem(bound).getNumbers();
        assertTrue(numbers.right < bound);
    }
    
    @Test
    public void should_generate_left_number_a_multiple_of_right_number() {
    	int bound = 10;
    	Tuple numbers = new DivisionProblem(bound).getNumbers();
    	assertTrue(numbers.left % numbers.right == 0);
    }
    

    @Test
    public void should_check_answer_correctly() {
        int bound = 10;
        MathProblem problem = new DivisionProblem(bound);
        Tuple numbers = problem.getNumbers();

        assertFalse(problem.checkAnswer(bound * 2));
        assertTrue(problem.checkAnswer(numbers.left / numbers.right));
    }
}