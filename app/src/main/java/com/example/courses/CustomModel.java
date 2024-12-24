package com.example.courses;

public class CustomModel {

    int image;
    String title, lesson, rating;

    public CustomModel(int image, String title, String lesson, String rating) {
        this.image = image;
        this.title = title;
        this.lesson = lesson;
        this.rating = rating;
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
}
