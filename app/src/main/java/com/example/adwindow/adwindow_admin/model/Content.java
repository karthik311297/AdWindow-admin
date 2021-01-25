package com.example.adwindow.adwindow_admin.model;

import java.util.Map;

public class Content {
    private String contentId;
    private String contentURL;
    private String uploadedByUser;

    private Map<String,Boolean> advertisementsStatus;

    private String contentName;

    public Content() {
    }

    public Content(String contentId, String contentURL, String uploadedByUser,Map<String, Boolean> advertisementsStatus, String contentName) {
        this.contentId = contentId;
        this.contentURL = contentURL;
        this.uploadedByUser = uploadedByUser;
        this.advertisementsStatus = advertisementsStatus;
        this.contentName = contentName;
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

    public String getContentName() {
        return contentName;
    }

    public Map<String, Boolean> getAdvertisementsStatus() {
        return advertisementsStatus;
    }

}
