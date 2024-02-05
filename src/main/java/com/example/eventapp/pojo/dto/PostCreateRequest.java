package com.example.eventapp.pojo.dto;

public class PostCreateRequest {
    private String content;
    private String imageUrl;
    private Integer authorId; // Assuming you're passing the ID of the author
    private Integer eventId;

    public PostCreateRequest(String content, String imageUrl, Integer authorId, Integer eventId) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.authorId = authorId;
        this.eventId = eventId;
    }

    public PostCreateRequest() {

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
