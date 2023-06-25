package com.example.finalproject;

import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private ArrayList<Course> prerequisites;
    // Todo: photo
    private String startDate;
    private String endDate;
    private String registrationStart;
    private String registrationEnd;

    public Course() {

    }

    public Course(String courseID, String courseName, ArrayList<Course> prerequisites,
                  String startDate, String endDate, String registrationStart, String registrationEnd) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.prerequisites = prerequisites;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRegistrationStart() {
        return registrationStart;
    }

    public void setRegistrationStart(String registrationStart) {
        this.registrationStart = registrationStart;
    }

    public String getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(String registrationEnd) {
        this.registrationEnd = registrationEnd;
    }
}
