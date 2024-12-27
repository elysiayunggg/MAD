package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Find the back button
        ImageButton backButton = findViewById(R.id.backButton);

        // Set up a click listener for the back button
        backButton.setOnClickListener(v -> {
            // Navigate back to the previous activity
            onBackPressed();  // Default behavior: finish the current activity and go back
        });

        WebView videoWebView = findViewById(R.id.videoWebView);
        ImageButton nextButton = findViewById(R.id.nextButton);
        ImageButton prevButton = findViewById(R.id.prevButton);

        // Load a YouTube video (or any other URL)
        videoWebView.loadUrl("https://youtu.be/w-HYZv6HzAs?si=oKaCrUOYvLhzdGad");

        // Next button click handler to navigate to QuizActivity
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(VideoActivity.this, QuizActivity.class);  // Navigate to QuizActivity
            startActivity(intent);
        });

        // Previous button click handler to go back to the previous activity
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
