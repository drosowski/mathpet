package com.rosowski.mathpet;

import com.rosowski.mathpet.problem.AdditionProblem;

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
}
