package com.example.adwindow.adwindow_admin.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String emailId;
    private String companyName;
    private String phoneNumber;

    public User() {
    }

    public User(String username, String emailId, String companyName, String phoneNumber) {
        this.username = username;
        this.emailId = emailId;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
