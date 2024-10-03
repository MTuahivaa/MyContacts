package com.example.mycontacts;

import android.media.Image;

public class Contact {
    private int contactNumber;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private String email;
    private String job;
    private String situation;
    private int thumbnailIndex;

    // Constructeur
    public Contact(int contactNumber, String lastName, String firstName, String phoneNumber, String address, String postalCode, String email, String job, String situation, int thumbnailIndex) {
        this.contactNumber = contactNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.job = job;
        this.situation = situation;
        this.thumbnailIndex = thumbnailIndex;
    }
    public int getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
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
    public String getPhoneNumber() {return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getSituation() {
        return situation;
    }
    public void setSituation(String situation) {
        this.situation = situation;
    }
    public int getThumbnailIndex() {
        return thumbnailIndex;
    }
    public void setThumbnailIndex(int thumbnailIndex) {
        this.thumbnailIndex = thumbnailIndex;
    }
}