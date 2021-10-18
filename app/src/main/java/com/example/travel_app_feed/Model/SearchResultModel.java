package com.example.travel_app_feed.Model;

public class SearchResultModel {
    private int profilePicture;

    private String name;

    private String explantion;

    public SearchResultModel(int profilePicture, String name, String explantion){
        this.profilePicture = profilePicture;
        this.name = name;
        this.explantion = explantion;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public String getName() {
        return name;
    }

    public String getExplantion() {
        return explantion;
    }
}
