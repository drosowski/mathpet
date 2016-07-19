package com.rosowski.mathpet.problem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        MathProblem additionProblem = new AdditionProblem(bound);
        Tuple numbers = additionProblem.getNumbers();

        assertFalse(additionProblem.checkAnswer(bound * 2));
        assertTrue(additionProblem.checkAnswer(numbers.left + numbers.right));
    }
    
    @Test
	public void should_render_correctly() throws Exception {
    	MathProblem additionProblem = new AdditionProblem(10);
    	String render = additionProblem.render();
    	assertTrue(render.matches("\\d\\s\\+\\s\\d\\s=\\s"));
	}
}