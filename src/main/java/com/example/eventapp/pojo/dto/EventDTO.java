package com.example.eventapp.pojo.dto;

public class EventDTO {
    private String name;

    public EventDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}