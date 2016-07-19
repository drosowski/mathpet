package com.rosowski.mathpet;

import com.rosowski.mathpet.problem.AdditionProblem;
import com.rosowski.mathpet.problem.MathProblem;
import com.rosowski.mathpet.problem.Tuple;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdditionActivity extends AppCompatActivity {

    private static final int NUM_ROUNDS = 10;
    private final ToastRenderer toast;
    private SharedPreferences sharedPref;

    private Levels levels;
    private MathProblem currentProblem;
    private Levels.Level currentLevel;
    private int currentRound = 0;
    private int savedLevel = 0;

    public AdditionActivity() {
        this.toast = new ToastRenderer();
        this.levels = new Levels();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        sharedPref = getPreferences(Context.MODE_PRIVATE);

        currentRound = sharedPref.getInt(getString(R.string.current_round), 0);
        savedLevel = sharedPref.getInt(getString(R.string.current_level), 0);

        if(currentRound == 0 && savedLevel == 0) {
            gotoNextLevel();
        }
        else {
            currentLevel = levels.getLevel(savedLevel);
            currentProblem = new AdditionProblem(currentLevel.bound);
            renderProblem();
        }
    }

    private void renderProgress() {
        TextView level = (TextView) findViewById(R.id.currentLevel);
        level.setText(String.format("Level %d", currentLevel.index));
        TextView progress = (TextView) findViewById(R.id.progress);
        progress.setText(String.format("Aufgabe %d von %d", currentRound, NUM_ROUNDS));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gotoNextLevel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveProgress();
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

    private void saveProgress() {
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt(getString(R.string.current_level), currentLevel.index);
        prefEditor.putInt(getString(R.string.current_round), currentRound);
        prefEditor.commit();
    }

    private void renderProblem() {
        Tuple numbers = currentProblem.getNumbers();
        TextView problem = (TextView) findViewById(R.id.problem);
        problem.setText(numbers.left + " + " + numbers.right + " = ");
        EditText answerField = (EditText) findViewById(R.id.answer);
        answerField.setText("");
        renderProgress();
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
