package com.example.eventapp.pojo.dto;

import java.time.LocalDateTime;

public class PostDetailResponse {

    private Integer id;
    private String content;
    private String imageUrl;
    private LocalDateTime timestamp;
    private UserDTO author; // Consider a simplified User DTO
    private EventDTO event;

    public PostDetailResponse() {
    }

    public PostDetailResponse(Integer id, String content, String imageUrl, LocalDateTime timestamp, UserDTO author, EventDTO event) {
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.author = author;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }
}