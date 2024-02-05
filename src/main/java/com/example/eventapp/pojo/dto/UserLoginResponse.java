package com.example.eventapp.pojo.dto;

public class UserLoginResponse {
    private String username;
    private String password;

    public UserLoginResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
