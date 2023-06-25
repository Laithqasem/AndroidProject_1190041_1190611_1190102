package com.example.finalproject;

public class Section {
    private String sectionID;
    private String instructorEmail;
    private String courseID;
    private int maxTrainees;
    //////

    public Section() {

    }

    public Section(String sectionID, String instructorEmail, String courseID, int maxTrainees) {
        this.sectionID = sectionID;
        this.instructorEmail = instructorEmail;
        this.courseID = courseID;
        this.maxTrainees = maxTrainees;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getMaxTrainees() {
        return maxTrainees;
    }

    public void setMaxTrainees(int maxTrainees) {
        this.maxTrainees = maxTrainees;
    }
}
