package com.rosowski.mathpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdditionActivity extends AppCompatActivity {

    private static final int NUM_ROUNDS = 10;
    private final ToastRenderer toast;

    private Levels levels;
    private AdditionProblem currentProblem;
    private Levels.Level currentLevel;
    private int currentRound = 0;

    public AdditionActivity() {
        this.toast = new ToastRenderer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        levels = new Levels();
        gotoNextLevel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gotoNextLevel();
    }

    private void gotoNextLevel() {
        if (!levels.hasNextLevel()) {
            return;
        }
        currentLevel = levels.getNextLevel();
        showNextProblem();
    }

    public void showNextProblem() {
        if (currentRound == NUM_ROUNDS) {
            toast.show(getApplicationContext(), "Du hast den Level geschafft! Es geht weiter im nächsten Level...");
            currentRound = 0;
            Intent intent = new Intent(this, RewardActivity.class);
            startActivity(intent);
            return;
        }

        currentRound++;
        currentProblem = new AdditionProblem(currentLevel.bound);
        renderProblem();
    }

    private void renderProblem() {
        Tuple numbers = currentProblem.getNumbers();
        TextView problem = (TextView) findViewById(R.id.problem);
        problem.setText(numbers.left + " + " + numbers.right + " = ");
        EditText answerField = (EditText) findViewById(R.id.answer);
        answerField.setText("");
    }

    public void checkAnswer(View view) {
        int answer = getAnswer();
        if (answer == -1) {
            return;
        }
        if (currentProblem.checkAnswer(answer)) {
            toast.show(getApplicationContext(), "Richtig!");
            showNextProblem();
        } else {
            toast.show(getApplicationContext(), "Leider falsch. Probier es nochmal!");
            renderProblem();
        }
    }

    private int getAnswer() {
        EditText answerField = (EditText) findViewById(R.id.answer);
        Editable answerText = answerField.getText();
        int answer = -1;
        try {
            answer = Integer.valueOf(answerText.toString());
        } catch (NumberFormatException ex) {
            toast.show(getApplicationContext(), "Keine Zahl angegeben!");
        }

        return answer;
    }
}
