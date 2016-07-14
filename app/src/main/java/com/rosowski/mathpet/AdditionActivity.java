package com.rosowski.mathpet;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdditionActivity extends AppCompatActivity {

    private static final int NUM_ROUNDS = 10;
    private Levels levels;
    private AdditionProblem currentProblem;
    private Levels.Level currentLevel;
    private int currentRound = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        levels = new Levels();
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
        if(currentRound == NUM_ROUNDS) {
            showToast("Du hast den Level geschafft! Es geht weiter im nächsten Level...");
            currentRound = 0;
            gotoNextLevel();
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
        EditText answerField = (EditText) findViewById(R.id.answer);
        Editable answerText = answerField.getText();
        int answer = Integer.valueOf(answerText.toString());

        if (currentProblem.checkAnswer(answer)) {
            showToast("Richtig!");
            showNextProblem();
        } else {
            showToast("Leider falsch. Probier es nochmal!");
            renderProblem();
        }
    }

    private void showToast(String msg) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }
}
