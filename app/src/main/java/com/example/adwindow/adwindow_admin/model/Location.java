package com.example.adwindow.adwindow_admin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {

    private String locationName;

    private Map<String,String> screenLocationTitles;

    public Location() {

    }

    public Location(String locationName) {
        this.locationName = locationName;
        this.screenLocationTitles = new HashMap<>();
    }

    public String getLocationName() {
        return locationName;
    }

    public Map<String, String> getScreenLocationTitles() {
        return screenLocationTitles;
    }

}
