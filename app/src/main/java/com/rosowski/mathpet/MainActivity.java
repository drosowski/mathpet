package com.rosowski.mathpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rosowski.mathpet.problem.AdditionProblem;
import com.rosowski.mathpet.problem.DivisionProblem;
import com.rosowski.mathpet.problem.MultiplicationProblem;
import com.rosowski.mathpet.problem.SubstractionProblem;

public class MainActivity extends AppCompatActivity {

    public static final String PROBLEM_CLASS = "problem_class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPlus(View view) {
        Intent intent = new Intent(this, MathProblemActivity.class);
        intent.putExtra(PROBLEM_CLASS, AdditionProblem.class.getName());
        startActivity(intent);
    }

    public void startMinus(View view) {
        Intent intent = new Intent(this, MathProblemActivity.class);
        intent.putExtra(PROBLEM_CLASS, SubstractionProblem.class.getName());
        startActivity(intent);
    }

    public void startMultiply(View view) {
        Intent intent = new Intent(this, MathProblemActivity.class);
        intent.putExtra(PROBLEM_CLASS, MultiplicationProblem.class.getName());
        startActivity(intent);
    }

    public void startDivide(View view) {
        Intent intent = new Intent(this, MathProblemActivity.class);
        intent.putExtra(PROBLEM_CLASS, DivisionProblem.class.getName());
        startActivity(intent);
    }

    public void showGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }
}
