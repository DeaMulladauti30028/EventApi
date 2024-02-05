package com.example.eventapp.pojo.dto;

public class UserNetworkResponse {

    private int followersCount;
    private int followingCount;

    // Constructors
    public UserNetworkResponse() {
    }

    public UserNetworkResponse(int followersCount, int followingCount) {
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    // Getters and Setters
    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    // Optional: ToString, Equals, and HashCode methods
}
