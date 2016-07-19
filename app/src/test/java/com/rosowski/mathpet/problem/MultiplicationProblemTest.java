package com.rosowski.mathpet.problem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MultiplicationProblemTest {

    @Test
    public void should_generate_numbers_below_bound() {
        int bound = 10;
        Tuple numbers = new MultiplicationProblem(bound).getNumbers();
        assertTrue(numbers.left < bound);
        assertTrue(numbers.right < bound);
    }

    @Test
    public void should_check_answer_correctly() {
        int bound = 10;
        MathProblem problem = new MultiplicationProblem(bound);
        Tuple numbers = problem.getNumbers();

        assertFalse(problem.checkAnswer(bound * 2));
        assertTrue(problem.checkAnswer(numbers.left * numbers.right));
    }	
    
    
    @Test
	public void should_render_correctly() throws Exception {
    	MathProblem problem = new MultiplicationProblem(10);
    	String render = problem.render();
    	assertTrue(render.matches("\\d+\\s\\*\\s\\d\\s=\\s"));
	}        
}
