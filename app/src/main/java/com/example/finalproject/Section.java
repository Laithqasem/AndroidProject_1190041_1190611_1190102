package com.example.finalproject;

public class Section {
    private String sectionID;
    private String instructorEmail;
    private String courseID;
    private Integer maxTrainees;

    public Section() {

    }

    public Section(String sectionID, String instructorEmail, String courseID, Integer maxTrainees) {
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

    public Integer getMaxTrainees() {
        return maxTrainees;
    }

    public void setMaxTrainees(Integer maxTrainees) {
        this.maxTrainees = maxTrainees;
    }
}
