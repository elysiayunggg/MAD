package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {

    ImageView courseImageView, mentorProfile;
    TextView courseTitleTextView, courseDetailsTextView, mentorName, mentorJob;
    ExpandableListView lessonsExpandableListView;
    Button enrollButton;

    private boolean isCourseStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Initialize UI elements
        Toolbar toolbar = findViewById(R.id.toolbar1);
        courseImageView = findViewById(R.id.courseImageView);
        courseTitleTextView = findViewById(R.id.courseTitle);
        courseDetailsTextView = findViewById(R.id.tvAboutDetails);
        lessonsExpandableListView = findViewById(R.id.expandableListView);
        enrollButton = findViewById(R.id.enrollButton);
        mentorProfile = findViewById(R.id.mentorProfile);
        mentorName = findViewById(R.id.mentorName);
        mentorJob = findViewById(R.id.mentorJob);

        // Initialize the "About" section TextViews
        TextView tvAbout = findViewById(R.id.tvAbout);
        TextView tvAboutDetails = findViewById(R.id.tvAboutDetails);

        // Setup Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed()); // Handle back button click

        // Retrieve course details passed from the previous activity
        CustomModel courseDetails = (CustomModel) getIntent().getSerializableExtra("courseDetails");

        if (courseDetails != null) {
            // Set course image and details
            courseImageView.setImageResource(courseDetails.getImage());
            courseTitleTextView.setText(courseDetails.getTitle());
            courseDetailsTextView.setText(courseDetails.getLesson());

            // Set About section
            tvAbout.setText("About");
            tvAboutDetails.setText(courseDetails.getAbout()); // Display the course "About" content


            // Set mentor details
            mentorProfile.setImageResource(courseDetails.getMentorProfileImage());
            mentorName.setText(courseDetails.getMentorName());
            mentorJob.setText(courseDetails.getMentorJob());

            // Populate ExpandableListView
            CourseDetailsExpandableListAdapter adapter = new CourseDetailsExpandableListAdapter(
                    this,
                    courseDetails.getModules(),
                    courseDetails.getLessons()
            );
            lessonsExpandableListView.setAdapter(adapter);
            setExpandableListViewHeight(lessonsExpandableListView);

            // Set click listener on mentor profile
            mentorProfile.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity2.this, MessageMentorActivity.class);
                intent.putExtra("mentorName", courseDetails.getMentorName()); // Pass mentor name
                startActivity(intent);
            });
        }

        // Enroll Button Functionality
        enrollButton.setOnClickListener(v -> {
            if (!isCourseStarted) {
                // Show Snackbar message saying "Enrolled in course"
                Snackbar.make(v, "Enrolled in course", Snackbar.LENGTH_SHORT).show();

                // Change button text to "Start Lesson"
                enrollButton.setText("Start Lesson");

                // Set isCourseStarted to true to indicate that the course has been enrolled
                isCourseStarted = true;

                // Optionally disable the button after enrollment to prevent multiple clicks
               // enrollButton.setEnabled(false);
            }
        });
    }

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
        super.onBackPressed(); // Navigate back to the previous activity
    }
}
