package com.example.mentor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCourseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private ArrayList<Course> courseList;
    private SharedPreferences sharedPreferences;
    private Button addCourseButton;

    private static final String COURSE_LIST_KEY = "courseList";
    private static final String LESSON_MAP_KEY = "lessonMap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        addCourseButton = findViewById(R.id.addCourseButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("CourseData", MODE_PRIVATE);

        // Load courses and lesson maps from SharedPreferences
        courseList = loadCourses();

        // Initialize adapter with delete functionality
        courseAdapter = new CourseAdapter(this, courseList, position -> {
            courseList.remove(position); // Remove course from the list
            saveCourses(); // Save updated list to SharedPreferences
            courseAdapter.notifyItemRemoved(position); // Notify adapter about the removed item
            courseAdapter.notifyItemRangeChanged(position, courseList.size()); // Refresh the list
        });

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseAdapter);
        Log.d("MyCourseActivity", "Loaded Courses: " + courseList);

        // Handle add course button click
        addCourseButton.setOnClickListener(v -> {
            Intent intent = new Intent(MyCourseActivity.this, AddCourseActivity.class);
            startActivityForResult(intent, 1); // Request code 1 to handle the result
        });
    }

    // Load courses and their lesson maps from SharedPreferences
    private ArrayList<Course> loadCourses() {
        Gson gson = new Gson();

        // Load course list
        String courseListJson = sharedPreferences.getString(COURSE_LIST_KEY, "");
        ArrayList<Course> courses = new ArrayList<>();
        if (!courseListJson.isEmpty()) {
            Type courseListType = new TypeToken<ArrayList<Course>>() {}.getType();
            courses = gson.fromJson(courseListJson, courseListType);
        }

        // Load lesson maps for each course
        for (Course course : courses) {
            String lessonMapJson = sharedPreferences.getString(LESSON_MAP_KEY + "_" + course.getTitle(), "");
            if (!lessonMapJson.isEmpty()) {
                Type lessonMapType = new TypeToken<HashMap<String, List<String>>>() {}.getType();
                HashMap<String, List<String>> lessonMap = gson.fromJson(lessonMapJson, lessonMapType);
                course.setLessonsByModule(lessonMap);
            }
        }

        return courses;
    }

    // Save courses and their lesson maps to SharedPreferences
    private void saveCourses() {
        Gson gson = new Gson();

        // Save course list
        String courseListJson = gson.toJson(courseList);
        sharedPreferences.edit().putString(COURSE_LIST_KEY, courseListJson).apply();

        // Save lesson maps for each course
        for (Course course : courseList) {
            String lessonMapJson = gson.toJson(course.getLessonsByModule());
            sharedPreferences.edit().putString(LESSON_MAP_KEY + "_" + course.getTitle(), lessonMapJson).apply();
        }
    }

    // Handle the result returned from AddCourseActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String courseTitle = data.getStringExtra("courseTitle");
            String courseDescription = data.getStringExtra("courseDescription");
            int lessonsCount = data.getIntExtra("lessonsCount", 0);
            String imageUrl = data.getStringExtra("imageUrl");
            ArrayList<String> modules = data.getStringArrayListExtra("modules");

            Course newCourse = new Course(courseTitle, courseDescription, lessonsCount, imageUrl, modules);

            // Initialize empty lesson map for the new course
            newCourse.setLessonsByModule(new HashMap<>());
            for (String module : modules) {
                newCourse.getLessonsByModule().put(module, new ArrayList<>());
            }

            courseList.add(newCourse);
            saveCourses();
            courseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload courses from SharedPreferences
        courseList.clear();
        courseList.addAll(loadCourses());
        courseAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Finish the current activity to return to the previous one
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
