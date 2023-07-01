package com.example.finalproject;

import java.util.Arrays;

public class Trainee {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private String personal_photo;

    private String mobileNumber;
    private String address;
    private byte[] image;

    public Trainee() {

    }

    public Trainee(String email, String password, String firstName, String lastName,String personal_photo, String mobileNumber, String address) {

        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personal_photo=personal_photo;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.image = image;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonal_photo() {
        return personal_photo;
    }

    public void setPersonal_photo(String personal_photo) {
        this.personal_photo = personal_photo;
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

    public byte[] getImage() { return image;}

    public void setImage(byte[] image) { this.image = image;}

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Mobile Number: " + mobileNumber + "\n" +
                "Address: " + address + "\n";
    }
}
