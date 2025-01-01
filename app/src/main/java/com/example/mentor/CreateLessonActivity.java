package com.example.mentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CreateLessonActivity extends AppCompatActivity {
    private EditText lessonTitleEditText;
    private EditText lessonContentEditText;
    private LinearLayout questionsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_lesson);

        lessonTitleEditText = findViewById(R.id.lessonTitleEditText);
        lessonContentEditText = findViewById(R.id.lessonContentEditText);

        // Retrieve the module name from the intent
        String moduleName = getIntent().getStringExtra("moduleName");

        // Check if the moduleName is not null
        if (moduleName != null) {
            setTitle("Create Lesson for " + moduleName);

            // Update the TextView with the module name
            TextView selectedModuleText = findViewById(R.id.selectedModuleText);
            selectedModuleText.setText("Module: " + moduleName);
        }

        questionsContainer = findViewById(R.id.questionsContainer);
        Button addQuestionButton = findViewById(R.id.addQuestionButton);

        // Set up "Add Question" button
        addQuestionButton.setOnClickListener(v -> addNewQuestion());

        Button saveLessonButton = findViewById(R.id.saveLessonButton);

        // Set up Save button with validation
        saveLessonButton.setOnClickListener(v -> saveLesson());
    }

    private void saveLesson() {
        String title = lessonTitleEditText.getText().toString().trim();
        String content = lessonContentEditText.getText().toString().trim();

        // Validate Title and Content
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a lesson title", Toast.LENGTH_SHORT).show();
            return;
        }

        if (content.isEmpty()) {
            Toast.makeText(this, "Please enter lesson content", Toast.LENGTH_SHORT).show();
            return;
        }

        // Proceed with saving the lesson (e.g., return the data to the calling activity)
        Intent resultIntent = new Intent();
        resultIntent.putExtra("lessonTitle", title);
        resultIntent.putExtra("lessonContent", content);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    // Method to add a new question dynamically
    private void addNewQuestion() {
        LayoutInflater inflater = LayoutInflater.from(this);

        // Create Question Layout
        LinearLayout questionLayout = new LinearLayout(this);
        questionLayout.setOrientation(LinearLayout.VERTICAL);
        questionLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        questionLayout.setPadding(20, 20, 20, 20);

        // Add Question EditText
        LinearLayout questionRow = new LinearLayout(this);
        questionRow.setOrientation(LinearLayout.HORIZONTAL);
        questionRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        questionRow.setPadding(10, 0, 10, 10);

        EditText questionEditText = new EditText(this);
        questionEditText.setHint("Enter Question");
        questionEditText.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        questionEditText.setBackgroundResource(R.drawable.edit_text_background);
        questionRow.addView(questionEditText);

        // Delete Question Icon
        ImageView deleteQuestionImage = new ImageView(this);
        deleteQuestionImage.setImageResource(R.drawable.baseline_delete_24);
        deleteQuestionImage.setPadding(10, 10, 10, 10);
        deleteQuestionImage.setOnClickListener(v -> {
            // Remove the entire question layout
            questionsContainer.removeView(questionLayout);
        });
        questionRow.addView(deleteQuestionImage);

        questionLayout.addView(questionRow);

        // Add Fixed Number of Answers
        for (int i = 0; i < 3; i++) {
            addAnswerOption(questionLayout);
        }

        // Add Question Layout to Container
        questionsContainer.addView(questionLayout);
    }




    // Method to add an answer option dynamically
    private void addAnswerOption(LinearLayout questionLayout) {
        // Create Answer Row
        LinearLayout answerRow = new LinearLayout(this);
        answerRow.setOrientation(LinearLayout.HORIZONTAL);
        answerRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        answerRow.setPadding(40, 10, 20, 10);

        // RadioButton for Correct Answer
        RadioButton correctAnswerRadio = new RadioButton(this);
        correctAnswerRadio.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        correctAnswerRadio.setOnClickListener(v -> {
            // Ensure only one RadioButton is selected in this question
            for (int i = 0; i < questionLayout.getChildCount(); i++) {
                View child = questionLayout.getChildAt(i);
                if (child instanceof LinearLayout) {
                    LinearLayout childLayout = (LinearLayout) child;
                    if (childLayout.getChildAt(0) instanceof RadioButton) {
                        ((RadioButton) childLayout.getChildAt(0)).setChecked(false);
                    }
                }
            }
            correctAnswerRadio.setChecked(true);
        });
        answerRow.addView(correctAnswerRadio);

        // EditText for Answer
        EditText answerEditText = new EditText(this);
        answerEditText.setHint("Enter Answer");
        answerEditText.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        answerEditText.setBackgroundResource(R.drawable.edit_text_background);
        answerRow.addView(answerEditText);

        // Delete Answer Icon
        ImageView deleteAnswerImage = new ImageView(this);
        deleteAnswerImage.setImageResource(R.drawable.baseline_delete_24);
        deleteAnswerImage.setPadding(10, 10, 10, 10);
        deleteAnswerImage.setOnClickListener(v -> {
            // Remove the specific answer row
            questionLayout.removeView(answerRow);
        });
        answerRow.addView(deleteAnswerImage);

        // Add Answer Row to Question Layout
        questionLayout.addView(answerRow);
    }

}
