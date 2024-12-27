package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LessonActivity extends AppCompatActivity {

    int currentLessonIndex = 0; // To keep track of the current lesson

    // Lesson content
    String[] lessonTitles = {"Definition", "Importance", "Benefits", "Key Concepts"};
    String[] lessonContents = {
            "Self-confidence is the belief in your abilities, qualities, and judgment.",
            "Self-confidence improves decision-making, resilience, and strengthens relationships.",
            "Self-confidence helps you recover quickly from setbacks and builds stronger relationships.",
            "Being self-assured helps you communicate clearly and maintain healthy boundaries."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Find the back button
        ImageButton backButton = findViewById(R.id.backButton);

        // Set up a click listener for the back button
        backButton.setOnClickListener(v -> {
            // Navigate back to the previous activity
            onBackPressed();  // Default behavior: finish the current activity and go back
        });

        // Initialize views
        TextView courseTitle = findViewById(R.id.courseTitle);
        TextView lessonTitle = findViewById(R.id.lessonTitle);
        TextView lessonContent = findViewById(R.id.lessonContent);
        TextView progressText = findViewById(R.id.progressText);
        ImageButton nextButton = findViewById(R.id.nextButton);
        ImageButton prevButton = findViewById(R.id.prevButton);

        // Previous button click handler to go back to the previous activity
        prevButton.setOnClickListener(v -> {
            onBackPressed();  // This calls the default back navigation
        });

        // Handle next button click to navigate to the next page (VideoActivity)
        nextButton.setOnClickListener(v -> {
            if (currentLessonIndex < lessonTitles.length - 1) {
                currentLessonIndex++;
                updateLessonContent(lessonTitle, lessonContent, progressText);
            } else {
                // Navigate to the VideoActivity after the last lesson
                Intent intent = new Intent(LessonActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });

        // Set initial content
        courseTitle.setText("Understanding Self-Confidence");
        lessonTitle.setText(lessonTitles[currentLessonIndex]);
        lessonContent.setText(lessonContents[currentLessonIndex]);
        progressText.setText("Building Self-Confidence\nLesson " + (currentLessonIndex + 1) + " of 4");
    }

    // Update lesson content dynamically
    private void updateLessonContent(TextView lessonTitle, TextView lessonContent, TextView progressText) {
        lessonTitle.setText(lessonTitles[currentLessonIndex]);
        lessonContent.setText(lessonContents[currentLessonIndex]);
        progressText.setText("Building Self-Confidence\nLesson " + (currentLessonIndex + 1) + " of 4");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  // Finish the current activity and go back to the previous one
        // Or, if you want to explicitly navigate to a specific activity, you can use:
        // Intent intent = new Intent(YourActivity.this, PreviousActivity.class);
        // startActivity(intent);
    }
}
