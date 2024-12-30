package com.example.mentor;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private static final List<Course> courses = new ArrayList<>();

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static List<Course> getCourses() {
        return new ArrayList<>(courses);
    }
}