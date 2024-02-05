package com.example.eventapp.pojo.dto;

import java.time.LocalDateTime;

public class CommentDetailResponse {
    private Integer id;
    private String content;
    private LocalDateTime timestamp;
    private UserDTO author;
    private Integer postId;

    public CommentDetailResponse() {
    }

    public CommentDetailResponse(Integer id, String content, LocalDateTime timestamp, UserDTO author, Integer postId) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.author = author;
        this.postId = postId;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
