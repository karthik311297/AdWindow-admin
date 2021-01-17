package com.example.adwindow.adwindow_admin.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Screen {
    String locationName;
    String screenLocationTitle;
    String screenAddress;
    String screenPlaceImageUrl;
    Map<String, String> runningContentIds;
    Map<String, String> pendingContentIds;
    String pricing;
    String footfall;
    int numScreens;

    public Screen() {
    }

    public Screen(String locationName, String screenLocationTitle, String screenAddress, String screenPlaceImageUrl, String pricing, String footfall, int numScreens) {
        this.locationName = locationName;
        this.screenLocationTitle = screenLocationTitle;
        this.screenAddress = screenAddress;
        this.screenPlaceImageUrl = screenPlaceImageUrl;
        this.pricing = pricing;
        this.footfall = footfall;
        this.numScreens = numScreens;
        this.runningContentIds = new HashMap<>();
        this.pendingContentIds = new HashMap<>();
    }

    public String getLocationName() {
        return locationName;
    }

    public String getScreenLocationTitle() {
        return screenLocationTitle;
    }

    public String getScreenAddress() {
        return screenAddress;
    }

    public String getScreenPlaceImageUrl() {
        return screenPlaceImageUrl;
    }

    public String getPricing() {
        return pricing;
    }

    public String getFootfall() {
        return footfall;
    }

    public int getNumScreens() {
        return numScreens;
    }

    public Map<String, String> getRunningContentIds() {
        return runningContentIds;
    }

    public Map<String, String> getPendingContentIds() {
        return pendingContentIds;
    }
}
