package com.example.finalproject;

public class Topic {
    private String topicID;
    private String courseID;
    private String topicName;

    public Topic() {

    }

    public Topic(String topicID, String courseID, String topicName) {
        this.topicID = topicID;
        this.courseID = courseID;
        this.topicName = topicName;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
