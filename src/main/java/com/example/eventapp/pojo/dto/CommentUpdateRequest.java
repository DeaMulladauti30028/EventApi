package com.example.eventapp.pojo.dto;

public class CommentUpdateRequest {
    private String content;

    public CommentUpdateRequest() {
    }

    public CommentUpdateRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
