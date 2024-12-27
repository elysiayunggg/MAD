package com.example.pizza;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        List<String> modules = new ArrayList<>();
        List<List<String>> lessons = new ArrayList<>();

        switch (category) {
            case "Career Advancement":
                // Course 1: Resume Building
                modules = new ArrayList<>();
                modules.add("Module 1: Resume Writing");
                modules.add("Module 2: Formatting");

                lessons = new ArrayList<>();
                List<String> resumeModule1 = new ArrayList<>();
                resumeModule1.add("Lesson 1: Writing Basics");
                resumeModule1.add("Lesson 2: Tailoring to Job Descriptions");
                List<String> resumeModule2 = new ArrayList<>();
                resumeModule2.add("Lesson 1: Formatting Tips");
                resumeModule2.add("Lesson 2: Finalizing and Proofreading");

                lessons.add(resumeModule1);
                lessons.add(resumeModule2);

                courses.add(new CustomModel(R.drawable.resume, "Resume Building", "10 Lessons", "4.5 / 5.0", "this course is about","John Doe", "Career Mentor", R.drawable.profile_girl2, modules, lessons));

                // Course 2: Interview Preparation
                modules = new ArrayList<>();
                modules.add("Module 1: Preparing for Interviews");
                modules.add("Module 2: Answering Questions");

                lessons = new ArrayList<>();
                List<String> interviewModule1 = new ArrayList<>();
                interviewModule1.add("Lesson 1: Researching the Company");
                interviewModule1.add("Lesson 2: Common Questions");
                List<String> interviewModule2 = new ArrayList<>();
                interviewModule2.add("Lesson 1: Behavioral Questions");
                interviewModule2.add("Lesson 2: Asking Questions");

                lessons.add(interviewModule1);
                lessons.add(interviewModule2);

                courses.add(new CustomModel(R.drawable.interview, "Interview Preparation", "8 Lessons", "4.7 / 5.0","this course is about what","Emily Su", "Master", R.drawable.profile_girl1, modules, lessons));

                // Course 3: Leadership Development
                modules = new ArrayList<>();
                modules.add("Module 1: Leadership Fundamentals");
                modules.add("Module 2: Team Management");

                lessons = new ArrayList<>();
                List<String> leadershipModule1 = new ArrayList<>();
                leadershipModule1.add("Lesson 1: Understanding Leadership Styles");
                leadershipModule1.add("Lesson 2: Communication Skills");
                List<String> leadershipModule2 = new ArrayList<>();
                leadershipModule2.add("Lesson 1: Delegation Techniques");
                leadershipModule2.add("Lesson 2: Conflict Resolution");

                lessons.add(leadershipModule1);
                lessons.add(leadershipModule2);

                courses.add(new CustomModel(R.drawable.leadership, "Leadership Development", "12 Lessons", "4.8 / 5.0","this course is about money", "James Wong", "Life Mentor", R.drawable.profile_mentor_boy,modules, lessons));

                // Course 4: Networking for Career Growth
                modules = new ArrayList<>();
                modules.add("Module 1: Building Connections");
                modules.add("Module 2: Leveraging Networks");

                lessons = new ArrayList<>();
                List<String> networkingModule1 = new ArrayList<>();
                networkingModule1.add("Lesson 1: Identifying Opportunities");
                networkingModule1.add("Lesson 2: Building Rapport");
                List<String> networkingModule2 = new ArrayList<>();
                networkingModule2.add("Lesson 1: Professional Platforms");
                networkingModule2.add("Lesson 2: Following Up");

                lessons.add(networkingModule1);
                lessons.add(networkingModule2);

                courses.add(new CustomModel(R.drawable.networking, "Networking for Career Growth", "10 Lessons", "4.6 / 5.0", "this course is about life","Qi Yi", "Professor", R.drawable.profile_mentor_girl,modules, lessons));

                break;

            // Add more categories here
        }
        return courses;
    }
}
                /*courses.add(new CustomModel(R.drawable.interview, "Interview Preparation", "8 Lessons", "4.7 / 5.0"));
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


