package com.example.adwindow.adwindow_admin.model;

import java.util.ArrayList;
import java.util.List;

public class Content {
    private String contentId;
    private String contentURL;
    private String uploadedByUser;
    private List<String> screensUploadedIn;

    public Content() {
    }

    public Content(String contentId, String contentURL, String uploadedByUser) {
        this.contentId = contentId;
        this.contentURL = contentURL;
        this.uploadedByUser = uploadedByUser;
        this.screensUploadedIn = new ArrayList<>();
    }

    public String getContentId() {
        return contentId;
    }

    public String getContentURL() {
        return contentURL;
    }

    public String getUploadedByUser() {
        return uploadedByUser;
    }

    public List<String> getScreensUploadedIn() {
        return screensUploadedIn;
    }
}
