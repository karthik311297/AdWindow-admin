package com.example.adwindow.adwindow_admin.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String userUid;
    private String emailId;
    private String companyName;
    private String phoneNumber;

    public User() {
    }

    public User(String userUid, String emailId, String companyName, String phoneNumber) {
        this.userUid = userUid;
        this.emailId = emailId;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
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

    public String getUserUid() {
        return userUid;
    }
}
