package com.example.eventapp.pojo.dto;

public class UserListElement {
    private String name;
    private String username;
    private String profilePictureUrl;

    public UserListElement(String name, String username, String profilePictureUrl) {
        this.name = name;
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
