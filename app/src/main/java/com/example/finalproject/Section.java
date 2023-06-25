package com.example.finalproject;

public class Section {
    private String sectionID;
    private String instructorEmail;
    private String courseID;

    public Section() {

    }

    public Section(String sectionID, String instructorEmail, String courseID) {
        this.sectionID = sectionID;
        this.instructorEmail = instructorEmail;
        this.courseID = courseID;
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
}
