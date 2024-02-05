package com.example.eventapp.pojo.dto;

public class UserDetailResponse {

    private String name;
    private String username;
    private String email;
    private String bio;
    private String profilePictureUrl;
    private String location;
    private int followerCount;
    private int followingCount;

    public UserDetailResponse(String name, String username, String email, String bio, String profilePictureUrl, String location, int followerCount,int followingCount) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.location = location;
        this.followerCount = followerCount;
        this.followingCount = followingCount;

    }

    public UserDetailResponse() {
    }

    public UserDetailResponse(String name, String bio, String profilePictureUrl, String location) {
        this.name = name;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.location = location;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }


    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }
}