package com.example.eventapp.pojo.dto;

public class UserDTO {
    private String Username;

    public UserDTO(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
