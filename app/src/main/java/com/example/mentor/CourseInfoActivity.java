package com.example.mentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseInfoActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private List<String> moduleList; // Modules
    private HashMap<String, List<String>> lessonMap; // Lessons under each module
    private ExpandableListAdapter expandableListAdapter;
    private ImageView courseImageView;
    private TextView titleTextView, descriptionTextView, lessonsTextView, modulesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        courseImageView = findViewById(R.id.courseImageView);
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.about);
        lessonsTextView = findViewById(R.id.lessonsTextView);

        Intent intent = getIntent();
        String title = intent.getStringExtra("courseTitle");
        String description = intent.getStringExtra("courseDescription");
        int lessonsCount = intent.getIntExtra("lessonsCount", 0);
        String imageUrl = intent.getStringExtra("imageUrl");
        ArrayList<String> modules = intent.getStringArrayListExtra("modules");

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        lessonsTextView.setText(String.format("Lessons: %d", lessonsCount));
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this).load(imageUrl).into(courseImageView);
        } else {
            courseImageView.setImageResource(R.drawable.error);
        }
    }




}
