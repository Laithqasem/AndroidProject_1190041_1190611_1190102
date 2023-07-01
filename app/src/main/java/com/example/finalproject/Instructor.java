package com.example.finalproject;

public class Instructor {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String personal_photo;
    private String mobileNumber;
    private String address;
    private String specialization;
    private String canTeach;

    public Instructor() {

    }

    public Instructor(String email, String password, String firstName, String lastName, String personal_photo, String mobileNumber,
                      String address, String specialization, String canTeach) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.personal_photo = personal_photo;
        this.address = address;
        this.specialization = specialization;
        this.canTeach = canTeach;
    }

    public String getPersonal_photo() {
        return personal_photo;
    }

    public void setPersonal_photo(String personal_photo) {
        this.personal_photo = personal_photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCanTeach() {
        return canTeach;
    }

    public void setCanTeach(String canTeach) {
        this.canTeach = canTeach;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                ", degree='" + canTeach + '\'' +
                '}';
    }
}
