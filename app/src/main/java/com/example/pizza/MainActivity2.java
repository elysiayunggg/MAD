package com.example.pizza;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {

    // UI Elements
    private ImageView courseImageView, mentorProfile;
    private TextView courseTitleTextView, courseDetailsTextView, mentorName, mentorJob;
    private ExpandableListView lessonsExpandableListView;
    private Button enrollButton;
    private boolean isCourseStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CoursePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Initialize UI elements
        initializeUI();

        // Retrieve course details passed from the previous activity
        CustomModel courseDetails = (CustomModel) getIntent().getSerializableExtra("courseDetails");
        String courseId = (courseDetails != null) ? courseDetails.getTitle() : "default_course";

        // Retrieve saved state of course start status
        isCourseStarted = sharedPreferences.getBoolean("isCourseStarted_" + courseId, false);
        updateEnrollmentState(isCourseStarted, courseDetails, editor);

        if (courseDetails != null) {
            populateCourseDetails(courseDetails);
            setupExpandableListView(courseDetails);
        }

    }

    /**
     * Initialize UI elements.
     */
    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        courseImageView = findViewById(R.id.courseImageView);
        courseTitleTextView = findViewById(R.id.courseTitle);
        courseDetailsTextView = findViewById(R.id.tvAboutDetails);
        lessonsExpandableListView = findViewById(R.id.expandableListView);
        enrollButton = findViewById(R.id.enrollButton);
        mentorProfile = findViewById(R.id.mentorProfile);
        mentorName = findViewById(R.id.mentorName);
        mentorJob = findViewById(R.id.mentorJob);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    /**
     * Update enrollment state based on whether the course is started.
     */
    private void updateEnrollmentState(boolean isCourseStarted, CustomModel courseDetails, SharedPreferences.Editor editor) {
        if (isCourseStarted) {
            enrollButton.setText("Start Lesson");
        } else {
            enrollButton.setText("Enroll Course");
        }

        enrollButton.setOnClickListener(v -> {
            if (!isCourseStarted) {
                Snackbar.make(v, "Enrolled in course", Snackbar.LENGTH_SHORT).show();
                enrollButton.setText("Start Lesson");
                editor.putBoolean("isCourseStarted_" + courseDetails.getTitle(), true);
                addCourseToEnrolledList(editor, courseDetails.getTitle());
            } else {
                navigateToLessonActivity(courseDetails);
            }
        });
    }

    /**
     * Add course to enrolled list in SharedPreferences.
     */
    private void addCourseToEnrolledList(SharedPreferences.Editor editor, String courseId) {
        Set<String> enrolledCourses = getSharedPreferences("CoursePrefs", MODE_PRIVATE)
                .getStringSet("enrolledCourses", new HashSet<>());
        enrolledCourses.add(courseId);
        editor.putStringSet("enrolledCourses", enrolledCourses);
        editor.apply();
    }

    /**
     * Populate course details in the UI.
     */
    private void populateCourseDetails(CustomModel courseDetails) {
        courseImageView.setImageResource(courseDetails.getImage());
        courseTitleTextView.setText(courseDetails.getTitle());
        courseDetailsTextView.setText(courseDetails.getAbout());
        mentorProfile.setImageResource(courseDetails.getMentorProfileImage());
        mentorName.setText(courseDetails.getMentorName());
        mentorJob.setText(courseDetails.getMentorJob());

        mentorProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MessageMentorActivity.class);
            intent.putExtra("mentorName", courseDetails.getMentorName());
            startActivity(intent);
        });
    }

    /**
     * Set up ExpandableListView with modules and lessons.
     */
    private void setupExpandableListView(CustomModel courseDetails) {
        CourseDetailsExpandableListAdapter adapter = new CourseDetailsExpandableListAdapter(
                this,
                courseDetails.getModules(),
                courseDetails.getLessons()
        );
        lessonsExpandableListView.setAdapter(adapter);
        setExpandableListViewHeight(lessonsExpandableListView);

        lessonsExpandableListView.setOnChildClickListener((parent, view, groupPosition, childPosition, id) -> {
            // Retrieve the selected lesson details
            String selectedLessonTitle = courseDetails.getLessons().get(groupPosition).get(childPosition);
            String selectedLessonContent = courseDetails.getLessonContents().get(groupPosition).get(childPosition);
            String videoURL = courseDetails.getVideoURL(); // Retrieve the course video URL
            List<QuizQuestion> selectedQuizQuestions = courseDetails.getQuizQuestions();

            // Navigate to FactsActivity
            Intent intent = new Intent(MainActivity2.this, FactsActivity.class);
            intent.putExtra("courseDetails", courseDetails);
            intent.putExtra("lessonTitle", selectedLessonTitle);
            intent.putExtra("lessonContent", selectedLessonContent);
            intent.putExtra("videoURL", videoURL);
            intent.putExtra("quizQuestions", new ArrayList<>(selectedQuizQuestions));
            startActivity(intent);
            return true;
        });
    }

    /**
     * Navigate to LessonActivity with relevant details.
     */
    private void navigateToLessonActivity(CustomModel courseDetails) {
        Intent intent = new Intent(MainActivity2.this, FactsActivity.class);
        intent.putExtra("courseDetails", courseDetails);
        startActivity(intent);
    }

    private void navigateToLessonActivity(CustomModel courseDetails, String lessonTitle, String lessonContent, List<QuizQuestion> quizQuestions) {
        Intent intent = new Intent(MainActivity2.this, FactsActivity.class);
        intent.putExtra("courseDetails", courseDetails);
        intent.putExtra("lessonTitle", lessonTitle);
        intent.putExtra("lessonContent", lessonContent); // Pass the String directly
        intent.putExtra("quizQuestions", new ArrayList<>(quizQuestions));
        startActivity(intent);
    }

    /**
     * Dynamically adjust the height of the ExpandableListView.
     */
    private void setExpandableListViewHeight(ExpandableListView listView) {
        ExpandableListAdapter adapter = listView.getExpandableListAdapter();
        if (adapter == null) return;

        int totalHeight = 0;
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            View groupItem = adapter.getGroupView(i, false, null, listView);
            groupItem.measure(0, 0);
            totalHeight += groupItem.getMeasuredHeight();

            if (listView.isGroupExpanded(i)) {
                for (int j = 0; j < adapter.getChildrenCount(i); j++) {
                    View childItem = adapter.getChildView(i, j, false, null, listView);
                    childItem.measure(0, 0);
                    totalHeight += childItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getGroupCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
