package com.example.pizza;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quiz);


            // Find the back button
            ImageButton backButton = findViewById(R.id.backButton);

            // Set up a click listener for the back button
            backButton.setOnClickListener(v -> {
                // Navigate back to the previous activity
                onBackPressed();  // Default behavior: finish the current activity and go back
            });


            RadioGroup quizOptions = findViewById(R.id.quizOptions);
            for (int i = 0; i < quizOptions.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) quizOptions.getChildAt(i);
                radioButton.setTextColor(Color.WHITE); // Set text color to white
            }
            Button submitButton = findViewById(R.id.submitButton);
            ImageButton nextButton = findViewById(R.id.nextButton);
            ImageButton prevButton = findViewById(R.id.prevButton);

            submitButton.setOnClickListener(v -> {
                int selectedOptionId = quizOptions.getCheckedRadioButtonId();
                RadioButton selectedOption = findViewById(selectedOptionId);
                String selectedAnswer = selectedOption != null ? selectedOption.getText().toString() : "";

                if ("Belief in your abilities".equals(selectedAnswer)) {
                    Toast.makeText(QuizActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
                }
            });

            // Next button click handler to navigate to the next page (could be a completion page or back to lesson)
            nextButton.setOnClickListener(v -> {
                Intent intent = new Intent(QuizActivity.this, VideoActivity.class);  // Navigate to VideoActivity (or LessonActivity)
                startActivity(intent);
            });

            // Previous button click handler to go back to the previous activity (VideoActivity)
            prevButton.setOnClickListener(v -> {
                onBackPressed();  // This calls the default back navigation
            });
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  // Finish the current activity and go back to the previous one
        // Or, if you want to explicitly navigate to a specific activity, you can use:
        // Intent intent = new Intent(YourActivity.this, PreviousActivity.class);
        // startActivity(intent);
        }
    }

