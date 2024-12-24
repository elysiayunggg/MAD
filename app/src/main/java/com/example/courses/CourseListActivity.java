package com.example.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;

public class CourseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_list);

        //initialize the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //display back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //getting the category passed from the MainActivity
        String category = getIntent().getStringExtra("category");
        setTitle(category);


        //String course = getIntent().getStringExtra("course");
        //setTitle(course);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<CustomModel> courseList = getCourseForCategory(category);
        // ArrayList<CourseDetailModel> coursedetail = getDetailsForCourse(course);


        CustomAdapter adapter = new CustomAdapter(this, courseList);
        recyclerView.setAdapter(adapter);

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

    private ArrayList<CustomModel> getCourseForCategory(String category) {
        ArrayList<CustomModel> courses = new ArrayList<>();

        switch (category) {
            case "Career Advancement":
                courses.add(new CustomModel(R.drawable.resume, "Resume Building", "10 Lessons", "4.5 / 5.0"));
                courses.add(new CustomModel(R.drawable.interview, "Interview Preparation", "8 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.jobsearch, "Job Search Strategies", "7 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.careerpath, "Career Path Exploration", "9 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.networking, "Networking for Career Growth", "6 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.personalbranding, "Building Personal Brand", "8 Lessons", "4.9 / 5.0"));
                courses.add(new CustomModel(R.drawable.salarynegotiation, "Salary Negotiation Techniques", "5 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.clock, "Professional Time Mastery", "10 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.email, "Professional Email Etiquette", "6 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.leadership, "Leadership Skills Development", "7 Lessons", "4.9 / 5.0"));
                break;

            case "Self-Development":
                courses.add(new CustomModel(R.drawable.speaker, "Public Speaking Skills", "12 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.clock, "Time Management", "6 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.procrastination, "Overcoming Procrastination", "7 Lessons", "4.9 / 5.0"));
                courses.add(new CustomModel(R.drawable.autism, "Mindfulness and Focus", "8 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.selfconfidence, "Building Self-Confidence", "9 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.emotions, "Emotional Intelligence", "6 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.goal, "Goal Setting and Achievement", "10 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.stress, "Dealing with Stress and Anxiety", "8 Lessons", "4.9 / 5.0"));
                courses.add(new CustomModel(R.drawable.positivethinking, "Positive Thinking and Attitude", "7 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.lifestyle, "Building Healthy Habits", "9 Lessons", "4.8 / 5.0"));
                break;


            case "Personal Growth":
                courses.add(new CustomModel(R.drawable.challenge, "Building Resilience", "6 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.finance, "Personal Finance Management", "10 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.discipline, "Self-Discipline for Success", "8 Lessons", "4.9 / 5.0"));
                courses.add(new CustomModel(R.drawable.creativity, "Unlocking Creativity", "7 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.decisionmaking, "Effective Decision Making", "6 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.timemanagement, "Personal Growth Through Time", "9 Lessons", "4.8 / 5.0"));
                break;


            case "Health and Wellness":
                courses.add(new CustomModel(R.drawable.yoga, "Yoga for Beginners", "8 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.stressmanagement, "Effective Stress Control", "6 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.mentalhealth, "Mental Health Awareness", "7 Lessons", "4.5 / 5.0"));
                courses.add(new CustomModel(R.drawable.nutrition, "Healthy Eating Habits", "9 Lessons", "4.9 / 5.0"));
                courses.add(new CustomModel(R.drawable.sleep, "Sleep Science and Health", "5 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.cardio, "Cardio Workouts for Beginners", "8 Lessons", "4.7 / 5.0"));
                break;

            case "Advocacy and Strength":
                courses.add(new CustomModel(R.drawable.policy, "Public Policy and Advocacy", "10 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.community, "Building Strong Communities", "8 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.collective, "The Power of Collective Action", "10 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.organizingbasic, "Community Organizing Basics", "7 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.socialjustice, "Social Justice and Advocacy", "6 Lessons", "4.5 / 5.0"));
                courses.add(new CustomModel(R.drawable.seeding, "Advocacy for the Environment", "8 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.activism, "Activism in Action", "9 Lessons", "4.9 / 5.0"));
                break;

            case "Relationship and Support":
                courses.add(new CustomModel(R.drawable.healthyrelationship, "Building Healthy Relationships", "10 Lessons", "4.8 / 5.0"));
                courses.add(new CustomModel(R.drawable.effectivecommunication, "Effective Communication Skills", "8 Lessons", "4.7 / 5.0"));
                courses.add(new CustomModel(R.drawable.conflictresolution, "Conflict Resolution Strategies", "6 Lessons", "4.9 / 5.0"));
                courses.add(new CustomModel(R.drawable.trust, "Trust and Relationship Building", "8 Lessons", "4.6 / 5.0"));
                courses.add(new CustomModel(R.drawable.family, "Family Support and Dynamics", "7 Lessons", "4.5 / 5.0"));
                courses.add(new CustomModel(R.drawable.peersupport, "Peer Support and Mentoring", "10 Lessons", "4.7 / 5.0"));
                break;
            default:
                courses.add(new CustomModel(R.drawable.warning, "Unknown Category", "0 Lessons", "N/A"));
                break;
        }
        return courses;
    }
}
   /* private ArrayList<CourseDetailModel>getDetailsForCourse (String course){
        ArrayList<CourseDetailModel> coursedetails = new ArrayList<>();

        switch(course){
            case "Resume Building":
                coursedetails.add(new CourseDetailModel( R.drawable.collective,"Resume Building", "this is a course that can help to improve yourself", "Elysia Yung", "Professional"));
                break;

            case "Interview Preparation":
                coursedetails.add(new CourseDetailModel( R.drawable.lifestyle,"Interview Preparation", "this is a course that can help to improve yourself", "Elysia Yung", "Professional"));
                break;

            default:
                coursedetails.add(new CourseDetailModel(R.drawable.warning, "unknown", "unknown", "unknown", "unknown"));
        }
        return coursedetails;
        */


