package com.example.courses;

import java.util.ArrayList;

public class DetailsListData {
    private String courseTitle;
    private String courseImage;
    private String aboutDetails;
    private String mentorName;
    private String mentorJob;
    private String mentorProfileImage;
    private String whatYouLearn;
    private String enrollButtonText;
    private String groupName; // Group name for the expandable list
    private ArrayList<String> childItems; // List of child items under the group

    public DetailsListData(String courseTitle, String courseImage, String aboutDetails, String mentorName, String mentorJob,
                              String mentorProfileImage, String whatYouLearn, String enrollButtonText,String groupName, ArrayList<String> childItems) {
        this.courseTitle = courseTitle;
        this.courseImage = courseImage;
        this.aboutDetails = aboutDetails;
        this.mentorName = mentorName;
        this.mentorJob = mentorJob;
        this.mentorProfileImage = mentorProfileImage;
        this.whatYouLearn = whatYouLearn;
        this.enrollButtonText = enrollButtonText;
        this.groupName = groupName;
        this.childItems = childItems;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<String> getChildItems() {
        return childItems;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public String getAboutDetails() {
        return aboutDetails;
    }

    public String getMentorName() {
        return mentorName;
    }

    public String getMentorJob() {
        return mentorJob;
    }

    public String getMentorProfileImage() {
        return mentorProfileImage;
    }

    public String getWhatYouLearn() {
        return whatYouLearn;
    }

    public String getEnrollButtonText() {
        return enrollButtonText;
    }
}
