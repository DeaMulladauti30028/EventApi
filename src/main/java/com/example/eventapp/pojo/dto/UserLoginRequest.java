package com.example.eventapp.pojo.dto;

public class UserLoginRequest {
    private Integer id;
    private String name;
    private String username;

    public UserLoginRequest(Integer id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public UserLoginRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}