package com.example.pizza;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyCourseMenteeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_course_mentee);

        SharedPreferences sharedPreferences = getSharedPreferences("CoursePrefs", MODE_PRIVATE);
        Set<String> enrolledCourses = sharedPreferences.getStringSet("enrolledCourses", new HashSet<>());

// Example logic to display courses (assuming a RecyclerView or ListView is used)
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(enrolledCourses));
        listView.setAdapter(adapter);



    }

    public void onBackPressed() {
        navigateBackToMainActivity();
        super.onBackPressed();
    }

    private void navigateBackToMainActivity() {
        Intent intent = new Intent(MyCourseMenteeActivity.this, DiscoverScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}