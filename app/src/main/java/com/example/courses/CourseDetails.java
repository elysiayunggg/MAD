package com.example.courses;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CourseDetails extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private CourseDetailsExpandableListAdapter adapter;
    private Button enrollButton;
    private boolean isCourseStarted = false;  // Flag to track if course has started

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_details);

        expandableListView = findViewById(R.id.expandableListView);

        // Initialize the enroll button
        enrollButton = findViewById(R.id.enrollButton);

        // Initialize the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        // Display back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Sample data for groups and children
        List<String> groupList = new ArrayList<>();
        groupList.add("Introduction");
        groupList.add("Module 1: Self-Confidence Basics");
        groupList.add("Module 2: Advanced Techniques");

        List<List<String>> childList = new ArrayList<>();

        List<String> introList = new ArrayList<>();
        introList.add("Overview of the course");
        introList.add("Why self-confidence matters");

        List<String> module1List = new ArrayList<>();
        module1List.add("Understanding self-confidence");
        module1List.add("Techniques for building confidence");

        List<String> module2List = new ArrayList<>();
        module2List.add("Advanced strategies");
        module2List.add("How to overcome setbacks");

        childList.add(introList);
        childList.add(module1List);
        childList.add(module2List);

        // Create and set the adapter
        adapter = new CourseDetailsExpandableListAdapter(this, groupList, childList);
        expandableListView.setAdapter(adapter);

        // Set up the "Enroll Course" button
        enrollButton.setOnClickListener(v -> {
            if (!isCourseStarted) {  // Check if the course has already started
                // Show Snackbar only if course hasn't started
                Snackbar.make(v, "Enrolled in course", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Close", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Dismiss the Snackbar when "Close" is clicked
                            }
                        })
                        .show();
                enrollButton.setText("Start Lesson");

                // Update the flag to indicate course has started
                isCourseStarted = true;
            } else {
                // If course is already started, change to "Lesson Started"
                enrollButton.setText("Start Lesson");
            }
        });

    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) { // Fix the method name to getItemId()
                case android.R.id.home:
                    // Handle the back button press
                    onBackPressed();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
