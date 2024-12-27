package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        ArrayList<CustomModel> customModelArrayList = new ArrayList<>();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        //get reference to 'CardView' in main.xml
        CardView careerCard = findViewById(R.id.careerCard);
        CardView selfDevelopmentCard = findViewById(R.id.selfDevelopmentCard);
        CardView personalGrowthCard = findViewById(R.id.personalGrowthCard);
        CardView healthCard = findViewById(R.id.healthCard);
        CardView advocacyCard = findViewById(R.id.advocacyCard);
        CardView relationshipCard = findViewById(R.id.relationshipCard);


        careerCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCourseList("Career Advancement");
            }
        });

        selfDevelopmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourseList("Self-Development");
            }
        });

        personalGrowthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourseList("Personal Growth");
            }
        });

        healthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourseList("Health and Wellness");
            }
        });

        advocacyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourseList("Advocacy and Strength");
            }
        });

        relationshipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourseList("Relationship and Support");
            }
        });


    }

    private void openCourseList(String category) {
        Intent intent = new Intent(this,CourseListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}