package com.example.eventapp.pojo.dto;

public class UserUpdateRequest {

    private String Name;
    private String Bio;
    private String ProfilePictureUrl;
    private String Location;

    public UserUpdateRequest(String name, String bio, String profilePictureUrl, String location) {
        Name = name;
        Bio = bio;
        ProfilePictureUrl = profilePictureUrl;
        Location = location;
    }

    public UserUpdateRequest() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getProfilePictureUrl() {
        return ProfilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        ProfilePictureUrl = profilePictureUrl;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
