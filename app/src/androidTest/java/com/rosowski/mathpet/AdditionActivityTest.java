package com.rosowski.mathpet;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by danielrosowski on 17.07.16.
 */
@RunWith(AndroidJUnit4.class)
public class AdditionActivityTest {

    @Rule
    public ActivityTestRule<AdditionActivity> activityRule = new ActivityTestRule<>(
            AdditionActivity.class);

    @Test
    public void should_repeat_round_for_wrong_answer() throws Exception {
        // given
        AdditionActivity activity = activityRule.getActivity();
        MathProblem currentProblem = (MathProblem) getField(activity, "currentProblem");
        Levels.Level currentLevel = (Levels.Level) getField(activity, "currentLevel");

        // when
        // set answer to bound*2, this answer is definitely wrong!
        setAnswer(activity, String.valueOf(currentLevel.bound * 2));
        clickAnswerButton(activity);

        // then
        MathProblem nextProblem = (MathProblem) getField(activity, "currentProblem");
        assertEquals(nextProblem, currentProblem);
    }

    @Test
    public void should_proceed_to_next_round_for_correct_answer() throws Exception {
        // given
        AdditionActivity activity = activityRule.getActivity();
        MathProblem currentProblem = (MathProblem) getField(activity, "currentProblem");
        Tuple numbers = currentProblem.getNumbers();

        // when
        // set correct answer
        setAnswer(activity, String.valueOf(numbers.left + numbers.right));
        clickAnswerButton(activity);

        // then
        MathProblem nextProblem = (MathProblem) getField(activity, "currentProblem");
        assertFalse(nextProblem.equals(currentProblem));
    }

    private void clickAnswerButton(AdditionActivity activity) throws InterruptedException {
        Button button = (Button) activity.findViewById(R.id.sendAnswer);
        Runnable buttonClick = buttonClick(button);
        synchronized (buttonClick) {
            activity.runOnUiThread(buttonClick);
            buttonClick.wait();
        }
    }

    private void setAnswer(AdditionActivity activity, String answerText) {
        final EditText editText = (EditText) activity.findViewById(R.id.answer);
        activity.runOnUiThread(() -> editText.setText(answerText));
    }

    private Runnable buttonClick(Button button) {
        Runnable buttonClick = new Runnable() {
            @Override
            public void run() {
                button.performClick();
                synchronized (this) {
                    this.notify();
                }
            }
        };
        return buttonClick;
    }

    private Object getField(AdditionActivity activity, String fieldName) {
        try {
            Field field = activity.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(activity);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}