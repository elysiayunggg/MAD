package com.example.mentor;

import java.util.List;

public class Course {
    private String title;
    private String description;
    private int lessonsCount;
    private String imageUrl;
    private List<String> modules;

    public Course(String title, String description, int lessonsCount, String imageUrl, List<String> modules) {
        this.title = title;
        this.description = description;
        this.lessonsCount = lessonsCount;
        this.imageUrl = imageUrl;
        this.modules = modules;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLessonsCount() {
        return lessonsCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getModules() {
        return modules;
    }
}