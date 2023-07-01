package com.example.finalproject;

public class TraineeToSection {
    private String traineeToSectionID;
    private String sectionID;
    private String traineeEmail;
    private Boolean status;

    public TraineeToSection() {

    }

    public TraineeToSection(String traineeToSectionID, String sectionID, String traineeEmail, Boolean status) {
        this.traineeToSectionID = traineeToSectionID;
        this.sectionID = sectionID;
        this.traineeEmail = traineeEmail;
        this.status = status;
    }

    public String getTraineeToSectionID() {
        return traineeToSectionID;
    }

    public void setTraineeToSectionID(String traineeToSectionID) {
        this.traineeToSectionID = traineeToSectionID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String traineeEmail) {
        this.traineeEmail = traineeEmail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
