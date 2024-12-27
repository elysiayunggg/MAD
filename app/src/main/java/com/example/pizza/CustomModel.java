package com.example.pizza;

import java.io.Serializable;
import java.util.List;

public class CustomModel implements Serializable {
    private static final long serialVersionUID = 1L;
    int image;
    String title, lesson, rating, about, mentorName, mentorJob;
    int mentorProfileImage;
    List<String> modules; // List of module titles
    List<List<String>> lessons; // Nested list of lessons for each module


    public CustomModel(int image, String title, String lesson, String rating, String about, String mentorName, String mentorJob, int mentorProfileImage, List<String> modules, List<List<String>> lessons) {
        this.image = image;
        this.title = title;
        this.lesson = lesson;
        this.rating = rating;
        this.about = about;
        this.mentorName = mentorName;
        this.mentorJob = mentorJob;
        this.mentorProfileImage = mentorProfileImage;
        this.modules = modules;
        this.lessons = lessons;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getMentorName() { return mentorName; }
    public String getMentorJob() { return mentorJob; }
    public int getMentorProfileImage() { return mentorProfileImage; }

    public List<String> getModules() {
        return modules;
    }

    public List<List<String>> getLessons() {
        return lessons;
    }
}
