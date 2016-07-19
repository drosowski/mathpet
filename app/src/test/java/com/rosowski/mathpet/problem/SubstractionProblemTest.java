package com.rosowski.mathpet.problem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SubstractionProblemTest {

    @Test
    public void should_generate_numbers_below_bound() {
        int bound = 10;
        Tuple numbers = new SubstractionProblem(bound).getNumbers();
        assertTrue(numbers.left < bound);
        assertTrue(numbers.right < bound);
    }
    
    @Test
    public void should_generate_left_number_greater_than_right_number() {
    	int bound = 10;
    	Tuple numbers = new SubstractionProblem(bound).getNumbers();
    	assertTrue(numbers.left >= numbers.right);
    }

    @Test
    public void should_check_answer_correctly() {
        int bound = 10;
        MathProblem substractionProblem = new SubstractionProblem(bound);
        Tuple numbers = substractionProblem.getNumbers();

        assertFalse(substractionProblem.checkAnswer(bound * 2));
        assertTrue(substractionProblem.checkAnswer(numbers.left - numbers.right));
    }	
    
    @Test
	public void should_render_correctly() throws Exception {
    	MathProblem problem = new SubstractionProblem(10);
    	String render = problem.render();
    	assertTrue(render.matches("\\d+\\s\\-\\s\\d\\s=\\s"));
	}     
}
